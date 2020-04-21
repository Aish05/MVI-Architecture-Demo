package aish.android.mvisample.viewmodel

import aish.android.mvisample.ui.state.CovidDataViewState
import aish.android.mvisample.model.Summary
import aish.android.mvisample.repository.SummaryRepository
import aish.android.mvisample.ui.state.CovidStateEvent
import aish.android.mvisample.util.AbsentLiveData
import aish.android.mvisample.util.DataState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainDataViewModel : ViewModel() {

    private val _stateEvent = MutableLiveData<CovidStateEvent>()
    private val _viewState = MutableLiveData<CovidDataViewState>()

    val viewState: LiveData<CovidDataViewState>
        get() = _viewState

    val dataState: LiveData<DataState<CovidDataViewState>> = Transformations
        .switchMap(_stateEvent) { stateEvent ->
            stateEvent?.let {
               handleStateEvent(it)
            }
        }

    private fun handleStateEvent(stateEvent: CovidStateEvent): LiveData<DataState<CovidDataViewState>> {
        when (stateEvent) {
            is CovidStateEvent.GetSummary -> {
                return SummaryRepository.getSummary()
            }

            is CovidStateEvent.None -> {
                return AbsentLiveData.create()
            }

        }
    }

    fun setSummary(summary : Summary) {
        val update = getCurrentCovidViewStateOrNew()
        update.summary = summary
       _viewState.value = update
    }

    fun getCurrentCovidViewStateOrNew() : CovidDataViewState {
        val value = viewState.value?.let {
            it
        } ?: CovidDataViewState()
        return value
    }

    fun setCovidStateEvent(event : CovidStateEvent){
       _stateEvent.value = event
    }


}