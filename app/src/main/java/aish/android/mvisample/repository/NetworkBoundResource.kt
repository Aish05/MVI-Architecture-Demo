package aish.android.mvisample.repository

import aish.android.mvisample.util.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class NetworkBoundResource<ResponseObject, ViewStateType> {

    protected val result = MediatorLiveData<DataState<ViewStateType>>()

    init {
        result.value = DataState.loading(true)
        GlobalScope.launch(IO) {
            withContext(Dispatchers.Main) {
                val apiResponse = createCall()
                result.addSource(apiResponse) { response->
                    result.removeSource(apiResponse)

                    handleNetworkCall(response)
                }

            }
        }
    }

    private fun handleNetworkCall(response: GenericApiResponse<ResponseObject>) {

        when(response){
            is ApiSuccessResponse ->{
                handleApiSuccessResponse(response)
            }
            is ApiErrorResponse ->{
                println("DEBUG: NetworkBoundResource: ${response.errorMessage}")
                onReturnError(response.errorMessage)
            }
            is ApiEmptyResponse -> {
                println("DEBUG: NetworkBoundResource: Request returned NOTHING (HTTP 204)")
                onReturnError("HTTP 204. Returned NOTHING. Empty response")
            }
        }
    }


    fun onReturnError(message : String) {
        result.value = DataState.error(message)
    }

    abstract fun handleApiSuccessResponse(response: ApiSuccessResponse<ResponseObject>)

    abstract fun createCall(): LiveData<GenericApiResponse<ResponseObject>>

    fun asLiveData() =  result as LiveData<DataState<ViewStateType>>

}