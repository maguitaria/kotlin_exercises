package com.example.bodymassindex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bodymassindex.ui.theme.BodyMassIndexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            BodyMassIndexTheme {
                BmiCalculatorUI(viewModel = BmiViewModel())
            }
        }
    }
}
@Composable
fun ProvideBmiViewModel(content: @Composable () -> Unit) {
    // Initialize the ViewModel within a @Composable function
    val viewModel: BmiViewModel = viewModel()

    DisposableEffect(viewModel) {
        onDispose {
            // Clean up any resources if needed
        }
    }

    content()
}
@Composable
fun BmiCalculatorUI(viewModel: BmiViewModel) {
    var heightInput by remember { mutableStateOf("") }
    var weightInput by remember { mutableStateOf("") }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Body Mass Index",
            style = MaterialTheme.typography.displaySmall, // Use h5 for consistent styling
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Body mass index (BMI) is a measure of body fat based on height and weight that applies to adult men and women. ",
            style = MaterialTheme.typography.bodyMedium, // Use h5 for consistent styling
            modifier = Modifier.padding(16.dp)
        )
        OutlinedTextField(
            value = heightInput,
            onValueChange = {
                heightInput = it
                viewModel.updateHeightCm(it.toFloatOrNull() ?: 0f)
            },
            label = { Text("Enter Height (cm)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = weightInput,
            onValueChange = {
                weightInput = it
                viewModel.updateWeight(it.toFloatOrNull() ?: 0f)
            },
            label = { Text("Enter Weight (kg)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Display the BMI result
        Text(
            text = "BMI: ${viewModel.bmi.value ?: "-"}",
            style = MaterialTheme.typography.displaySmall, // Use h5 for consistent styling
            modifier = Modifier.padding(16.dp)
        )
        // Display the BMI status
        Text(
            text = "Your BMI Category is: ${viewModel.calculateBmiStatus()}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}
