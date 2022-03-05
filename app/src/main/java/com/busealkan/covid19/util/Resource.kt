package com.busealkan.covid19

sealed class Resource<T>(
    val data: T?,
    val errorMessage: String?,
    val status: ResourceStatus
) {
    class Loading<T> : Resource<T>(null, null, ResourceStatus.LOADING)
    class Success<T>(data: T?) : Resource<T>(data, null, ResourceStatus.SUCCESS)
    class Error<T>(errorMessage: String) : Resource<T>(null, errorMessage, ResourceStatus.ERROR)
}

enum class ResourceStatus {
    LOADING,
    SUCCESS,
    ERROR
}
