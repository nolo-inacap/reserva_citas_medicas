package com.example.reservacitasmedicas.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reservacitasmedicas.Adapter.NearDoctorsAdapter
import com.example.reservacitasmedicas.R
import com.example.reservacitasmedicas.ViewModel.MainViewModel
import com.example.reservacitasmedicas.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNearByDoctor()

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