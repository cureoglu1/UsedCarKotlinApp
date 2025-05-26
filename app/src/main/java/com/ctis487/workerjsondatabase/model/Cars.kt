package com.ctis487.workerjsondatabase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ctis487.workerjsondatabase.util.Utils
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = Utils.TABLENAME_CARS)
@Parcelize
data class Cars(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") val id: Int,
    @SerializedName("make") val make: String,
    @SerializedName("model") val model: String,
    @SerializedName("year") val year: Int,
    @SerializedName("color") val color: String,
    @SerializedName("mileage") val mileage: Int,
    @SerializedName("price") val price: Int,
    @SerializedName("fuelType") val fuelType: String,
    @SerializedName("transmission") val transmission: String,
    @SerializedName("engine") val engine: String,
    @SerializedName("horsepower") val horsepower: Int,
    @SerializedName("features") val features: List<String>,
    @SerializedName("owners") val owners: Int,
    @SerializedName("image") val image: String
) : Parcelable

