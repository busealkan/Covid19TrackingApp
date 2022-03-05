package com.busealkan.covid19.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.busealkan.covid19.util.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {
    var isLoading = SingleLiveEvent<Boolean>()
    var errorMessageLiveData = SingleLiveEvent<String>()
    var isPageLoaded = MutableLiveData<Boolean>(false)
}