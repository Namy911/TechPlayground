package com.wpay.medibook.ui.screens

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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.util.Calendar
import java.util.Locale

@Composable
fun DateTimePicker() {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // States for date and time
    val selectedDate = remember { mutableStateOf("") }

    // State for controlling the visibility of the DatePicker
    val showDatePicker = remember { mutableStateOf(false) }

    // UI
    Column(modifier = Modifier.padding(16.dp)) {
        // Date input field
        TextField(
            value = selectedDate.value,
            onValueChange = { selectedDate.value = it },
            label = { Text("Select Date") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker.value = !showDatePicker.value }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Open Calendar")
                }
            }
        )

        // Show the calendar below the date field
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
    selectedDate: String
) {
    var year by remember { mutableStateOf(currentYear) }
    var month by remember { mutableStateOf(currentMonth) }
    var selectedDay by remember { mutableStateOf(currentDay) }
    var showMonthDropdown by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month)

    // Get the number of days in the current month
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    // Get the weekday of the 1st day of the current month
    val firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK)

    // Get the month name (e.g., "January")
    val monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())

    // Get the weekday names (Mon, Tue, Wed, etc.)
    val weekdays = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

    // Create a list of days for the calendar grid
    val daysList = mutableListOf<String>()
    for (i in 1 until firstDayOfMonth) {
        daysList.add("")
    }
    for (i in 1..daysInMonth) {
        daysList.add(i.toString())
    }

    // Check if there is a selected date from the input field
    val selectedDateParts = selectedDate.split("/")
    if (selectedDateParts.size == 3) {
        val selectedDayInput = selectedDateParts[0].toIntOrNull()
        val selectedMonthInput = selectedDateParts[1].toIntOrNull()?.minus(1)
        val selectedYearInput = selectedDateParts[2].toIntOrNull()

        if (selectedDayInput != null && selectedMonthInput != null && selectedYearInput != null) {
            // If the date from input field exists, set it as the selected date
            if (selectedYearInput == year && selectedMonthInput == month) {
                selectedDay = selectedDayInput
            }
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        // Display the month name and year with navigation arrows
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                // Decrease month, handle year change
                if (month == 0) {
                    month = 11
                    year -= 1
                } else {
                    month -= 1
                }
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Previous Month")
            }

            // Month Name with dropdown on click
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
                        modifier = Modifier.size(32.dp) // Adjust size as needed
                            .clickable { showMonthDropdown = !showMonthDropdown }
                    )
                }
            }

            IconButton(onClick = {
                // Increase month, handle year change
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

        // Display dropdown for month selection
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

        // Display the days of the week (Sun, Mon, Tue, etc.)
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

        // Display the days in a grid (4 weeks, each row is a week)
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
                            // Only select valid days (not empty)
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