package camp.codelab.omdbapp

import com.google.gson.annotations.SerializedName

class Movie {

    @SerializedName("Title")
    var title: String = ""
    @SerializedName("Year")
    var year: String = ""
    @SerializedName("imdbID")
    var imdbID: String = ""
    @SerializedName("Poster")
    var poster: String = ""
    @SerializedName("Website")
    var website: String = ""

}