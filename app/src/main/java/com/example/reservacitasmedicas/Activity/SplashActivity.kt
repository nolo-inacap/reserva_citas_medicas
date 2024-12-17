package com.example.reservacitasmedicas.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.reservacitasmedicas.R
import com.example.reservacitasmedicas.databinding.ActivitySplashBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth
import kotlin.math.sign

class SplashActivity : BaseActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        // Verificar si el usuario ya está autenticado
        if (firebaseAuth.currentUser != null) {
            navigateToMain()
        } else {
            setupAuthenticationFlow()
        }
    }

    private fun setupAuthenticationFlow() {
        binding.apply {
            // Mostrar campos para ingresar correo y contraseña
            val correo: TextView = findViewById(R.id.edtEmail)
            val clave: TextView = findViewById(R.id.edtPassword)
            val btnIngresar: Button = findViewById(R.id.startBtn)

            btnIngresar.setOnClickListener {
                signIn(correo.text.toString(), clave.text.toString())
            }
        }
    }

    private fun signIn(correo: String, clave: String) {
        firebaseAuth.signInWithEmailAndPassword(correo, clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Autenticación Correcta", Toast.LENGTH_SHORT).show()
                    navigateToMain()
                } else {
                    Toast.makeText(baseContext, "Autenticación Fallida", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
