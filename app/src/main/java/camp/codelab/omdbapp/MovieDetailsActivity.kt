package camp.codelab.omdbapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_movie_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        setSupportActionBar(toolbar)

        val id = intent.getStringExtra(Consts.ID)


        loadMovieData(id)


    }

    private fun loadMovieData(id: String?) {

        id?.let { id ->

            val retrofit = Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val omdbInterface = retrofit.create(OMDBInterface::class.java)

            omdbInterface.getMovieInfo(Consts.API_KEY, id)
                .enqueue(object : Callback<Movie> {
                    override fun onFailure(call: Call<Movie>, t: Throwable) {
                        Toast.makeText(this@MovieDetailsActivity, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<Movie>, response: Response<Movie>) {

                        response.body()?.let {
                            fillInfo(it)
                        }

                    }

                })

        }

    }

    private fun fillInfo(movie: Movie) {
        Toast.makeText(this, movie.title, Toast.LENGTH_LONG).show()

        toolbar_layout.title = movie.title

        posterImageView.setImageFromUrl(movie.poster)

        if (movie.website == "N/A")
            fab.visibility = View.INVISIBLE
        else
            fab.setOnClickListener { view ->

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.website))
                startActivity(intent)

            }


    }
}
