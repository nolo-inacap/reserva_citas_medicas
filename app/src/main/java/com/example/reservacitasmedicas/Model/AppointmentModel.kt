package com.example.reservacitasmedicas.Model

import android.os.Parcel
import android.os.Parcelable

data class AppointmentModel(
    val Date:String="",
    val Time:String="",
    val Rut:String="",
    val Phone:String="",
    val Name:String=""
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Date)
        parcel.writeString(Time)
        parcel.writeString(Rut)
        parcel.writeString(Phone)
        parcel.writeString(Name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AppointmentModel> {
        override fun createFromParcel(parcel: Parcel): AppointmentModel {
            return AppointmentModel(parcel)
        }

        override fun newArray(size: Int): Array<AppointmentModel?> {
            return arrayOfNulls(size)
        }
    }
}
