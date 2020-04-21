package  aish.android.mvisample.model

import com.google.gson.annotations.SerializedName

data class Global(@SerializedName("NewRecovered")
                  val newRecovered: Int = 0,
                  @SerializedName("NewDeaths")
                  val newDeaths: Int = 0,
                  @SerializedName("TotalRecovered")
                  val totalRecovered: Int = 0,
                  @SerializedName("TotalConfirmed")
                  val totalConfirmed: Int = 0,
                  @SerializedName("NewConfirmed")
                  val newConfirmed: Int = 0,
                  @SerializedName("TotalDeaths")
                  val totalDeaths: Int = 0) {
    override fun toString(): String {
        return "Global(newRecovered=$newRecovered, newDeaths=$newDeaths, totalRecovered=$totalRecovered, totalConfirmed=$totalConfirmed, newConfirmed=$newConfirmed, totalDeaths=$totalDeaths)"
    }
}