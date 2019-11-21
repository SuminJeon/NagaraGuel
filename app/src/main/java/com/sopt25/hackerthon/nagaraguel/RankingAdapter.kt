package com.sopt25.hackerthon.nagaraguel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sopt25.hackerthon.nagaraguel.data.rvItem

class RankingAdapter (var context: Context) : RecyclerView.Adapter<RankingViewHolder>(){
    var dataList = arrayListOf<rvItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val view: View = LayoutInflater
            .from(context)
            .inflate(R.layout.rv_item_ranking, parent, false)
        return RankingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}

class RankingViewHolder(v: View) : RecyclerView.ViewHolder(v){
    val tvRanking: TextView = v.findViewById(R.id.tv_ranking)
    val tvName: TextView = v.findViewById(R.id.tv_name)
    val tvMandarine: TextView = v.findViewById(R.id.tv_mandarineCount)

    fun bind(data: rvItem){
        tvRanking.text = data.ranking.toString()
        tvName.text = data.name
        tvMandarine.text = data.mandarine.toString()
    }
}