package com.android.goally.ui.reminder

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.android.goally.R
import com.android.goally.data.model.api.response.reminder.Routines
import com.android.goally.data.model.api.response.reminder.Schedule
import com.android.goally.util.poppins
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class ReminderDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the routine object passed via the Intent inside onCreate
        val routine = intent.getSerializableExtra("routine") as? Routines


        setContent {
            if (routine != null) {
                DetailsScreen(routine)
            }
        }

    }
}


@Composable
fun DetailsScreen(routines: Routines) {

    val currentDayOfWeek = SimpleDateFormat("EEE", Locale.getDefault()).format(Date())

    val scheduleTime = getScheduleTimeForCurrentDay(routines.schedule, currentDayOfWeek)


    // Variable to hold the current time
    var currentTime by remember { mutableStateOf("") }
    var remainingTime by remember { mutableStateOf("") }


    // This will update the current time every second
    LaunchedEffect(Unit) {
        while (true) {

            currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
            remainingTime = calculateRemainingTime(scheduleTime, currentTime)
            delay(1000L)
        }
    }


    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.ic_bg_pattern),
                contentScale = ContentScale.Crop
            )
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 30.dp, top = 20.dp),
        ) {
            Text(
                text = routines.name.toString(),
                fontFamily = poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            Image(
                modifier = Modifier.padding(start = 10.dp),
                painter = painterResource(id = R.drawable.ic_voice_btn),
                contentDescription = null
            )

        }

        Row(
            modifier = Modifier.fillMaxHeight()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .shadow(
                            8.dp,
                            RoundedCornerShape(16.dp)
                        )
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(routines.imgURL),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }


            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
                    .padding(10.dp),
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .shadow(8.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // First Row
                        Row(
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .weight(1f),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.padding(horizontal = 20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    modifier = Modifier.padding(top = 10.dp),
                                    text = stringResource(R.string.starting),
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp
                                )
                                Box(
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(70.dp)
                                        .paint(
                                            painterResource(id = R.drawable.ic_timer_bg),
                                            contentScale = ContentScale.FillBounds
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = scheduleTime
                                            ?: stringResource(R.string.Schedule_Time),
                                        fontFamily = poppins,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                            Column(
                                modifier = Modifier.padding(horizontal = 20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    modifier = Modifier.padding(top = 10.dp),
                                    text = stringResource(R.string.Now),
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp
                                )
                                Box(
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(70.dp)
                                        .paint(
                                            painterResource(id = R.drawable.ic_timer_bg),
                                            contentScale = ContentScale.FillBounds
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = currentTime,
                                        fontFamily = poppins,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }


                        Row(
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .weight(0.5f)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(50.dp)
                                    .paint(
                                        painterResource(id = R.drawable.ic_bg_green_bar),
                                        contentScale = ContentScale.Crop
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                // Centered text
                                Text(
                                    text = remainingTime,
                                    fontFamily = poppins,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White, // Make the text stand out on the background
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }

                        Row(modifier = Modifier.weight(1f)) {
                            Image(
                                modifier = Modifier.size(150.dp),
                                painter = painterResource(id = R.drawable.ok_btn),
                                contentDescription = null
                            )
                        }
                    }
                }
            }


        }


    }

}


fun calculateRemainingTime(scheduleTime: String?, currentTime: String): String {
    val timeFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
    val outputFormat = SimpleDateFormat("hh:mm", Locale.getDefault()) // For output in AM/PM format

    Log.d("Time Calculation", "Schedule Time: $scheduleTime, Current Time: $currentTime")

    if (scheduleTime.isNullOrEmpty()) {
        return "00:00"
    }

    try {
        // Parse the current time
        val currentDate = timeFormat.parse(currentTime) ?: return "00:00"

        // Create a calendar instance for the scheduled time
        val scheduledDate = timeFormat.parse(scheduleTime) ?: return "00:00"

        val calendar = Calendar.getInstance()
        calendar.time = scheduledDate // Set to the parsed scheduled time

        // If the scheduled time has already passed today, set it for tomorrow
        if (currentDate.after(calendar.time)) {
            calendar.add(Calendar.DAY_OF_YEAR, 1) // Move to the next day
        }

        // Calculate remaining milliseconds
        val remainingMillis = calendar.timeInMillis - currentDate.time

        // If the remainingMillis is negative, return "00:00" (shouldn't happen due to the above check)
        if (remainingMillis < 0) {
            return "00:00"
        }

        // Calculate remaining hours and minutes
        val remainingHours = (remainingMillis / (1000 * 60 * 60)) % 24 // Ensure hours are within 0-23
        val remainingMinutes = (remainingMillis / (1000 * 60)) % 60

        // Create a Calendar object to represent the remaining time
        val remainingCalendar = Calendar.getInstance()
        remainingCalendar.set(Calendar.HOUR_OF_DAY, remainingHours.toInt())
        remainingCalendar.set(Calendar.MINUTE, remainingMinutes.toInt())
        remainingCalendar.set(Calendar.SECOND, 0)

        // Format remaining time to AM/PM
        return outputFormat.format(remainingCalendar.time)

    } catch (e: Exception) {
        Log.e("Time Calculation Error", "Error parsing time: ${e.message}")
        return "00:00"
    }
}


// Function to get the scheduled time for the current day
fun getScheduleTimeForCurrentDay(schedule: Schedule?, currentDayOfWeek: String): String? {
    return when (currentDayOfWeek) {
        "Mon" -> schedule?.Mon
        "Tue" -> schedule?.Tue
        "Wed" -> schedule?.Wed
        "Thu" -> schedule?.Thu
        "Fri" -> schedule?.Fri
        "Sat" -> schedule?.Sat
        "Sun" -> schedule?.Sun
        else -> null
    }
}
