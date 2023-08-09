package com.example.hfncheckins.ui.components.CheckinSuccessScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hfncheckins.R
import com.example.hfncheckins.ui.hfnTheme.HFNTheme
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@Composable
fun CheckinSuccessScreen(
    modifier: Modifier = Modifier,
    onClickReturnToMain: () -> Unit
) {
    val party = Party(
        speed = 0f,
        maxSpeed = 30f,
        damping = 0.9f,
        spread = 360,
        colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
        position = Position.Relative(0.5, 0.3),
        emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100)
    )
    KonfettiView(
        modifier = Modifier.fillMaxSize(),
        parties = listOf(party),
    )
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            12.dp,
            Alignment.CenterVertically
        )
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.baseline_check_circle_24
            ),
            modifier = Modifier.size(150.dp),
            contentDescription = "Checkin success",
            tint = Color(0xFF4BB543)
        )
        Button(onClick = onClickReturnToMain) {
            Text(
                text="RETURN TO MAIN SCREEN",
            )
        }
    }
}

@Preview
@Composable
fun CheckinSuccessScreenPreview() {
    HFNTheme() {
        Scaffold {
            CheckinSuccessScreen(
                modifier = Modifier.padding(it),
                onClickReturnToMain = {

                }
            )
        }
    }
}