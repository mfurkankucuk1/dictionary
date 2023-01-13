package com.mfk.dictionary.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mfk.dictionary.data.model.DataMuse
import com.mfk.dictionary.data.model.Dictionary
import com.mfk.dictionary.data.model.LocalSearch
import com.mfk.dictionary.data.repository.DictionaryRepository
import com.mfk.dictionary.data.repository.PreferencesRepository
import com.mfk.dictionary.utils.Constants.NOT_FOUND
import com.mfk.dictionary.utils.Constants.NO_INTERNET_CONNECTION
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
class DictionaryViewModel @Inject constructor(
    application: Application,
    private val dictionaryRepository: DictionaryRepository,
    val preferencesRepository: PreferencesRepository
) : AndroidViewModel(application) {

    private var _insertSearchResponse: MutableLiveData<Long?> = MutableLiveData()
    val insertSearchResponse: LiveData<Long?> get() = _insertSearchResponse

    private var _getLastSearchResponse: MutableLiveData<List<LocalSearch>?> = MutableLiveData()
    val getLastSearchResponse: LiveData<List<LocalSearch>?> get() = _getLastSearchResponse

    private var _getDictionaryDetailResponse: MutableLiveData<Resource<List<Dictionary>>> =
        MutableLiveData()
    val getDictionaryDetailResponse: LiveData<Resource<List<Dictionary>>> get() = _getDictionaryDetailResponse



    fun clearInsertSearchResponse() {
        _insertSearchResponse.value = null
    }

    fun clearGetLastSearchResponse() {
        _getLastSearchResponse.value = null
    }


    /**
     * Insert search
     * **/
    fun insertSearch(localSearch: LocalSearch) = viewModelScope.launch {
        insertSearchSafeCall(localSearch)
    }

    private suspend fun insertSearchSafeCall(localSearch: LocalSearch) {
        _insertSearchResponse.value = dictionaryRepository.insertSearch(localSearch)
    }

    /**
     * Get last search
     * **/

    fun getLastSearch() = viewModelScope.launch {
        getLastSearchSafeCall()
    }

    private suspend fun getLastSearchSafeCall() {
        _getLastSearchResponse.value = dictionaryRepository.getLastSearch()
    }

    /**
     * Get dictionary detail
     * **/

    fun getDictionaryDetail(word:String) = viewModelScope.launch {
        getDictionarySafeCall(word)
    }

    private suspend fun getDictionarySafeCall(word: String) {
        _getDictionaryDetailResponse.value = Resource.Loading()
        if (NetworkHelper.hasInternetConnection(getApplication())) {
            try {
                val response = dictionaryRepository.getWordDetail(word)
                _getDictionaryDetailResponse.value = RequestHelper.handleResponse(response)
            } catch (e: Exception) {
                _getDictionaryDetailResponse.postValue(Resource.Error(NOT_FOUND))
                e.printStackTrace()
            }
        } else {
            _getDictionaryDetailResponse.postValue(Resource.Error(NO_INTERNET_CONNECTION))
        }
    }



}