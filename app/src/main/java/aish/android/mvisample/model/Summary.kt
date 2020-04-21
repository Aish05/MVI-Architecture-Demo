package  aish.android.mvisample.model

import com.google.gson.annotations.SerializedName

data class Summary(@SerializedName("Countries")
                   val countries: List<CountriesItem>?,
                   @SerializedName("Global")
                   val global: Global,
                   @SerializedName("Date")
                   val date: String = "") {
    override fun toString(): String {
        return "Summary(countries=$countries, global=$global, date='$date')"
    }
}