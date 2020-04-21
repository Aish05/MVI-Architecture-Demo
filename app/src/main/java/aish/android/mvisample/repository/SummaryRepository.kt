package aish.android.mvisample.repository

import aish.android.mvisample.api.RetrofitBuilder
import aish.android.mvisample.model.Summary
import aish.android.mvisample.ui.main.state.CovidDataViewState
import aish.android.mvisample.util.ApiSuccessResponse
import aish.android.mvisample.util.DataState
import aish.android.mvisample.util.GenericApiResponse
import androidx.lifecycle.LiveData

object SummaryRepository {

    fun getSummary() : LiveData<DataState<CovidDataViewState>> {
        return object : NetworkBoundResource<Summary, CovidDataViewState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<Summary>) {
                result.value = DataState.data(data = CovidDataViewState(response.body))
            }

            override fun createCall(): LiveData<GenericApiResponse<Summary>> {
                return RetrofitBuilder.apiService.getSummary()
            }

        }.asLiveData()
    }

}