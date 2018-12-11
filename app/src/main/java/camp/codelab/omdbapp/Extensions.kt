package camp.codelab.omdbapp

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import com.squareup.picasso.Picasso



fun ImageView.setImageFromUrl(url: String){
    Picasso.get()
        .load(url)
        .into(this)
}

fun EditText.setChangeListener(callback: (String) -> Unit){
    addTextChangedListener(object :TextWatcher{
        override fun afterTextChanged(s: Editable?) {
            callback(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}