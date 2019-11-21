package com.sopt25.hackerthon.nagaraguel.ui.bottom_nav

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import com.sopt25.hackerthon.nagaraguel.R
import com.sopt25.hackerthon.nagaraguel.ui.main.MainActivity
import com.sopt25.hackerthon.nagaraguel.ui.main.MyPagerAdapter
import com.sopt25.hackerthon.nagaraguel.ui.main.RankAdapter
import kotlinx.android.synthetic.main.fragment_rank.*
import org.jetbrains.anko.support.v4.startActivity

class RankFragment : Fragment() {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rank, container, false)


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_back.setOnClickListener {
            startActivity<MainActivity>()
        }


    }

}
