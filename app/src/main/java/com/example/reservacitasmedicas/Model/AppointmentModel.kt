package com.example.reservacitasmedicas.Model

import android.os.Parcel
import android.os.Parcelable

data class AppointmentModel(
    var Id: String = "",
    var Date:String="",
    var Time:String="",
    var Rut:String="",
    var Phone:String="",
    var Email:String="",
    var Name:String="",
    val Available: Int = 1
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Date)
        parcel.writeString(Time)
        parcel.writeString(Rut)
        parcel.writeString(Phone)
        parcel.writeString(Email)
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