package com.example.reservacitasmedicas.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.reservacitasmedicas.Model.AppointmentModel
import com.example.reservacitasmedicas.Model.DoctorsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore

class MainRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val firebaseDatabase=FirebaseDatabase.getInstance()

    fun  load():LiveData<MutableList<DoctorsModel>>{
        val listData=MutableLiveData<MutableList<DoctorsModel>>()
        val ref=firebaseDatabase.getReference("Doctors")
        //val ref = firestore.collection("Doctors")

        // Consulta a Firestore
        /*ref.get()
            .addOnSuccessListener { documents ->
                val lists = mutableListOf<DoctorsModel>()

                // Itera sobre los documentos obtenidos
                for (document in documents) {
                    val item = document.toObject(DoctorsModel::class.java) // Convierte el documento a un modelo
                    item?.let { lists.add(it) } // Agrega el doctor a la lista
                }

                // Log para verificar la cantidad de doctores cargados
                Log.d("MainRepository", "Doctores cargados desde Firestore: ${lists.size} doctores")

                // Actualiza el LiveData con la lista de doctores
                listData.value = lists
            }
            .addOnFailureListener { exception ->
                Log.e("MainRepository", "Error al cargar los datos: ${exception.message}")
            }

        return listData*/

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