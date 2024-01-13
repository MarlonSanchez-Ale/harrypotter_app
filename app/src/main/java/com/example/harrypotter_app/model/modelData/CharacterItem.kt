package com.example.harrypotter_app.model.modelData

import android.os.Parcel
import android.os.Parcelable

data class CharacterItem(
    val actor: String,
    val alive: Boolean,
    val alternate_actors: List<String>,
    val alternate_names: List<String>,
    val ancestry: String,
    val dateOfBirth: String,
    val eyeColour: String,
    val gender: String,
    val hairColour: String,
    val hogwartsStaff: Boolean,
    val hogwartsStudent: Boolean,
    val house: String,
    val id: String,
    val image: String,
    val name: String,
    val patronus: String,
    val species: String,
    val wand: Wand,
    val wizard: Boolean,
    val yearOfBirth: Int
): Parcelable {
 constructor(parcel: Parcel) : this(
  parcel.readString()!!,
  parcel.readByte() != 0.toByte(),
  parcel.createStringArrayList()!!,
  parcel.createStringArrayList()!!,
  parcel.readString()!!,
  parcel.readString()!!,
  parcel.readString()!!,
  parcel.readString()!!,
  parcel.readString()!!,
  parcel.readByte() != 0.toByte(),
  parcel.readByte() != 0.toByte(),
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
  parcel.writeByte(if (alive) 1 else 0)
  parcel.writeStringList(alternate_actors)
  parcel.writeStringList(alternate_names)
  parcel.writeString(ancestry)
  parcel.writeString(dateOfBirth)
  parcel.writeString(eyeColour)
  parcel.writeString(gender)
  parcel.writeString(hairColour)
  parcel.writeByte(if (hogwartsStaff) 1 else 0)
  parcel.writeByte(if (hogwartsStudent) 1 else 0)
  parcel.writeString(house)
  parcel.writeString(id)
  parcel.writeString(image)
  parcel.writeString(name)
  parcel.writeString(patronus)
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