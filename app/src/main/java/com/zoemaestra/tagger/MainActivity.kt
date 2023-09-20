package com.zoemaestra.tagger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.zoemaestra.tagger.ui.theme.TaggerTheme
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaggerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navController: NavController) {
    var passwordText by rememberSaveable { mutableStateOf("") }
    var emailText by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 50.dp),
            textAlign = TextAlign.Center,
            fontSize = 70.sp
        )

        OutlinedTextField(
            value = emailText,
            onValueChange = { newText: String ->
                emailText = newText
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 0.dp),
            label = { Text("Email") },
        )

        OutlinedTextField(
            value = passwordText,
            onValueChange = { newPassword: String ->
                passwordText = newPassword
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 0.dp),
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        FilledIconButton(
            onClick = { navController.navigate("second_screen_route") {
                popUpTo("first_screen_route") {
                    inclusive = true
                }
            } },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 0.dp),
        ) {
            Text("Login")
        }
        FilledTonalButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 0.dp),
        ) {
            Text("Register")
        }
    }
}

@Composable
fun SecondScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Populate this later") /*TODO*/
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "first_screen_route") {
        composable("first_screen_route") {
            FirstScreen(navController = navController)
        }
        composable("second_screen_route") {
            SecondScreen()
        }
        /*TODO: Change default launch screen when logged in*/
    }
}
