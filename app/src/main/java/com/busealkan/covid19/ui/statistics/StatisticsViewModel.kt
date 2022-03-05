package com.busealkan.covid19.ui.statistics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.busealkan.covid19.ResourceStatus
import com.busealkan.covid19.data.model.StatisticsResponse
import com.busealkan.covid19.data.repository.StatisticsRepository
import com.busealkan.covid19.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(private val repository: StatisticsRepository) : BaseViewModel() {


    var allStatisticsLiveData = MutableLiveData<List<StatisticsResponse>>()
    var allHistoryLiveData = MutableLiveData<List<StatisticsResponse>>()


    fun getStatisticsList()  = viewModelScope.launch {

        repository.getStatisticsList()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {
                    ResourceStatus.LOADING -> {
                        isLoading.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        allStatisticsLiveData.postValue(it.data!!)
                        isLoading.postValue(false)
                    }

                    ResourceStatus.ERROR -> {
                        errorMessageLiveData.postValue(it.errorMessage)
                        isLoading.postValue(false)
                    }
                }
            }
    }

    fun getStatisticalHistory(country:String,day:String)  = viewModelScope.launch {

        repository.getStatisticalHistory(country,day)

                .asLiveData(viewModelScope.coroutineContext).observeForever {

                    when(it.status) {
                        ResourceStatus.LOADING -> {
                            isLoading.postValue(true)
                        }

                        ResourceStatus.SUCCESS -> {
                            allHistoryLiveData.postValue(it.data!!)
                            isLoading.postValue(false)
                        }

                        ResourceStatus.ERROR -> {
                            errorMessageLiveData.postValue(it.errorMessage)
                            isLoading.postValue(false)
                        }
                    }
                }
    }

}