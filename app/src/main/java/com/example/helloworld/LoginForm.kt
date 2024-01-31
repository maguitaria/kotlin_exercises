package com.example.helloworld


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt


val purple = "#b688ea"
val purpleColor = Color(purple.toColorInt())
@Preview
@Composable
fun LoginForm() {
    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
            )
        {
    Text(
        modifier = Modifier.padding(8.dp), fontSize = 30.sp,
        color = purpleColor,
        textAlign = TextAlign.Left,
        fontWeight = FontWeight.Bold,
            text = "Login")
            LoginField(
                value = "",
                onChange = { },
                modifier = Modifier.fillMaxWidth()

            )
            PasswordField(value = "Password", onChange = {}, submit = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth())

            // Align the button to the right
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {},
                    enabled = true,
                    modifier = Modifier.fillMaxWidth(),

                    ) {
                    Text("Submit")
                }
            }



        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginField(
    value: String,
    onChange: (String) -> Unit,
    modifier : Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val trailingIcon = @Composable {
        Icon(
            Icons.Default.Person,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary,
        )
    }

    OutlinedTextField(
        value = (value),
        onValueChange = onChange,
        //modifier = modifier.border(1.dp, MaterialTheme.colorScheme.primary),
        trailingIcon = trailingIcon ,
        keyboardOptions =  KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {focusManager.moveFocus(FocusDirection.Down)}
        ),

        label = {Text("")},
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
    }

@Composable
fun PasswordField(
    value : String,
    onChange: (String) -> Unit,
    submit : () -> Unit,
    modifier: Modifier = Modifier,
    label : String = "Password",
    placeholder : String = "Enter your password"
) {
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Key,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }

    val trailingIcon = @Composable {
        IconButton(onClick = ({ isPasswordVisible = !isPasswordVisible })) {
            Icon(
                if (isPasswordVisible) Icons.Default.VisibilityOff
                else Icons.Default.Visibility,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }

    OutlinedTextField(value = value, onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = { submit() }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun LabeledCheckBox () {

}
