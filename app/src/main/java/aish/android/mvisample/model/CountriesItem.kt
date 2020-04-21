package  aish.android.mvisample.model

import com.google.gson.annotations.SerializedName

data class CountriesItem(@SerializedName("NewRecovered")
                         val newRecovered: Int = 0,
                         @SerializedName("NewDeaths")
                         val newDeaths: Int = 0,
                         @SerializedName("TotalRecovered")
                         val totalRecovered: Int = 0,
                         @SerializedName("TotalConfirmed")
                         val totalConfirmed: Int = 0,
                         @SerializedName("Country")
                         val country: String = "",
                         @SerializedName("CountryCode")
                         val countryCode: String = "",
                         @SerializedName("Slug")
                         val slug: String = "",
                         @SerializedName("NewConfirmed")
                         val newConfirmed: Int = 0,
                         @SerializedName("TotalDeaths")
                         val totalDeaths: Int = 0,
                         @SerializedName("Date")
                         val date: String = "") {

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as CountriesItem
        if (countryCode != other.countryCode) return false
        return true
    }

    override fun toString(): String {
        return "CountriesItem(newRecovered=$newRecovered, newDeaths=$newDeaths, totalRecovered=$totalRecovered, totalConfirmed=$totalConfirmed, country='$country', countryCode='$countryCode', slug='$slug', newConfirmed=$newConfirmed, totalDeaths=$totalDeaths, date='$date')"
    }


}