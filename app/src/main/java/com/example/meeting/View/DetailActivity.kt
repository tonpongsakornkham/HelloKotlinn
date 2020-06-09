package com.example.meeting.View

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.meeting.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    var tvRoom: TextView? = null
    var tvMeetRoom: TextView? = null
    var tvDate: TextView? = null
    var tvGetTimeStart: TextView? = null
    var tvNumber: TextView? = null
    var tvSetDes: TextView? = null
    var tvGetTimeEnd: TextView? = null

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)

        val bundle = intent.extras
        val room = bundle!!.getString("Location")
        val subject = bundle.getString("Subject")
        val status = bundle.getString("Status")
        val body = bundle.getString("Body")
        val date = bundle.getString("Date")
        val startTime = bundle.getString("StartTime")
        val endTime = bundle.getString("EndTime")

        val originalStringFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val desiredStringFormatTime = "HH:mm"
        val desiredStringFormatDate = "dd/MM/yyyy"

        val readingFormat =
            SimpleDateFormat(originalStringFormat)
        val outputFormatTime =
            SimpleDateFormat(desiredStringFormatTime)
        val outputFormatDate =
            SimpleDateFormat(desiredStringFormatDate)

        tvRoom = findViewById<TextView>(R.id.tvRoom)
        tvMeetRoom = findViewById<TextView>(R.id.tvMeetRoom)
        tvDate = findViewById<TextView>(R.id.tvDate)
        tvGetTimeStart = findViewById<TextView>(R.id.tvGetTimeStart)
        tvNumber = findViewById<TextView>(R.id.tvNumber)
        tvSetDes = findViewById<TextView>(R.id.tvSetDes)
        tvGetTimeEnd = findViewById<TextView>(R.id.tvGetTimeEnd)

        try {
            val dateStart = readingFormat.parse(date)
            val timeStart = readingFormat.parse(startTime)
            val timeEnd = readingFormat.parse(endTime)
            tvDate!!.text = outputFormatDate.format(dateStart)
            tvGetTimeStart!!.text = outputFormatTime.format(timeStart)
            tvGetTimeEnd!!.text = outputFormatTime.format(timeEnd)
        } catch (e: ParseException) {
            e.printStackTrace()
        }


        tvRoom!!.text = room
        tvMeetRoom!!.text = subject
        tvNumber!!.text = status
        tvSetDes!!.text = body
        //tvRoom.setText(room);
        //tvRoom.setText(room);
    }
}