package com.example.gym4u_movile_app

import CommentsAdapter
import android.app.AlertDialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gym4u_movile_app.entities.Comment
import com.example.gym4u_movile_app.entities.Post
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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

class PostPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val context: Context = itemView.context

    private val tvUsername = itemView.findViewById<TextView>(R.id.tvUsername)
    private val tvEmail = itemView.findViewById<TextView>(R.id.tvEmail)
    private val ivImage = itemView.findViewById<ImageView>(R.id.ivImage)
    private val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
    private val tvNumComments = itemView.findViewById<TextView>(R.id.tvNumComments)
    private val llComments = itemView.findViewById<LinearLayout>(R.id.llComments)
    private val tilComment = itemView.findViewById<TextInputLayout>(R.id.tilComment)
    //private val etComment = itemView.findViewById<TextInputEditText>(R.id.etComment)
    //private val btnAddComment = itemView.findViewById<Button>(R.id.btnAddComment)

    private val commentsAdapter = CommentsAdapter()
    private val commentsList: MutableList<Comment> = mutableListOf()

    init {
        val rvComments = itemView.findViewById<RecyclerView>(R.id.rvComments)
        rvComments.layoutManager = LinearLayoutManager(itemView.context)
        rvComments.adapter = commentsAdapter
    }

    private fun toggleComments(comments: List<Comment>) {
        if (llComments.visibility == View.VISIBLE) {
            llComments.visibility = View.GONE
            commentsList.clear()
            commentsAdapter.setComments(commentsList)
        } else {
            llComments.visibility = View.VISIBLE
            commentsList.addAll(comments)
            commentsAdapter.setComments(commentsList)
        }
    }
    fun bind(post: Post) {
        tvUsername.text = post.user.username
        tvEmail.text = post.user.email
        Glide.with(itemView)
            .load(post.urlImage)
            .placeholder(R.drawable.ic_search)
            .into(ivImage)
        tvDescription.text = post.description
        tvNumComments.text = "${post.comments.size} comentarios"

        // Mostrar u ocultar comentarios
        if (post.comments.isNotEmpty()) {
            llComments.visibility = View.VISIBLE
            commentsAdapter.setComments(post.comments)
        } else {
            llComments.visibility = View.GONE
        }

        // Hacer clic en la imagen del post para expandir/colapsar comentarios
        ivImage.setOnClickListener {
            showCommentsModal(post.comments)
        }

        // Agregar comentario
        //btnAddComment.setOnClickListener {
            /*val commentText = etComment.text.toString()
            if (commentText.isNotEmpty()) {
                val comment = Comment(
                    id = generateCommentId(),
                    review = commentText,
                    user = getCurrentUser(),
                    post = post
                )
                commentsAdapter.addComment(comment)
                etComment.text?.clear()
            }*/
        //}
    }
    private fun showCommentsModal(comments: List<Comment>) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.comments_modal, null)
        val rvComments = dialogView.findViewById<RecyclerView>(R.id.rvComments)

        rvComments.layoutManager = LinearLayoutManager(context)
        rvComments.adapter = CommentsAdapter().apply { setComments(comments) }

        val dialogBuilder = AlertDialog.Builder(context, R.style.NoMarginDialog)
            .setView(dialogView)
            .setPositiveButton("Cerrar", null)

        val dialog = dialogBuilder.create()

        // Configurar la gravedad del Window para colocarlo en la parte inferior
        val window = dialog.window
        val layoutParams = window?.attributes
        layoutParams?.gravity = Gravity.BOTTOM
        window?.attributes = layoutParams

        // Establecer el fondo superior con bordes redondeados utilizando el drawable personalizado
        window?.setBackgroundDrawableResource(R.drawable.dialog_rounded)

        dialog.show()
    }

}