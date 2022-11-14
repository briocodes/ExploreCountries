package com.briocodes.explorecountries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.briocodes.explorecountries.adapters.ViewPagerAdapter
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {
    private lateinit var dotIndicator1:ImageView
    private lateinit var dotIndicator2:ImageView
    private lateinit var dotIndicator3:ImageView
    private lateinit var viewPager2: ViewPager2
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var navigateBackButton: ImageView
    private lateinit var population: TextView
    private lateinit var region: TextView
    private lateinit var drivingSide: TextView
    private lateinit var capital: TextView
    private lateinit var timeZone: TextView
    private lateinit var area: TextView
    private lateinit var dialingCode:TextView
    private lateinit var independence: TextView
    private lateinit var name: TextView
    private lateinit var unMember: TextView
    private lateinit var geoLocation: TextView
    private lateinit var startOfWeek: TextView
    private lateinit var status: TextView
    private lateinit var landlocked: TextView
    private lateinit var cioc:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_details)
        viewPager2 = findViewById(R.id.detail_slider_viewpager)
        dotIndicator1 = findViewById(R.id.dot_indicator1)
        dotIndicator2 = findViewById(R.id.dot_indicator2)
        dotIndicator3 = findViewById(R.id.dot_indicator3)
        nextButton = findViewById(R.id.next_btn)
        prevButton = findViewById(R.id.previous_btn)
        navigateBackButton = findViewById(R.id.navigate_back_arrow)
        //Passed data from intent
        population = findViewById(R.id.population)
        region = findViewById(R.id.region)
        drivingSide = findViewById(R.id.driving)
        capital = findViewById(R.id.capital)
        timeZone = findViewById(R.id.timezone)
        area = findViewById(R.id.area)
        dialingCode = findViewById(R.id.dialing_code)
        independence = findViewById(R.id.independence)
        name = findViewById(R.id.name_textView)
        status = findViewById(R.id.status)
        startOfWeek = findViewById(R.id.start_of_week)
        geoLocation = findViewById(R.id.geo_location)
        unMember = findViewById(R.id.un_member)
        landlocked = findViewById(R.id.landlocked)
        cioc = findViewById(R.id.cioc)

        val _population = intent.getStringExtra("POPULATION")
        val _region = intent.getStringExtra("REGION")
        val _drivingSide = intent.getStringExtra("DRIVING_SIDE")
        val _capital = intent.getStringExtra("CAPITAL")
        val _timezone = intent.getStringExtra("TIMEZONE")
        val _area = intent.getStringExtra("AREA")
        val _dialingCode = intent.getStringExtra("DIALING_CODE")
        val _dialingCodeSuffix = intent.getStringExtra("DIALING_CODE_SUFFIX")
        val _independence = intent.getStringExtra("INDEPENDENCE")
        val _flagUrl = intent.getStringExtra("FLAG_URL")
        val _name = intent.getStringExtra("NAME")
        val _status = intent.getStringExtra("STATUS")
        val _startOfWeek = intent.getStringExtra("START_OF_WEEK")
        val _geoLocation = intent.getStringExtra("GEO_LOCATION")
        val _unMember = intent.getStringExtra("UN_MEMBER")
        val _landlocked = intent.getStringExtra("LANDLOCKED")
        val _cioc = intent.getStringExtra("CIOC")

        population.text = _population
        region.text = _region
        drivingSide.text = _drivingSide
        capital.text = _capital
        timeZone.text = _timezone
        area.text = _area
        dialingCode.text = _dialingCode + _dialingCodeSuffix
        independence.text = _independence
        name.text = _name
        status.text = _status
        startOfWeek.text = _startOfWeek
        geoLocation.text = _geoLocation
        unMember.text = _unMember
        landlocked.text = _landlocked
        cioc.text = _cioc

        var flagImageView: ImageView? = null
        if (flagImageView != null) {
            Glide.with(this).load(_flagUrl).into(flagImageView)
        }


        val images= listOf(R.drawable.ad_flag,R.drawable.ad_coat_of_arm,R.drawable.ad_map)
        val adapter = ViewPagerAdapter(images)
        viewPager2.adapter=adapter

        viewPager2.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun equals(other: Any?): Boolean {
                return super.equals(other)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                changeColor()
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                changeColor()
            }
        })

        nextButton.setOnClickListener(View.OnClickListener { v ->
            viewPager2.setCurrentItem(viewPager2.currentItem+1, true)
        })

        prevButton.setOnClickListener(View.OnClickListener {  v ->
            viewPager2.setCurrentItem(viewPager2.currentItem-1, true)
        })

        navigateBackButton.setOnClickListener(View.OnClickListener { v ->
            onBackPressed()
        })
    }

    private fun changeColor() {
        when(viewPager2.currentItem){
            0 -> {
                dotIndicator1.setBackgroundColor(applicationContext.resources.getColor(R.color.white))
                dotIndicator2.setBackgroundColor(applicationContext.resources.getColor(R.color.item_gray_night))
                dotIndicator3.setBackgroundColor(applicationContext.resources.getColor(R.color.item_gray_night))
            }
            1 -> {
                dotIndicator1.setBackgroundColor(applicationContext.resources.getColor(R.color.item_gray_night))
                dotIndicator2.setBackgroundColor(applicationContext.resources.getColor(R.color.white))
                dotIndicator3.setBackgroundColor(applicationContext.resources.getColor(R.color.item_gray_night))
            }
            2 -> {
                dotIndicator1.setBackgroundColor(applicationContext.resources.getColor(R.color.item_gray_night))
                dotIndicator2.setBackgroundColor(applicationContext.resources.getColor(R.color.item_gray_night))
                dotIndicator3.setBackgroundColor(applicationContext.resources.getColor(R.color.white))
            }
        }
    }
}