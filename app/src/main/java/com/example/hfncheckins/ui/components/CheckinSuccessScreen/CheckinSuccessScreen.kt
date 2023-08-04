package com.example.hfncheckins.ui.components.CheckinSuccessScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hfncheckins.R
import com.example.hfncheckins.ui.theme.HFNCheckinsTheme

@Composable
fun CheckinSuccessScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.baseline_arrow_drop_up_24
            ),
            modifier = Modifier.size(300.dp),
            contentDescription = "Checkin success",
            tint = Color(0xFF4BB543)
        )
    }
}

@Preview
@Composable
fun CheckinSuccessScreenPreview() {
    HFNCheckinsTheme {
        Scaffold {
            CheckinSuccessScreen(
                modifier = Modifier.padding(it)
            )
        }
    }
}