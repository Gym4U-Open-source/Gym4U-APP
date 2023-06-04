package com.example.gym4u_movile_app

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gym4u_movile_app.entities.Comment
import com.example.gym4u_movile_app.entities.Post

class PostsAdapter(var posts: ArrayList<Post>): RecyclerView.Adapter<PostPrototype>() {
    // Mostrar la data en el prototype
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostPrototype {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_post, parent, false)
        return PostPrototype(view)
    }

    // Tamaño
    override fun getItemCount(): Int {
        return posts.size
    }

    // Vincular la data con el adapter
    override fun onBindViewHolder(holder: PostPrototype, position: Int) {
        holder.bind(posts.get(position))
    }
}

class PostPrototype(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val context: Context = itemView.context

    val tvUsername = itemView.findViewById<TextView>(R.id.tvUsername)
    val tvEmail = itemView.findViewById<TextView>(R.id.tvEmail)
    val ivImage = itemView.findViewById<ImageView>(R.id.ivImage)
    val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
    val tvNumComments = itemView.findViewById<TextView>(R.id.tvNumComments)
    val lyComments = itemView.findViewById<LinearLayout>(R.id.llComment)
    //val btnComment = itemView.findViewById<Button>(R.id.btnComment)

    public fun bind(post: Post) {
        tvUsername.text = post.user.username
        tvEmail.text = post.user.email
        Glide.with(itemView)
            .load(post.urlImage)
            .placeholder(R.drawable.ic_search)
            .into(ivImage)
        tvDescription.text = post.description
        tvNumComments.text = "${post.comments.size} comentarios"

        ivImage.setOnClickListener {
            showComments(post.comments);
        }
        //btnComment.setOnClickListener {
            // Lógica para agregar comentario
        //}
        tvNumComments.setOnClickListener {

        }
    }

    private fun showComments(comments: List<Comment>) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setTitle("Comentarios")
        dialogBuilder.setPositiveButton("Cerrar", null)

        // Crear una lista de strings con los comentarios
        val commentStrings = comments.map { comment ->
            "${comment.user.username}: ${comment.review}"
        }

        // Convertir la lista de strings a un array de caracteres
        val commentArray = commentStrings.toTypedArray()

        // Configurar el diálogo para mostrar los comentarios
        dialogBuilder.setItems(commentArray, null)

        // Mostrar el diálogo
        val dialog = dialogBuilder.create()
        dialog.show()
    }
}