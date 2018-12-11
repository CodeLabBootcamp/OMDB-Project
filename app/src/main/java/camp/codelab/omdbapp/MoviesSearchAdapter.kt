package camp.codelab.omdbapp

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_movie_search.view.*

class MoviesSearchAdapter(val moviesList: List<Movie>) :
    RecyclerView.Adapter<MoviesSearchAdapter.MoviesSearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesSearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_search, parent, false)
        return MoviesSearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(viewHolder: MoviesSearchViewHolder, position: Int) {
        viewHolder.setMovie(moviesList[position])
    }


    inner class MoviesSearchViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        init {

            view.setOnClickListener {
                val id = moviesList[layoutPosition].imdbID

                val intent = Intent(it.context, MovieDetailsActivity::class.java)
                intent.putExtra(Consts.ID, id)
                it.context.startActivity(intent)

            }

        }

        fun setMovie(movie: Movie) {
            view.titleTextView.text = movie.title
            view.movieImageView.setImageFromUrl(movie.poster)
        }

    }
}