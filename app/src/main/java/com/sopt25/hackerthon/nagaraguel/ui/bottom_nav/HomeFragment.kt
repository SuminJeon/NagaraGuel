package com.sopt25.hackerthon.nagaraguel.ui.bottom_nav

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.sopt25.hackerthon.nagaraguel.R
import com.sopt25.hackerthon.nagaraguel.StepCheckService
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.toast


// PlayingReceiver에서 사용
lateinit var serviceData: String
lateinit var txtCount: TextView

class HomeFragment : Fragment() {

    lateinit var manboService: Intent
    lateinit var receiver: BroadcastReceiver

    var flag: Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v: View = inflater.inflate(R.layout.fragment_home, container, false)

        txtCount = v.findViewById(R.id.tv_walkingCount)
        return v


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        manboService = Intent(activity, StepCheckService::class.java)
        receiver = PlayingReceiver()

        btn_start.setOnClickListener {
            if (flag) {
                try {
                    var mainFilter: IntentFilter = IntentFilter("manbo")
                    activity?.registerReceiver(receiver, mainFilter)
                    activity?.startService(manboService)

                } catch (e: Exception) {
                    Log.i("error", "error")
                    toast("에러")
                }
            } else {
                btn_start.text = "Go!!"
                try {
                    activity?.unregisterReceiver(receiver)
                    activity?.stopService(manboService)
                } catch (e: Exception) {
                    Log.i("error", "error")
                    toast("에러")
                }
            }

            flag = !flag
        }
    }

    class PlayingReceiver: BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            Log.i("PlayingReceiver", "IN")
            serviceData = p1!!.getStringExtra("stepService")
            txtCount.text = serviceData
        }
    }

}
