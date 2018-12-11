package camp.codelab.omdbapp

import com.google.gson.annotations.SerializedName


class SearchResponse {

    @SerializedName("Search")
    var movieList: List<Movie> = listOf()

}