package com.example.harrypotter_app.di.model.modelData

import android.os.Parcel
import android.os.Parcelable

data class CharacterItem(
    val actor: String,
    val dateOfBirth: String,
    val gender: String,
    val house: String,
    val id: String,
    val image: String,
    val name: String,
    val species: String,
    val wand: Wand,
    val wizard: Boolean,
    val yearOfBirth: Int
): Parcelable {
 constructor(parcel: Parcel) : this(
  parcel.readString()!!,
  parcel.readString()!!,
  parcel.readString()!!,
  parcel.readString()!!,
  parcel.readString()!!,
  parcel.readString()!!,
  parcel.readString()!!,
  parcel.readString()!!,
  TODO("wand"),
  parcel.readByte() != 0.toByte(),
  parcel.readInt()
 ) {
 }

 override fun writeToParcel(parcel: Parcel, flags: Int) {
  parcel.writeString(actor)
  parcel.writeString(dateOfBirth)
  parcel.writeString(gender)
  parcel.writeString(house)
  parcel.writeString(id)
  parcel.writeString(image)
  parcel.writeString(name)
  parcel.writeString(species)
  parcel.writeByte(if (wizard) 1 else 0)
  parcel.writeInt(yearOfBirth)
 }

 override fun describeContents(): Int {
  return 0
 }

 companion object CREATOR : Parcelable.Creator<CharacterItem> {
  override fun createFromParcel(parcel: Parcel): CharacterItem {
   return CharacterItem(parcel)
  }

  override fun newArray(size: Int): Array<CharacterItem?> {
   return arrayOfNulls(size)
  }
 }
}