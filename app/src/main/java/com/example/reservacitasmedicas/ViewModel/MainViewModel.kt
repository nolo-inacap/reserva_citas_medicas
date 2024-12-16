package com.example.reservacitasmedicas.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.reservacitasmedicas.Model.AppointmentModel
import com.example.reservacitasmedicas.Model.DoctorsModel
import com.example.reservacitasmedicas.Repository.MainRepository

class MainViewModel():ViewModel() {
    private val repository=MainRepository()
    fun loadDoctors():LiveData<MutableList<DoctorsModel>>{
        return repository.load()
    }

}