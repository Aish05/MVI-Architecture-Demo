package aish.android.mvisample.api

import aish.android.mvisample.model.Summary
import aish.android.mvisample.util.GenericApiResponse
import androidx.lifecycle.LiveData
import retrofit2.http.GET

interface ApiService {

    @GET("summary")
    fun getSummary(): LiveData<GenericApiResponse<Summary>>

}