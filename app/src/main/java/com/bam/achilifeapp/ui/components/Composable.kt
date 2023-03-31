package com.bam.achilifeapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun SnackBarError(modifier: Modifier = Modifier, notifyUiState: NotifyUiState) {

    val state by notifyUiState.state.collectAsState()

    if (state.isOpen) {
        Snackbar(modifier = modifier) {
            Text(text = state.text)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditText(state: EditTextUiState, hint: String) {

    val model by state.flow().collectAsState()


    Column(horizontalAlignment = Alignment.End) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = model.text,
            singleLine = true,
            onValueChange = { state.text = it },
            isError = model.isError,
            label = {
                Text(text = hint)
            })
        Text(text = model.error, color = Color.Red, fontSize = 11.sp)
    }
}

@Composable
fun PrimaryBtn(state: BtnUiState, onClick: () -> Unit, text : String){

    val model by state.flow().collectAsState()

    Button(onClick = onClick, enabled = !model.block) {
        if (model.isWait){
            CircularProgressIndicator(color = Color.White)
        }
        else{
            Text(text = text)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun Inputs_Preview() {
    Column() {
        EditText(state = EditTextUiState(), hint = "Email")
        PrimaryBtn(state = BtnUiState(), onClick = { /*TODO*/ }, text = "SignIn")
    }

}