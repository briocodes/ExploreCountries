package com.briocodes.explorecountries

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log.d
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.briocodes.explorecountries.adapters.CountryAdapter
import com.briocodes.explorecountries.dataclass.CountryDataItem
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var langButton: Button
    private lateinit var filterButton: Button
    lateinit var countryAdapter: CountryAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager=linearLayoutManager
        recyclerView.setHasFixedSize(true)

        getCountryData()
        langButton = findViewById(R.id.language_btn)
        filterButton = findViewById(R.id.filter_btn)

        langButton.setOnClickListener(View.OnClickListener { v ->
            showSheetDialog()
        })

        filterButton.setOnClickListener(View.OnClickListener { v ->
            showFilterDialog()
        })

    }

    private fun getCountryData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<List<CountryDataItem>?> {
            override fun onResponse(
                call: Call<List<CountryDataItem>?>,
                response: Response<List<CountryDataItem>?>
            ) {
                val responseBody = response.body()!!

                val sortedResponseBody = responseBody.sortedBy { it.name.common }

                countryAdapter = CountryAdapter(baseContext,sortedResponseBody)
                countryAdapter.notifyDataSetChanged()
                recyclerView.adapter=countryAdapter

            }

            override fun onFailure(call: Call<List<CountryDataItem>?>, t: Throwable) {
                d("MainActivity","onFailure: " + t.message)
            }
        })
    }

    private fun showFilterDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_filter_layout)

        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog.window!!.setGravity(Gravity.BOTTOM)
    }

    private fun showSheetDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet_layout)

        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog.window!!.setGravity(Gravity.BOTTOM)
    }

    companion object {
        const val BASE_URL = "https://restcountries.com/v3.1/"
    }
}