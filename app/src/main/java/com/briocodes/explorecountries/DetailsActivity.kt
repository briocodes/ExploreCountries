package com.briocodes.explorecountries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2

class DetailsActivity : AppCompatActivity() {
    private lateinit var dotIndicator1:ImageView
    private lateinit var dotIndicator2:ImageView
    private lateinit var dotIndicator3:ImageView
    private lateinit var viewPager2: ViewPager2
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var navigateBackButton: ImageView

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