package com.wpay.medibook.ui.screens.consultation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.wpay.common.R

@Composable
fun AppointmentCard(
    text: String,
    @DrawableRes imageId: Int,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            ) {
            Image(
                painter = painterResource(imageId),
                contentDescription = null,
                alignment = Alignment.CenterStart,
                modifier = Modifier
                    .width(32.dp)
                    .height(48.dp)
                    .weight(1f)
            )

            Image(
                painter = painterResource(R.drawable.arrow_forward_24px),
                contentDescription = null,
                alignment = Alignment.CenterEnd,
                modifier = Modifier
                    .size(32.dp)
                    .weight(1f)
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}
