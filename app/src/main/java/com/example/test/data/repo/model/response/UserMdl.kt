package com.example.test.data.repo.model.response

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Keep
@Parcelize
data class UserMdl(
    @SerializedName("usrID") val usrID : String,
    @SerializedName("usrName") val usrName : String,
    @SerializedName("usrFullName") val usrFullName : String,
    @SerializedName("usrRole") val usrRole : Int,
    @SerializedName("usrActive") val usrActive : Boolean,
    @SerializedName("usrColorCode") val usrColorCode : String,
    @SerializedName("usrCreatedAt") val usrCreatedAt : String,
    @SerializedName("usrUpdatedUsr") val usrUpdatedUsr : String,
    @SerializedName("usrUpdatedAt") val usrUpdatedAt : String,
    @SerializedName("usrInitial") val usrInitial : String,
    @SerializedName("usrActiveLabel") val usrActiveLabel : String,
    @SerializedName("usrRoleName") val usrRoleName : String,
    @SerializedName("locations") val locations : List<String>,
    @SerializedName("token") val token : String
) : Parcelable