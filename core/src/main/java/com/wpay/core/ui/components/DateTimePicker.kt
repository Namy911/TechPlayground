package com.wpay.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wpay.core.ui.theme.backgroundColor
import java.util.Calendar
import java.util.Locale

@Composable
fun DateTimePicker() {
    val calendar = Calendar.getInstance()
    val selectedDate = remember { mutableStateOf("") }
    val showDatePicker = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.background(
            color = backgroundColor,
            shape = RoundedCornerShape(16.dp)
        )
    ) {
        CustomDialogField()

        if (showDatePicker.value) {
            DatePicker(
                onDateSelected = { day, month, year ->
                    selectedDate.value = "$day/${month + 1}/$year"
                    showDatePicker.value = false
                },
                currentYear = calendar.get(Calendar.YEAR),
                currentMonth = calendar.get(Calendar.MONTH),
                currentDay = calendar.get(Calendar.DAY_OF_MONTH),
                selectedDate = selectedDate.value
            )
        }
    }
}

@Composable
fun CustomDialogField() {
    val calendar = Calendar.getInstance()
    val selectedDate = remember { mutableStateOf("") }
    val showDatePicker = remember { mutableStateOf(false) }

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
                .background(Color.White, shape = RoundedCornerShape(8.dp))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                BasicTextField(
                    value = selectedDate.value,
                    onValueChange = {},
                    readOnly = true,
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp, top = 8.dp)
                )

                IconButton(
                    onClick = { showDatePicker.value = !showDatePicker.value },
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(Icons.Default.DateRange, contentDescription = "Open Calendar")
                }
            }
        }

        if (showDatePicker.value) {
            DatePicker(
                onDateSelected = { day, month, year ->
                    selectedDate.value = "$day/${month + 1}/$year"
                    showDatePicker.value = false
                },
                currentYear = calendar.get(Calendar.YEAR),
                currentMonth = calendar.get(Calendar.MONTH),
                currentDay = calendar.get(Calendar.DAY_OF_MONTH),
                selectedDate = selectedDate.value
            )
        }
    }
}

@Composable
fun DatePicker(
    onDateSelected: (Int, Int, Int) -> Unit,
    currentYear: Int,
    currentMonth: Int,
    currentDay: Int,
    selectedDate: String,
) {
    var year by remember { mutableStateOf(currentYear) }
    var month by remember { mutableStateOf(currentMonth) }
    var selectedDay by remember { mutableStateOf(currentDay) }
    var showMonthDropdown by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month)

    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK)
    val monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
    val weekdays = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

    val daysList = mutableListOf<String>()
    for (i in 1 until firstDayOfMonth) {
        daysList.add("")
    }
    for (i in 1..daysInMonth) {
        daysList.add(i.toString())
    }

    val selectedDateParts = selectedDate.split("/")
    if (selectedDateParts.size == 3) {
        val selectedDayInput = selectedDateParts[0].toIntOrNull()
        val selectedMonthInput = selectedDateParts[1].toIntOrNull()?.minus(1)
        val selectedYearInput = selectedDateParts[2].toIntOrNull()

        if (selectedDayInput != null && selectedMonthInput != null && selectedYearInput != null) {
            if (selectedYearInput == year && selectedMonthInput == month) {
                selectedDay = selectedDayInput
            }
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                if (month == 0) {
                    month = 11
                    year -= 1
                } else {
                    month -= 1
                }
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Previous Month")
            }

            Box(
                modifier = Modifier

                    .align(Alignment.CenterVertically)
            ) {
                Row {
                    Text(
                        text = "$monthName $year",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Arrow pointing down",
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { showMonthDropdown = !showMonthDropdown }
                    )
                }
            }

            IconButton(onClick = {
                if (month == 11) {
                    month = 0
                    year += 1
                } else {
                    month += 1
                }
            }) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Next Month")
            }
        }

        DropdownMenu(
            expanded = showMonthDropdown,
            onDismissRequest = { showMonthDropdown = false }
        ) {
            val months = listOf(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
            )

            months.forEachIndexed { index, monthName ->
                DropdownMenuItem(
                    onClick = {
                        month = index
                        showMonthDropdown = false
                    },
                    text = { Text(text = monthName) },
                    contentPadding = PaddingValues(16.dp),
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            weekdays.forEach {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(daysList) { day ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            if (day.isNotEmpty()) {
                                val dayInt = day.toInt()
                                onDateSelected(dayInt, month, year)
                            }
                        }
                ) {
                    if (day.isNotEmpty()) {
                        Text(
                            text = day,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.align(Alignment.Center),
                            color = if (day.toInt() == selectedDay) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}