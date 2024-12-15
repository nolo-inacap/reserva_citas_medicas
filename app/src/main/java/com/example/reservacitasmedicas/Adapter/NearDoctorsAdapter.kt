package com.example.reservacitasmedicas.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reservacitasmedicas.Model.DoctorsModel
import com.example.reservacitasmedicas.databinding.ViewholderNearbyDoctorBinding

class NearDoctorsAdapter(val items:MutableList<DoctorsModel>):RecyclerView.Adapter<NearDoctorsAdapter.Viewholder>(){

    private var context: Context? =null

    class Viewholder(val binding:ViewholderNearbyDoctorBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NearDoctorsAdapter.Viewholder {
        context = parent.context
        return Viewholder(
            ViewholderNearbyDoctorBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NearDoctorsAdapter.Viewholder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}