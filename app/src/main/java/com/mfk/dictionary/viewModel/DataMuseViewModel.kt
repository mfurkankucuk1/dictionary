package com.mfk.dictionary.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mfk.dictionary.data.model.DataMuse
import com.mfk.dictionary.data.remote.DatamuseService
import com.mfk.dictionary.utils.Constants
import com.mfk.dictionary.utils.NetworkHelper
import com.mfk.dictionary.utils.RequestHelper
import com.mfk.dictionary.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
@HiltViewModel
class DataMuseViewModel @Inject constructor(
    application: Application,
    private val dataMuseService: DatamuseService
) : AndroidViewModel(application) {

    private var _getDataMuseResponse: MutableLiveData<Resource<List<DataMuse>>> = MutableLiveData()
    val getDataMuseResponse: LiveData<Resource<List<DataMuse>>> get() = _getDataMuseResponse


    /**
     * Get data muse
     * **/

    fun getDataMuse(word: String) = viewModelScope.launch {
        getDataMuseSafeCall(word)
    }

    private suspend fun getDataMuseSafeCall(word: String) {
        _getDataMuseResponse.value = Resource.Loading()
        if (NetworkHelper.hasInternetConnection(getApplication())) {
            try {
                val response = dataMuseService.getDataMuse(word)
                _getDataMuseResponse.value = RequestHelper.handleResponse(response)
            } catch (e: Exception) {
                _getDataMuseResponse.postValue(Resource.Error(Constants.NOT_FOUND))
                e.printStackTrace()
            }
        } else {
            _getDataMuseResponse.postValue(Resource.Error(Constants.NO_INTERNET_CONNECTION))
        }
    }
}