package com.example.reservacitasmedicas.Activity

import android.widget.TextView
import com.example.reservacitasmedicas.R
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reservacitasmedicas.Adapter.AppointmentAdapter
import com.example.reservacitasmedicas.Model.AppointmentModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.util.UUID

class CreateAppointmentActivity : BaseActivity() {
    private lateinit var btnSelectDate: Button
    private lateinit var tvSelectedDate: TextView

    private lateinit var db: FirebaseFirestore
    private lateinit var appointmentAdapter: AppointmentAdapter
    private val appointmentList = mutableListOf<AppointmentModel>()
    private var selectedAppointmentId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_appointment)

        db = Firebase.firestore

        // Referencias a las vistas
        val rvAppointments = findViewById<RecyclerView>(R.id.rvAppointments)
        val etRut = findViewById<EditText>(R.id.etRut)
        val etPatientName = findViewById<EditText>(R.id.etPatientName)
        val etCorreo = findViewById<EditText>(R.id.edtEmail)
        val etTelefono = findViewById<EditText>(R.id.etPhone)
        val btnSaveAppointment = findViewById<Button>(R.id.btnSaveAppointment)
        val btnEditAppointment = findViewById<Button>(R.id.btnEditAppointment)
        val btnDisableAppointment = findViewById<Button>(R.id.btnDisableAppointment)
        val btnRefrescar = findViewById<Button>(R.id.btnRefrescar)

        rvAppointments.layoutManager = LinearLayoutManager(this)
        appointmentAdapter = AppointmentAdapter(appointmentList)
        rvAppointments.adapter = appointmentAdapter

        getAllAppointments()

        appointmentAdapter.setOnItemClickListener{ documentId, appointment ->
            selectedAppointmentId = documentId
            etRut.setText(appointment.Rut)
            etPatientName.setText(appointment.Name)
            etCorreo.setText(appointment.Email)

            etTelefono.setText(appointment.Phone)
        }

        btnSaveAppointment.setOnClickListener{
            val rut = etRut.text.toString()
            val nombre = etPatientName.text.toString()
            val correo = etCorreo.text.toString()
            val telefono = etTelefono.text.toString()

            if (rut.isNotEmpty() && nombre.isNotEmpty() && correo.isNotEmpty() && telefono.isNotEmpty()
            ) {
                val newAppointment = AppointmentModel(
                    Id = UUID.randomUUID().toString(),  // Generar un ID único
                    Rut = rut,
                    Name = nombre,
                    Email = correo,
                    Phone = telefono,
                    Available = 1
                )
                addClient(newAppointment)
                clearFields(etRut, etPatientName, etCorreo, etTelefono)
                Toast.makeText(this, "Cliente agregado exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnEditAppointment.setOnClickListener {
            selectedAppointmentId?.let { documentId ->
                val updatedAppointment = AppointmentModel(
                    Id = documentId,  // Añadir el ID aquí
                    Rut = etRut.text.toString(),
                    Name = etPatientName.text.toString(),
                    Email = etCorreo.text.toString(),
                    Phone = etTelefono.text.toString(),
                    Available = 1
                )
                editAppointment(documentId, updatedAppointment)
                clearFields(etRut, etPatientName, etCorreo, etTelefono)
                Toast.makeText(this, "Reserva Actualizada Exitosamente", Toast.LENGTH_SHORT).show()
            } ?: Toast.makeText(this, "Seleccione una Reserva primero", Toast.LENGTH_SHORT).show()
        }

        btnDisableAppointment.setOnClickListener {
            selectedAppointmentId?.let { documentId ->
                disableAppointment(documentId)
                clearFields(etRut, etPatientName, etCorreo, etTelefono)
                Toast.makeText(this, "Reserva deshabilitada exitosamente", Toast.LENGTH_SHORT).show()
            } ?: Toast.makeText(this, "Seleccione una Reserva primero", Toast.LENGTH_SHORT).show()
        }

        btnRefrescar.setOnClickListener{
            getAllAppointments()
        }
    }
    private fun getAllAppointments() {
        db.collection("Appointments")
            .whereEqualTo("habilitado", 1)
            .get()
            .addOnSuccessListener { result ->
                appointmentList.clear()
                for (document in result) {
                    val appointment = document.toObject(AppointmentModel::class.java)
                    appointment.Id = document.id  // Guardar el ID del documento en el cliente
                    appointmentList.add(appointment)
                }
                appointmentAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Toast.makeText(baseContext, "Error al mostrar Reservas $e ", Toast.LENGTH_SHORT).show()
            }
    }
    private fun addClient(appointment: AppointmentModel) {
        db.collection("Appointments")
            .add(appointment)
            .addOnSuccessListener {
                getAllAppointments()
            }
            .addOnFailureListener { e ->
                Toast.makeText(baseContext, "Error al agregar Reserva $e ", Toast.LENGTH_SHORT).show()
            }
    }
    private fun editAppointment(documentId: String, updatedAppointments: AppointmentModel) {
        db.collection("Appointments").document(documentId)
            .set(updatedAppointments)
            .addOnSuccessListener {
                getAllAppointments()
            }
            .addOnFailureListener { e ->
                Toast.makeText(baseContext, "Error al editar Reservas $e ", Toast.LENGTH_SHORT).show()
            }
    }

    private fun disableAppointment(documentId: String) {
        db.collection("Appointments").document(documentId)
            .update("habilitado", 0)
            .addOnSuccessListener {
                getAllAppointments()
            }
            .addOnFailureListener { e ->
                Toast.makeText(baseContext, "Error al deshabilitar Reserva $e ", Toast.LENGTH_SHORT).show()
            }
    }
    private fun clearFields(vararg editTexts: EditText) {
        editTexts.forEach { it.text.clear() }
    }


    /*private val allowedDates = arrayOf(
        "2024-07-01", "2024-07-05", "2024-07-10", "2024-07-15", "2024-07-20"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_appointment)
        btnSelectDate = findViewById(R.id.btnSelectDate)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        /*btnSelectDate.setOnClickListener {
                showNumberPickerDialog()
            }*/



        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
    }*/
}