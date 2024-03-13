package com.appsparkler.gsm.features.HomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appsparkler.gsm.features.GSMLogo
import com.appsparkler.gsm.features.TextTitleLarge

@Composable
fun HomeScreenContent(
  modifier: Modifier = Modifier,
  name: String = "",
  mobileNo: String = "",
  email: String = "",
  organization: String = "",
  checkinButtonEnabled: Boolean = false,
  isDirtyMobileNo: Boolean = false,
  onCheckin: () -> Unit = {},
  onChangeName: (String) -> Unit = {},
  onChangeMobileNo: (String) -> Unit = {},
  onChangeEmail: (String) -> Unit = {},
  onChangeOrganization: (String) -> Unit = {},
) {
  ElevatedCard(
    modifier = modifier
  ) {
    Column(
      modifier = Modifier
        .padding(12.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      GSMLogo()
      TextTitleLarge(
        text = "Global Spirituality Mahotsav"
      )
      TextField(
        value = name,
        supportingText = {
          Text(text = "Please enter name as displayed on ID card")
        },
        label = {
          Text(text = "Name*")
        },
        onValueChange = onChangeName
      )
      TextField(
        value = if(mobileNo.isEmpty() && !isDirtyMobileNo) "+91" else mobileNo,
        onValueChange = onChangeMobileNo,
        label = {
          Text(text = "Mobile #")
        }
      )
      TextField(
        value = email,
        onValueChange = onChangeEmail,
        label = {
          Text(text = "Email")
        }
      )
      TextField(
        value = organization,
        onValueChange = onChangeOrganization,
        label = {
          Text(text = "Organization")
        }
      )
      ElevatedButton(
        enabled = checkinButtonEnabled,
        colors = ButtonDefaults.elevatedButtonColors(
          containerColor = MaterialTheme.colorScheme.primary,
          contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        onClick = onCheckin
      ) {
        Text(text = "Checkin")
      }
    }
  }
}

@Preview
@Composable
fun HomeScreenContentPreview() {
  var isDirtyMobileNo by remember {
    mutableStateOf(false)
  }
  var mobileNo by remember{
    mutableStateOf("")
  }
  HomeScreenContent(
    modifier = Modifier.padding(12.dp),
    name = "Abhishek",
    mobileNo = mobileNo,
    email = "abhishek@me.com",
    organization = "Global",
    checkinButtonEnabled = true,
    isDirtyMobileNo = isDirtyMobileNo,
    onChangeMobileNo = {
      mobileNo = it
      isDirtyMobileNo = true
    }
  )
}