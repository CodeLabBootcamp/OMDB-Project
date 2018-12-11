package camp.codelab.omdbapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDBInterface {


    @GET(".")
    fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") searchQuery: String
    ) : Call<SearchResponse>


    @GET(".")
    fun getMovieInfo(
        @Query("apikey") apiKey: String,
        @Query("i") id: String
    ) : Call<Movie>


}