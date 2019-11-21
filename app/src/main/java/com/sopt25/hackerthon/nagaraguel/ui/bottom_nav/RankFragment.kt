package com.sopt25.hackerthon.nagaraguel.ui.bottom_nav

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import com.sopt25.hackerthon.nagaraguel.R
import com.sopt25.hackerthon.nagaraguel.RankingAdapter
import com.sopt25.hackerthon.nagaraguel.data.rvItem
import com.sopt25.hackerthon.nagaraguel.ui.main.MainActivity
import com.sopt25.hackerthon.nagaraguel.ui.main.MyPagerAdapter
import com.sopt25.hackerthon.nagaraguel.ui.main.RankAdapter
import kotlinx.android.synthetic.main.fragment_rank.*
import org.jetbrains.anko.support.v4.startActivity

class RankFragment(context: Context) : Fragment() {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    var ctx: Context = context

    lateinit var rankingAdapter: RankingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rank, container, false)

        //tv_first.bringToFront()
        //tv_firstNick.bringToFront()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_back.setOnClickListener {
            startActivity<MainActivity>()
        }

        init()
        makeDummy()


    }

    private fun init(){
        rankingAdapter = RankingAdapter(ctx)
        rv_ranking.adapter = rankingAdapter
        rv_ranking.layoutManager = LinearLayoutManager(this.activity)
    }

    private fun makeDummy(){
        rankingAdapter.dataList.add(rvItem(2, "가", 1))
        rankingAdapter.dataList.add(rvItem(3, "나", 2))
        rankingAdapter.dataList.add(rvItem(4, "다", 3))

    }

}
