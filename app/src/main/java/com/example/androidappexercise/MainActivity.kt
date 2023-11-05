package com.example.androidappexercise

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var onOffButton: ImageView
    private lateinit var timeView: TextView
    private lateinit var dayView: TextView
    private lateinit var dateView: TextView
    private lateinit var weatherIcon: ImageView
    private lateinit var wifiIcon: ImageView
    private lateinit var screenView: View
    private var isDarkened = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onOffButton = findViewById(R.id.powerIcon)
        timeView = findViewById(R.id.timeView)
        dayView = findViewById(R.id.dayView)
        dateView = findViewById(R.id.dateView)
        weatherIcon = findViewById(R.id.weatherIcon)
        wifiIcon = findViewById(R.id.wifiIcon)
        screenView = findViewById(R.id.screenView)

        onOffButton.setOnClickListener {
            darkenScreen()
        }

        screenView.setOnClickListener {
            brightenScreen()
        }

        weatherIcon.setOnClickListener {
            showToast("Weather Icon Pressed")
        }

        wifiIcon.setOnClickListener {
            showToast("Wi-Fi Icon Pressed")
        }

        val handler = Handler(Looper.getMainLooper())
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())

        // Update time, day, and date in real-time
        handler.post(object : Runnable {
            override fun run() {
                val currentTime = System.currentTimeMillis()
                val date = Date(currentTime)
                val formattedDate = dateFormat.format(date)
                val formattedTime = timeFormat.format(date)
                val formattedDay = dayFormat.format(date)

                timeView.text = formattedTime
                dayView.text = formattedDay
                dateView.text = formattedDate

                handler.postDelayed(this, 1000)
            }
        })

        // Set initial background color
        updateBackgroundColor()
    }

    private fun darkenScreen() {
        isDarkened = true
        updateBackgroundColor()
    }

    private fun brightenScreen() {
        isDarkened = false
        updateBackgroundColor()
    }

    private fun updateBackgroundColor() {
        val colorResId = if (isDarkened) R.color.black else R.color.backgroundClr
        val color = resources.getColor(colorResId)
        screenView.setBackgroundColor(color)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
