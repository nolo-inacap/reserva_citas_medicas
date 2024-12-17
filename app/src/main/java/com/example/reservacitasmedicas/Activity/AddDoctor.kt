package com.example.reservacitasmedicas.Activity

import com.google.firebase.firestore.FirebaseFirestore

data class Doctor(
    val Address: String = "",
    val Biography: String = "",
    val Cost: String = "",
    val Date: String = "",
    val Experience: Int = 0,
    val Id: Int = 0,
    val Location: String = "",
    val Mobile: String = "",
    val Name: String = "",
    val Patiens: String = "",
    val Picture: String = "",
    val Rating: Double = 0.0,
    val Site: String = "",
    val Special: String = "",
    val Time: String = ""
)


fun addDoctorToFirestore() {
    val db = FirebaseFirestore.getInstance()

    // Crear el objeto Doctor con los datos
    val doctor = Doctor(
        Address = "8502 Preston Rd, Inglewood, Maine 98380",
        Biography = "A board-certified with over 3 years of experience,  specializing in heart conditions such as coronary artery  disease and arrhythmias. Known for patient-centered care  and a commitment to the latest medical advancements",
        Cost = "$40/hr",
        Date = "Sunday 29 Jun",
        Experience = 3,
        Id = 3,
        Location = "http://maps.google.com/maps?q=loc:31.995801008207952,44.31452133516133",
        Mobile = "00123456789",
        Name = "Dr. Jessica Wyne",
        Patiens = "500+",
        Picture = "https://firebasestorage.googleapis.com/v0/b/project198-ee047.appspot.com/o/Dr%20Jessica%20Wyne.png?alt=media&token=9ad675d6-45e9-4bc5-b61b-fdecbcc6c79b",
        Rating = 4.1,
        Site = "http://www.test.com",
        Special = "Radiologo Especialista",
        Time = "5:00 - 7:00 Pm"
    )

    // Agregar el doctor al documento con ID "0" en la colección "Doctors"
    db.collection("Doctors")
        .document("3")  // Especificamos el ID del documento
        .set(doctor)  // Establecemos los datos del objeto Doctor
        .addOnSuccessListener {
            println("Doctor agregado con éxito a Firestore.")
        }
        .addOnFailureListener { e ->
            println("Error al agregar el doctor: ${e.message}")
        }
}
