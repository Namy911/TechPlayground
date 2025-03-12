package com.wpay.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wpay.common.theme.primaryTextColor
import com.wpay.common.R.drawable


@Composable
fun MedicalCardItem(user: User, modifier: Modifier) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier
            .fillMaxWidth()
            .height(104.dp)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(drawable.calendar_24),
                contentDescription = "User Icon",
                modifier = modifier.size(40.dp),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 32.dp),
            ) {
                Text(
                    text = "${user.name} ${user.surname}",
                    style = MaterialTheme.typography.titleMedium,
                    color = primaryTextColor,
                    textAlign = TextAlign.Start,
                )
                Text(
                    text = "${user.name} ${user.surname}",
                    lineHeight = 22.sp,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Start,
                )
            }

            Image(
                painter = painterResource( drawable.navigate_next),
                contentDescription = "Arrow",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

data class User(val name: String, val surname: String)

val sampleUsers = listOf(
    User("John", "Doe"),
    User("Jane", "Smith"),
    User("Alice", "Johnson"),
    User("Bob", "Brown"),
    User("Bob", "Brown"),
)
