package com.briocodes.explorecountries

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log.d
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
    lateinit var progressBar:ProgressBar
    lateinit var darkLightModeToggle:ImageView
    lateinit var countryList: List<CountryDataItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        countryList = mutableListOf()
        recyclerView = findViewById(R.id.recyclerview)
        darkLightModeToggle = findViewById(R.id.night_mode_toggle)
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        darkLightModeToggle.setOnClickListener(View.OnClickListener { v ->
            Toast.makeText(this,"Light mode is activated",Toast.LENGTH_SHORT).show()
        })

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
                progressBar.visibility = View.GONE
                val responseBody = response.body()!!

                val sortedResponseBody = responseBody.sortedBy { it.name.common }

                countryAdapter = CountryAdapter(baseContext,sortedResponseBody)
                countryAdapter.notifyDataSetChanged()
                recyclerView.adapter=countryAdapter
                countryAdapter.setOnCountryItemClickedListener(object : CountryAdapter.onCountryItemClickedListener {
                    override fun onCountryItemClick(position: Int) {
                        val intent = Intent(this@MainActivity,DetailsActivity::class.java)
                        intent.putExtra("NAME",sortedResponseBody.get(position).name.common)
                        intent.putExtra("POPULATION", sortedResponseBody.get(position).population.toString())
                        intent.putExtra("REGION",sortedResponseBody.get(position).region)
                        intent.putExtra("DRIVING_SIDE",sortedResponseBody.get(position).car.side)
                        intent.putExtra("CAPITAL",
                            sortedResponseBody.get(position).capital?.first().toString())
                        intent.putExtra("TIMEZONE",sortedResponseBody.get(position).timezones.first().toString())
                        intent.putExtra("AREA",sortedResponseBody.get(position).area.toString())
                        intent.putExtra("DIALING_CODE",sortedResponseBody.get(position).idd.root)
                        intent.putExtra("DIALING_CODE_SUFFIX",sortedResponseBody.get(position).idd.suffixes.first().toString())
                        intent.putExtra("INDEPENDENCE",sortedResponseBody.get(position).independent.toString())
                        intent.putExtra("FLAG_URL",sortedResponseBody.get(position).flags.png)
                        intent.putExtra("STATUS",sortedResponseBody.get(position).status)
                        intent.putExtra("START_OF_WEEK", sortedResponseBody.get(position).startOfWeek)
                        intent.putExtra("GEO_LOCATION",sortedResponseBody.get(position).latlng.first().toString())
                        intent.putExtra("UN_MEMBER",sortedResponseBody.get(position).unMember.toString())
                        intent.putExtra("LANDLOCKED",sortedResponseBody.get(position).landlocked.toString())
                        intent.putExtra("CIOC",sortedResponseBody.get(position).cioc)
                        startActivity(intent)

                    }
                })

            }

            override fun onFailure(call: Call<List<CountryDataItem>?>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity,"An error occurred: " + t.message,Toast.LENGTH_SHORT).show()
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