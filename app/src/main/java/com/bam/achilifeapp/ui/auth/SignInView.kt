package com.bam.achilifeapp.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bam.achilifeapp.ui.components.EditText
import com.bam.achilifeapp.ui.components.NotifyUiState
import com.bam.achilifeapp.ui.components.PrimaryBtn
import com.bam.achilifeapp.ui.components.SnackBarError


@Composable
fun SignInScreen(navController: NavController?, viewModel: SignInViewModel = hiltViewModel()) {

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {

            EditText(
                state = viewModel.emailUiState,
                hint = "Email"
            )
            EditText(
                state = viewModel.passUiState,
                hint = "Пароль"
            )
            PrimaryBtn(
                state = viewModel.signInBtnState,
                onClick = { viewModel.signIn() },
                text = "Войти"
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
fun SignIn_Preview() {
    MaterialTheme() {
        SignInScreen(navController = null)
    }


}