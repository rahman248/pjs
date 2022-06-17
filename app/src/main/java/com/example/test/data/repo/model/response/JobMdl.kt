package com.example.test.data.repo.model.response

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class JobMdl(
    @SerializedName("jotID") val jotID : String,
    @SerializedName("jotName") val jotName : String,
    @SerializedName("jotActive") val jotActive : Boolean,
    @SerializedName("jotCreatedAt") val jotCreatedAt : String,
    @SerializedName("jotUpdatedUsr") val jotUpdatedUsr : String,
    @SerializedName("jotUpdatedAt") val jotUpdatedAt : String,
    @SerializedName("jotActiveLabel") val jotActiveLabel : String
): Parcelable
