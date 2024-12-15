package com.example.reservacitasmedicas.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.reservacitasmedicas.Model.DoctorsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private val firebaseDatabase=FirebaseDatabase.getInstance()

    fun  load():LiveData<MutableList<DoctorsModel>>{
        val listData=MutableLiveData<MutableList<DoctorsModel>>()
        val ref=firebaseDatabase.getReference("Doctors")

        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<DoctorsModel>()
                    for(childSnapshot in snapshot.children){
                        val item=childSnapshot.getValue(DoctorsModel::class.java)
                        item?.let {lists.add(it)}
                    }

                // Verificar si los datos fueron cargados correctamente
                Log.d("MainRepository", "Doctores cargados desde Firebase: ${lists.size} doctores")

                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainRepository", "Error al cargar los datos: ${error.message}")
            }
        })

        return listData

    }
}