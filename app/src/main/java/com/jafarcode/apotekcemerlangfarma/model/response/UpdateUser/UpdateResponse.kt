package com.jafarcode.apotekcemerlangfarma.model.response.UpdateUser


import com.google.gson.annotations.SerializedName

data class UpdateResponse(
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("created_at")
    val createdAt: Int,
    @SerializedName("current_team_id")
    val currentTeamId: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any,
    @SerializedName("houseNumber")
    val houseNumber: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("profile_photo_path")
    val profilePhotoPath: String,
    @SerializedName("profile_photo_url")
    val profilePhotoUrl: String,
    @SerializedName("roles")
    val roles: String,
    @SerializedName("two_factor_confirmed_at")
    val twoFactorConfirmedAt: Any,
    @SerializedName("updated_at")
    val updatedAt: Int
)