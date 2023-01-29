package com.jafarcode.apotekcemerlangfarma.model.response.home


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Data(
    @Expose
    @SerializedName("created_at")
    val createdAt: String?,
    @Expose
    @SerializedName("deleted_at")
    val deletedAt: String?,
    @Expose
    @SerializedName("description")
    val description: String?,
    @Expose
    @SerializedName("dose")
    val dose: String?,
    @Expose
    @SerializedName("effect")
    val effect: String?,
    @Expose
    @SerializedName("how_to_use")
    val howToUse: String?,
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("ingredients")
    val ingredients: String?,
    @Expose
    @SerializedName("manufacture")
    val manufacture: String?,
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("picturePath")
    val picturePath: String?,
    @Expose
    @SerializedName("price")
    val price: Int?,
    @Expose
    @SerializedName("rate")
    val rate: Int?,
    @Expose
    @SerializedName("types")
    val types: String?,
//    @Expose
//    @SerializedName("effect")
//    val effect: String?,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: String?,
) : Parcelable