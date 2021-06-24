package ir.nima.balootchallenge.utils

import ir.nima.balootchallenge.data.api.model.ErrorModel

sealed class Result<out T>{
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    data class GenericError(val code: Int? = null, val error: ErrorModel? = null): Result<Nothing>()
    object Loading : Result<Nothing>()
}
