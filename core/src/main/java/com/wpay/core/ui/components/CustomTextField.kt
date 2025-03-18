package com.wpay.core.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wpay.core.ui.theme.primaryColor

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isFormDisabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null,
    placeholder: String = label,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    val textDynamicColor by animateColorAsState(
        getTextColor(isError, isFocused, primaryColor)
    )
    val borderDynamicColor by animateColorAsState(
        getBorderColor(isError)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp )

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, borderDynamicColor, RoundedCornerShape(4.dp))
                .padding(16.dp)
        ) {
            if (value.isEmpty() && !isFocused) {
                Text(
                    text = placeholder,
                    color = Color.Gray
                )
            }

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                enabled = isFormDisabled,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = if (isError) Color.Red else primaryColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .onFocusEvent { isFocused = it.isFocused },
                visualTransformation = visualTransformation,
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                cursorBrush = SolidColor(primaryColor),
            )
        }
        Box(
            modifier = Modifier
                .offset(y = 60.dp)
                .animateContentSize()
        ) {
            if (isError && errorMessage != null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Error",
                        tint = Color.Red,
                        modifier = Modifier.size(20.dp)
                    )

                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 12.sp,
                        lineHeight = 13.sp,
                        maxLines = 2,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 12.dp)
        ) {
            Text(
                text = label,
                color = textDynamicColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .offset(y = (-26).dp)
                    .background(Color.White)
                    .padding(horizontal = 4.dp)
            )
        }
    }
}

fun getBorderColor(isError: Boolean) = when {
    isError -> Color.Red
    else -> Color.Black
}

fun getTextColor(isError: Boolean, isFocused: Boolean, primaryColor: Color) = when {
    isError -> Color.Red
    isFocused -> primaryColor
    else -> Color.Black
}