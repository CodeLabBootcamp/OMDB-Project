package camp.codelab.omdbapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        searchEditText.setChangeListener {
            searchForMovies(it)
        }


    }

    private fun searchForMovies(searchQuery: String) {

        val retrofit = Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val omdbInterface = retrofit.create(OMDBInterface::class.java)

        omdbInterface.searchMovies(Consts.API_KEY, searchQuery)
            .enqueue(object : Callback<SearchResponse> {
                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                    response.body()?.movieList?.let {
                        prepareRecyclerView(it)
                    }
                }

            })

    }

    private fun prepareRecyclerView(list: List<Movie>) {

        recylcerView.layoutManager = GridLayoutManager(this, 2)
        recylcerView.adapter = MoviesSearchAdapter(list)

    }

}
