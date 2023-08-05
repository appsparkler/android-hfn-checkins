package com.example.hfncheckins.ui.components.CheckinScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hfncheckins.ui.theme.HFNCheckinsTheme

@Composable
fun CheckinScreen(
    modifier:Modifier  = Modifier
) {

}

@Preview
@Composable
fun CheckinScreenPreview() {
    HFNCheckinsTheme {
        Scaffold {
            CheckinScreen(
                modifier = Modifier.padding(it)
            )
        }

    }
}
