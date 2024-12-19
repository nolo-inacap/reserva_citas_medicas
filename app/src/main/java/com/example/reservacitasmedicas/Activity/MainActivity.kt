package com.example.reservacitasmedicas.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reservacitasmedicas.Adapter.NearDoctorsAdapter
import com.example.reservacitasmedicas.ViewModel.MainViewModel
import com.example.reservacitasmedicas.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Verificar si el usuario está autenticado
        if (FirebaseAuth.getInstance().currentUser == null) {
            // Si el usuario no está autenticado, redirigir a la SplashActivity
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
            finish() // Finaliza la actividad actual para evitar que el usuario regrese a ella sin autenticarse
            return // Asegúrate de no continuar con la ejecución del código
        }

        // Si está autenticado, continuar con la inicialización de la actividad
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNearByDoctor()
        //addDoctorToFirestore()
    }

    private fun initNearByDoctor() {
        binding.apply {
            // Mostrar el ProgressBar mientras se cargan los datos
            progressBar.visibility = View.VISIBLE

            // Usar Log.d para verificar que la carga de doctores ha comenzado
            Log.d("MainActivity", "Cargando doctores...")

            // Llamada al ViewModel para obtener los doctores
            viewModel.loadDoctors().observe(this@MainActivity, Observer {
                // Verificar que los datos de doctores fueron recibidos
                if (it.isEmpty()) {
                    Log.e("MainActivity", "Error al cargar los doctores: Lista vacía")
                } else {
                    Log.i("MainActivity", "Doctores cargados con éxito")
                }

                // Configurar el RecyclerView con los doctores
                topView.layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                topView.adapter = NearDoctorsAdapter(it)

                // Ocultar el ProgressBar una vez que los datos han sido cargados
                progressBar.visibility = View.GONE
            })
        }
    }
}
