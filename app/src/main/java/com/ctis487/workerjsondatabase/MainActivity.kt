package com.ctis487.workerjsondatabase

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.animation.AlphaAnimation
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ctis487.workerjsondatabase.adapter.CustomRecyclerViewAdapter
import com.ctis487.workerjsondatabase.databinding.ActivityMainBinding
import com.ctis487.workerjsondatabase.model.Cars
import com.ctis487.workerjsondatabase.retrofit.ApiClient
import com.ctis487.workerjsondatabase.retrofit.ApiService
import com.ctis487.workerjsondatabase.util.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var apiService: ApiService
    lateinit var adapter: CustomRecyclerViewAdapter
    lateinit var bindingMain: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        ViewCompat.setOnApplyWindowInsetsListener(bindingMain.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        apiService = ApiClient.getClient().create(ApiService::class.java)
        var request = apiService.getCars()

        bindingMain.recyclerCars.layoutManager = LinearLayoutManager(this)
        adapter = CustomRecyclerViewAdapter(this)
        bindingMain.recyclerCars.adapter = adapter

        // Add click listener
        adapter.setOnItemClickListener { car ->
            val intent = Intent(this, CarDetailActivity::class.java)
            intent.putExtra("car_data", car)
            startActivity(intent)
        }

        Log.d("JSONARRAYPARSE", "Before Request")
        request.enqueue(object : Callback<List<Cars>> {
            override fun onResponse(call: Call<List<Cars>>, response: Response<List<Cars>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let { carsList ->
                        adapter.setCarList(carsList)
                    }
                } else {
                    Log.e("API Error", "Response not successful: ${response.code()}")
                    Log.e("API Error", "Error body: ${response.errorBody()?.string()}")
                    Toast.makeText(
                        this@MainActivity,
                        "Error loading data: ${response.code()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Cars>>, t: Throwable) {
                Log.e("API Error", "Network call failed", t)
                Toast.makeText(
                    this@MainActivity,
                    "Network error: ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }

        })

    }
}