package ir.nima.balootchallenge.data.api.model


import com.google.gson.annotations.SerializedName

data class ErrorModel(
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)