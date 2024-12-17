package com.example.reservacitasmedicas.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reservacitasmedicas.Model.AppointmentModel
import com.example.reservacitasmedicas.R

class AppointmentAdapter(private val appointments:List<AppointmentModel>) :
    RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>(){

    private var onItemClickListener: ((String, AppointmentModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_create_appointment, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointments[position]
        holder.bind(appointment)

        // Llamar al listener cuando se haga clic en el elemento
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(appointment.Id, appointment) // cliente.id debe contener el documentId de Firestore
        }
    }

    override fun getItemCount() = appointments.size

    fun setOnItemClickListener(listener: (String, AppointmentModel) -> Unit) {
        onItemClickListener = listener
    }

    class AppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvNombre = itemView.findViewById<TextView>(R.id.tvNombre)
        private val tvCorreo = itemView.findViewById<TextView>(R.id.tvCorreo)
        private val tvTelefono = itemView.findViewById<TextView>(R.id.tvTelefono)
        private val tvRut = itemView.findViewById<TextView>(R.id.tvRut)

        fun bind(appointment: AppointmentModel) {
            tvNombre.text = "${appointment.Name}"
            tvCorreo.text = appointment.Email
            tvTelefono.text = appointment.Phone
            tvRut.text = appointment.Rut
        }
    }
}


