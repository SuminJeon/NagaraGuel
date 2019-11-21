package com.sopt25.hackerthon.nagaraguel.ui.bottom_nav

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pickcocast.loginandboard.network.DothomeServiceImpl

import com.sopt25.hackerthon.nagaraguel.R
import com.sopt25.hackerthon.nagaraguel.StepCheckService
import com.sopt25.hackerthon.nagaraguel.data.GetMandarineData
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// PlayingReceiver에서 사용
var serviceData: Int = 0
var lastCount: Int = 0
lateinit var txtCount: TextView
lateinit var txtLastCount: TextView
val dothomeServiceImpl = DothomeServiceImpl
class HomeFragment(id_string : String) : Fragment() {
    lateinit var manboService: Intent
    lateinit var receiver: BroadcastReceiver
    val id_value = id_string

    var flag: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v: View = inflater.inflate(R.layout.fragment_home, container, false)

        txtCount = v.findViewById(R.id.tv_walkingCount)
        return v


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        manboService = Intent(activity, StepCheckService::class.java)
        manboService.putExtra("id_string",id_value)
        receiver = PlayingReceiver(id_value)

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

}
class PlayingReceiver(id_string: String) : BroadcastReceiver() {
    var id_value = id_string
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.i("PlayingReceiver", "IN")
        serviceData = p1!!.getIntExtra("stepService", 0)
        lastCount = p1!!.getIntExtra("lastService", 0)

        Log.i("lastCount", lastCount.toString())
        txtCount.text = serviceData.toString()
        if(serviceData==300){

            val dothomeService = dothomeServiceImpl.service.requestPlusMandarine(id_value,id_value)
            dothomeService.enqueue(object : Callback<GetMandarineData> {
                override fun onFailure(call: Call<GetMandarineData>, t: Throwable) {
                    Log.d("hj","error : $t")
                }
                override fun onResponse(call: Call<GetMandarineData>, response: Response<GetMandarineData>) {
                    if(response.isSuccessful){
                        if(response.body()!!.success){
                            //받은 귤 개수 데이터 처리

                        }else{
                            //데이터 받는데 실패함.
                        }
                    }
                }
            })
        }
        // txtLastCount.text = lastCount.toString()
        // txtLastCount.text = lastCount.toString()

    }
}