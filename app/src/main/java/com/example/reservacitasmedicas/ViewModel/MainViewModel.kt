package com.example.reservacitasmedicas.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.reservacitasmedicas.Model.DoctorsModel
import com.example.reservacitasmedicas.Repository.MainRepository
import com.google.firebase.database.core.view.View

class MainViewModel():ViewModel() {
    private val repository=MainRepository()
    fun loadDoctors():LiveData<MutableList<DoctorsModel>>{
        return repository.load()
    }

}