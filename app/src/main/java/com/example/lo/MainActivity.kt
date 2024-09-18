package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lo.R
import com.example.lo.ui.theme.LoginTheme
//import com.example.login.ui.theme.LoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { paddingValues ->
                        LoginForm(modifier = Modifier.padding(paddingValues))
                    }
                )
            }
        }
    }
}

@Composable
fun LoginForm(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Kirjaudu sisään",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Email Field
        UserInputText(
            keyboardType = KeyboardType.Email,
            onTextChanged = { email = it.text },
            textFieldValue = TextFieldValue(email),
            placeholder = "Sähköposti",
            modifier = Modifier.fillMaxWidth(),
            keyboardShown = false,
            onTextFieldFocused = {},
            focusState = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        UserInputText(
            keyboardType = KeyboardType.Password,
            onTextChanged = { password = it.text },
            textFieldValue = TextFieldValue(password),
            placeholder = "Salasana",
            modifier = Modifier.fillMaxWidth(),
            keyboardShown = false,
            onTextFieldFocused = {},
            focusState = false,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Handle login */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kirjaudu")
        }
    }
}

@Composable
fun UserInputText(
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChanged: (TextFieldValue) -> Unit,
    textFieldValue: TextFieldValue,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    borderColor: Color = Color.Gray,
    keyboardShown: Boolean,
    onTextFieldFocused: (Boolean) -> Unit,
    focusState: Boolean,
    placeholder: String = "",
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        TextField(
            value = textFieldValue.text,
            onValueChange = { newValue -> onTextChanged(TextFieldValue(newValue)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            visualTransformation = visualTransformation,
            maxLines = 1,
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
            placeholder = { Text(placeholder) }
        )
        if (keyboardType == KeyboardType.Password) {
            Icon(
                painter = painterResource(id = R.drawable.ic_password_visibility),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
                    .clickable { /* Toggle password visibility */ }
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_email),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginForm() {
    LoginTheme {
        LoginForm()
    }
}


