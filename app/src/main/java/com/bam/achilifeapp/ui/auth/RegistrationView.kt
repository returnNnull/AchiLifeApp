package com.bam.achilifeapp.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bam.achilifeapp.ui.components.EditText
import com.bam.achilifeapp.ui.components.PrimaryBtn
import com.bam.achilifeapp.ui.components.SnackBarError

@Composable
fun RegistrationScreen(
    navController: NavController?,
    viewModel: RegistrationViewModel = hiltViewModel()
) {

    val registerState by viewModel.registerState.collectAsState()
    if (registerState) {
        navController?.navigate("login"){
            launchSingleTop = true
        }
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            EditText(state = viewModel.emailState, hint = "Email")
            EditText(state = viewModel.passState, hint = "Введите пароль")
            EditText(state = viewModel.displayName, hint = "Имя")
            PrimaryBtn(
                state = viewModel.registerBtn,
                onClick = {
                    viewModel.registration()
                },
                text = "Зарегистрироваться"
            )
        }
        SnackBarError(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            notifyUiState = viewModel.notifyUiState
        )
    }
}


@Preview(showBackground = true)
@Composable
fun RegistrationView_Preview() {
    RegistrationScreen(null)
}