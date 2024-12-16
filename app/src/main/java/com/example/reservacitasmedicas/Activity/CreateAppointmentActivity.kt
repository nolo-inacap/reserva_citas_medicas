package com.example.reservacitasmedicas.Activity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.reservacitasmedicas.R

class CreateAppointmentActivity : AppCompatActivity() {
    private lateinit var btnSelectDate: Button
    private lateinit var tvSelectedDate: TextView
    private val allowedDates = arrayOf(
        "2024-07-01", "2024-07-05", "2024-07-10", "2024-07-15", "2024-07-20"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_appointment)
        btnSelectDate = findViewById(R.id.btnSelectDate)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        btnSelectDate.setOnClickListener {
                showNumberPickerDialog()
            }



        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
    }
}