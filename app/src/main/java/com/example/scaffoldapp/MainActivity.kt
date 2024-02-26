package com.example.scaffoldapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.scaffoldapp.ui.theme.ScaffoldAPpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldAPpTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldApp()
                }
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun ScaffoldApp() {
   val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable(route = "Home") {
            MainScreen(navController)
        }
        composable(route = "Home") {
            InfoScreen(navController)
        }
        composable(route = "Home") {
            SettingsScreen(navController)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
        }
    )
    DropdownMenu(expanded = expanded,
        onDismissRequest = { expanded = false }) {
        DropdownMenuItem(text = { "info" }, onClick = { navController.navigate("info") })
        DropdownMenuItem(text = { "settings" }, onClick = { navController.navigate("settings") })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController) {
    TopAppBar(title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = {navController.navigateUp()}) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
        )
}

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { MainTopBar(title = "MyApp", navController )},
        content = {Text(text = "Content for Home screen")},
    )
}

@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar(title = "MyApp", navController ) },
                content = {Text(text = "Content for Info screen")},
    )
}


@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar(title = "MyApp", navController ) },
        content = {Text(text = "Content for Settings screen")},
    )
}

