package com.sopt25.hackerthon.nagaraguel.ui.main

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sopt25.hackerthon.nagaraguel.R
import com.sopt25.hackerthon.nagaraguel.ui.bottom_nav.HomeFragment
import com.sopt25.hackerthon.nagaraguel.ui.bottom_nav.MyTotalFragment
import com.sopt25.hackerthon.nagaraguel.ui.bottom_nav.RankFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    var id_string = ""
    lateinit var viewPager_main : ViewPager
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.navigation_home ->{
                setThisPage(p0,1)
            }
            R.id.navigation_my_total -> {
                setThisPage(p0,0)
            }
            R.id.navigation_rank -> {
                setThisPage(p0,2)
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        val intent = getIntent()
        id_string = intent.getStringExtra("userID")
        viewPager_main = findViewById(R.id.fragment_container)
        setUpViewPager(viewPager_main)
        bottom_navigation_view.setOnNavigationItemSelectedListener(this)
        val home_select = bottom_navigation_view.menu.getItem(1)
        onNavigationItemSelected(home_select)
        home_select.setChecked(true)
        viewPager_main?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                val menu_item = bottom_navigation_view.menu.getItem(position)
                setThisPage(menu_item,position)
            }

        })
    }

    private fun setThisPage(menuItem: MenuItem,postition:Int){
        menuItem.setChecked(true)
        viewPager_main.setCurrentItem(postition)
    }

    private fun setUpViewPager(viewPager : ViewPager){
        val adapter = MyPagerAdapter(supportFragmentManager)
        adapter.addFragment(MyTotalFragment())
        adapter.addFragment(HomeFragment(id_string))
        adapter.addFragment(RankFragment())
        viewPager.adapter=adapter
    }

}

