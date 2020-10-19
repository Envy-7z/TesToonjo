package com.tif.testoonjo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.tif.testoonjo.activity.add.AddActivitys
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        tv_toolbar_title.text = "List Contact"

        viewPager = findViewById(R.id.viewpager_main)
        tabs = findViewById(R.id.tabs_main)
        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)
        floating_action_button.setOnClickListener {
            startActivity(Intent(this@MainActivity , AddActivitys::class.java))
        }
    }
}