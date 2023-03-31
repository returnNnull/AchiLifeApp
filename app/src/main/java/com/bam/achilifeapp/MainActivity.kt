package com.bam.achilifeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.bam.achilifeapp.ui.auth.RegistrationScreen
import com.bam.achilifeapp.ui.auth.SignInScreen
import com.bam.achilifeapp.ui.main.HomeScreen
import com.bam.achilifeapp.ui.theme.AchiLifeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AchiLifeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mainViewModel: MainViewModel = viewModel()
                    val authState by mainViewModel.userSession.loggedState.collectAsState()
                    mainViewModel.checkAuth()
                    if (authState)
                        AppNavHost(navController = navController)
                    else
                        AuthNavHost(navController = navController)

                }
            }
        }
    }
}

@Composable
fun AuthNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            SignInScreen(navController)
        }

        composable("registration") {
            RegistrationScreen(navController = navController)
        }

        composable("resetPassword") {

        }

    }
}

@Composable
fun AppNavHost(navController: NavHostController){
    NavHost(navController = navController, startDestination = "home"){
        composable("home") {
            HomeScreen(navController)
        }
        composable("category") {

        }
        composable("achievement") {

        }
    }
}

fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation(startDestination = "login", route = "auth") {
        composable("login") {

            SignInScreen(navController)
        }

        composable("registration") {
            RegistrationScreen(navController = navController)
        }

        composable("resetPassword") {

        }
    }
}


fun NavGraphBuilder.appGraph(navController: NavController) {
    navigation("home", "app") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("category") {

        }
        composable("achievement") {

        }
    }

}



@Preview(showBackground = true)
@Composable
fun HomeScreen_Preview() {
    Surface() {
        HomeScreen(navController = null)
    }
}