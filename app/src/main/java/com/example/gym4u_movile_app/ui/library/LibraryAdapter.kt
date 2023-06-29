package com.example.gym4u_movile_app.ui.library

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.models.Library
//import com.bumptech.glide.Glid

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class LibraryAdapter (var library: ArrayList<Library>):RecyclerView.Adapter<LibraryPrototype>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryPrototype {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_library, parent, false)
        return LibraryPrototype(view)
    }

    override fun onBindViewHolder(holder: LibraryPrototype, position: Int) {
        holder.bind(library.get(position))
    }
    override fun getItemCount(): Int {
        return library.size
    }
}

class LibraryPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvname= itemView.findViewById<TextView>(R.id.tvnameExercise)
    val tvcategory= itemView.findViewById<TextView>(R.id.tvcategoryExecise)
    val tvaproach= itemView.findViewById<TextView>(R.id.tvaprroach)
    val Ivexercise= itemView.findViewById<ImageView>(R.id.ivFirstImage)

    fun bind(library: Library) {

        tvname.text =library.name

        tvcategory.text = library.category
        tvaproach.text = library.tag.name
        Glide.with(itemView)
            .load(library.assetUrl)
            .into(Ivexercise)
    }
}


