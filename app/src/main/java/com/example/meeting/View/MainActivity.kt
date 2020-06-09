package com.example.meeting.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meeting.Model.DataModel
import com.example.meeting.R
import com.example.meeting.RecycleView.DataAdapter
import com.example.meeting.Retrofit.CallApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var arrayList: ArrayList<DataModel>? = null
    var dataAdapter: DataAdapter? = null
    var listCall: Call<List<DataModel>>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView!!.setHasFixedSize(true)

        intent = Intent(this, DetailActivity::class.java)

        getData()

    }

    private fun getClick() {
        dataAdapter!!.setItemClickListener(object :
            DataAdapter.ItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("test", "onItemClick: index = $position")
                val location: String? = arrayList!![position].Location
                val subject: String? = arrayList!![position].Subject
                val intStatus: Int = arrayList!![position].StatusChecked
                val body: String? = arrayList!![position].Body
                val date: String? = arrayList!![position].Start
                val startTime: String? = arrayList!![position].Start
                val endTime: String? = arrayList!![position].End
                val status = Integer.toString(intStatus)
                intent.putExtra("Location", location)
                intent.putExtra("Subject", subject)
                intent.putExtra("Status", status)
                intent.putExtra("Body", body)
                intent.putExtra("Date", date)
                intent.putExtra("StartTime", startTime)
                intent.putExtra("EndTime", endTime)
                startActivity(intent)
            }
        })
    }

    private fun getData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(CallApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val callApi = retrofit.create(CallApi::class.java)
        val call = callApi.getDatas()
        call.enqueue(object : Callback<List<DataModel>> {
            override fun onResponse(call: Call<List<DataModel>>, response: Response<List<DataModel>>) {
                arrayList = response.body() as ArrayList<DataModel>?
                dataAdapter = DataAdapter(
                    this@MainActivity,
                    arrayList!!
                )
                getClick()
                recyclerView!!.adapter = dataAdapter
            }

            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}


