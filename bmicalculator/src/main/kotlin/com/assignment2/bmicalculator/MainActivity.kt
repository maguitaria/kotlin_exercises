package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.Calculate
import androidx.compose.material3.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmicalculator.ui.theme.*
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                ) { BMIApp() }
            }
        }
    }
}

@Composable
fun BMIApp(viewModel: BMIViewModel = viewModel()) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        var height by remember { mutableStateOf("") }
        var weight by remember { mutableStateOf("") }

        OutlinedTextField(
                value = height,
                onValueChange = {
                    height = it
                    viewModel.updateHeight(it.toFloatOrNull() ?: 0f)
                },
                label = { Text(stringResource(R.string.height_label)) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                keyboardOptions =
                        KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                        ),
                keyboardActions =
                        KeyboardActions(
                                onDone = { LocalSoftwareKeyboardController.current?.hide() }
                        )
        )

        OutlinedTextField(
                value = weight,
                onValueChange = {
                    weight = it
                    viewModel.updateWeight(it.toFloatOrNull() ?: 0f)
                },
                label = { Text(stringResource(R.string.weight_label)) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                keyboardOptions =
                        KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                        ),
                keyboardActions =
                        KeyboardActions(
                                onDone = { LocalSoftwareKeyboardController.current?.hide() }
                        )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
                onClick = {
                    // Do something with the calculated BMI
                    val bmi = viewModel.bmi
                    val message =
                            when {
                                bmi < 18.5 -> stringResource(R.string.underweight)
                                bmi < 24.9 -> stringResource(R.string.normal_weight)
                                bmi < 29.9 -> stringResource(R.string.overweight)
                                else -> stringResource(R.string.obese)
                            }
                    val resultMessage = stringResource(R.string.bmi_result, bmi, message)
                    showToast(context, resultMessage)
                },
                modifier = Modifier.fillMaxWidth(),
                colors =
                        ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colorScheme.primary
                        )
        ) {
            Icon(imageVector = Icons.Default.Calculate, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.calculate_bmi))
        }
    }
}

@Composable
fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun BMIAppPreview() {
    BMICalculatorTheme { BMIApp() }
}
