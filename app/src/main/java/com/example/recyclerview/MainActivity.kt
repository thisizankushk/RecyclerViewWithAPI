package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getdata()

    }




    private fun getdata() {
        val data: Call<Info> = dataservice.datainstance.getstatedata()
        data.enqueue(object : Callback<Info> {
            override fun onResponse(call: Call<Info>?, response: Response<Info>?) {
                val data = response?.body()
                if (data != null) {



                    layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerview.layoutManager = layoutManager

                    adapter = RecyclerAdapter(this@MainActivity, data.data)
                    recyclerview.adapter = adapter



                    Log.d("DATAISHERE", data.toString())
                }
            }

            override fun onFailure(call: Call<Info>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "Error fetching data", Toast.LENGTH_SHORT).show()
                Log.d("NOTHERE", data.toString())
            }
        })

    }

}