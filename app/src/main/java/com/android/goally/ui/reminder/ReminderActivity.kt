package com.android.goally.ui.reminder

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.android.goally.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.android.goally.data.model.api.response.reminder.ReminderResponse
import com.android.goally.data.model.api.response.reminder.Routines
import com.android.goally.ui.viewmodels.GeneralViewModel
import com.android.goally.util.poppins
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.*
import kotlin.collections.ArrayList
import me.vponomarenko.compose.shimmer.shimmer
import java.io.Serializable


@AndroidEntryPoint
class ReminderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReminderListScreen()
        }
    }

    @Composable
    fun ReminderListScreen() {
        // Loading state
        var isLoading by remember { mutableStateOf(true) }
        // Error state
        var errorMessage by remember { mutableStateOf<String?>(null) }

        // State to hold the selected reminder name
        var selectedReminderName by remember { mutableStateOf("") }


        val remindersViewModel: GeneralViewModel by viewModels()
        val remindersData by remindersViewModel.reminderState.collectAsState()

        LaunchedEffect(Unit) {
            remindersViewModel.getReminderList()
        }

        // Update loading state based on remindersData
        LaunchedEffect(remindersData) {
            if (remindersData != null) {
                isLoading = false
                if (remindersData is NetworkResponse.Success) {
                    (remindersData as NetworkResponse.Success<ReminderResponse>).body.routines?.let { reminders ->
                        if (reminders.isNotEmpty()) {
                            selectedReminderName = reminders[0].name.toString()
                        }
                    }
                }
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
            // Header
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .weight(0.4f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (isLoading) {
                    ShimmerEffectHeader()
                } else {

                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Reminders List",
                        fontFamily = poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DisplayCurrentTime()
                        Image(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            painter = painterResource(id = R.drawable.ic_settings_btn),
                            contentDescription = null
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .weight(1.3f)
                    .padding(vertical = 10.dp)
            ) {
                if (isLoading) {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        items(5) {  // Show 5 shimmer items
                            ShimmerEffect()
                        }
                    }
                } else {
                    remindersData?.let { response ->
                        when (response) {
                            is NetworkResponse.Success -> {
                                response.body.routines?.let { reminders ->
                                    ReminderDetailList(
                                        reminders,
                                        onReminderClick = { selectedName ->
                                            selectedReminderName = selectedName
                                        })
                                }
                            }

                            is NetworkResponse.NetworkError,
                            is NetworkResponse.ServerError,
                            is NetworkResponse.UnknownError -> {
                                errorMessage = "An error occurred"
                                // Display an error message if needed
                                Text(
                                    text = errorMessage ?: "Unknown Error",
                                    color = Color.Red,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (isLoading) {
                    ShimmerEffect(
                        modifier = Modifier.height(20.dp)
                    )
                } else {

                    Text(
                        selectedReminderName,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }

    @Composable
    fun ReminderDetailList(reminders: ArrayList<Routines>, onReminderClick: (String) -> Unit) {
        var selectedIndex by remember { mutableStateOf(0) }

        val context = LocalContext.current

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(reminders.size) { index ->
                val scale = if (index == selectedIndex) 1.2f else 1.0f

                Box(
                    modifier = Modifier
                        .size(150.dp * scale)
                        .clickable {
                            selectedIndex = index
                            onReminderClick(reminders[index].name.toString())
                        }
                ) {
                    val painter = rememberImagePainter(reminders[index].imgURL)

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
                            painter = painter,
                            contentDescription = "Image $index",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                    if (index == selectedIndex) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_play_btn),
                            contentDescription = "Play Button",
                            modifier = Modifier
                                .size(60.dp)
                                .align(Alignment.BottomCenter)
                                .offset(y = 20.dp)
                                .clickable {
                                    val intent = Intent(context, ReminderDetailActivity::class.java).apply {
                                        putExtra("routine", reminders[index] as Serializable)
                                    }
                                    context.startActivity(intent)
                                }
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun DisplayCurrentTime() {
        var currentTime by remember { mutableStateOf(getCurrentTime()) }

        LaunchedEffect(Unit) {
            while (true) {
                currentTime = getCurrentTime()
                delay(1000L)
            }
        }

        Text(
            text = currentTime,
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp
        )
    }

    fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return dateFormat.format(Date())
    }

    @Composable
    fun ShimmerEffect(modifier: Modifier = Modifier) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .shimmer()
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
        )
    }


    @Composable
    fun ShimmerEffectHeader() {

// Header
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Shimmer effect for the header text
            Text(
                modifier = Modifier
                    .padding(end = 50.dp)
                    .weight(0.2f)
                    .height(32.dp)
                    .shimmer()
                    .background(Color.LightGray, RoundedCornerShape(4.dp)),
                text = "",
                fontFamily = poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )

            // Shimmer effect for the current time
            DisplayShimmerTime()

            // Shimmer effect for the settings icon
            Box(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(24.dp)
                    .shimmer()
                    .background(Color.LightGray, RoundedCornerShape(4.dp)),
            )
        }
    }


    @Composable
    fun DisplayShimmerTime() {
        Text(
            modifier = Modifier
                .size(60.dp, 24.dp)
                .shimmer()
                .background(Color.LightGray, RoundedCornerShape(4.dp)),
            text = "",
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp
        )
    }

    @Preview
    @Composable
    fun PreviewReminder() {
        ReminderListScreen()
    }
}
