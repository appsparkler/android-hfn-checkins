package com.appsparkler.gsm.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.appsparkler.hfncheckins.R

@Composable
fun GSMLogo(
  modifier: Modifier = Modifier
) {
  Image(
    modifier = modifier
      .fillMaxWidth(),
    painter = painterResource(id = R.drawable.image),
    contentDescription = null,
    contentScale = ContentScale.Crop
  )
}