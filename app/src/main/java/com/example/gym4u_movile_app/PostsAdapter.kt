package com.example.gym4u_movile_app

import CommentsAdapter
import android.app.AlertDialog
import android.content.Context
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.Comment
import com.example.gym4u_movile_app.entities.Post
import com.example.gym4u_movile_app.entities.resources.CreateCommentResource
import com.example.gym4u_movile_app.services.CommentService
import com.example.gym4u_movile_app.util.AppPreferences
import com.example.gym4u_movile_app.util.RetrofitBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    private val commentService = RetrofitBuilder.build().create(CommentService::class.java)
    private lateinit var preferences: AppPreferences

    private val tvUsername = itemView.findViewById<TextView>(R.id.tvUsername)
    private val tvEmail = itemView.findViewById<TextView>(R.id.tvEmail)
    private val ivImage = itemView.findViewById<ImageView>(R.id.ivImage)
    private val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
    private val tvNumComments = itemView.findViewById<TextView>(R.id.tvNumComments)

    fun bind(post: Post) {
        preferences = AppPreferences(context)

        tvUsername.text = post.user!!.username
        tvEmail.text = post.user.email
        Glide.with(itemView)
            .load(post.urlImage)
            .placeholder(R.drawable.ic_search)
            .into(ivImage)
        tvDescription.text = post.description
        tvNumComments.text = "${post.comments?.size} comentarios"

        // Hacer clic en la imagen del post para expandir/colapsar comentarios
        ivImage.setOnClickListener {
            //post?.comments?.let { it -> showCommentsModal(it) }
            getComments(post)
        }
    }

    private fun getComments(post: Post) {
        commentService
            .getComments(post.id)
            .enqueue(object : Callback<BaseResponse<Comment>> {
                override fun onResponse(
                    call: Call<BaseResponse<Comment>>,
                    response: Response<BaseResponse<Comment>>
                ) {
                    if(response.isSuccessful)
                        response.body()?.content?.let { showCommentsModal(it, post) }
                }

                override fun onFailure(call: Call<BaseResponse<Comment>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    private fun showCommentsModal(comments: List<Comment>, post: Post) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.comments_modal, null)
        val rvComments = dialogView.findViewById<RecyclerView>(R.id.rvComments)
        val etComment = dialogView.findViewById<EditText>(R.id.etFilter)

        val commentsAdapter = CommentsAdapter()
        commentsAdapter.setComments(comments)
        rvComments.adapter = commentsAdapter

        rvComments.layoutManager = LinearLayoutManager(context)

        val dialogBuilder = AlertDialog.Builder(context, R.style.NoMarginDialog)
            .setView(dialogView)

        val dialog = dialogBuilder.create()

        // Configurar la gravedad del Window para colocarlo en la parte inferior
        val window = dialog.window
        val layoutParams = window?.attributes
        layoutParams?.gravity = Gravity.BOTTOM
        window?.attributes = layoutParams

        // Establecer el fondo superior con bordes redondeados utilizando el drawable personalizado
        window?.setBackgroundDrawableResource(R.drawable.dialog_rounded)

        etComment.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val user = preferences.getUser()

                val commentText = etComment.text.toString()

                val createCommentResource = CreateCommentResource(commentText, post)

                user.token?.let {
                    commentService
                        .createComment("Bearer " + it, createCommentResource)
                        .enqueue(object : Callback<Comment> {
                            override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
                                if (response.isSuccessful) {
                                    val newComment = response.body() // Obtener el comentario creado
                                    if (newComment != null) {
                                        commentsAdapter.addComment(newComment) // Agregar el comentario a la lista
                                        // Actualizar la vista del RecyclerView si es necesario
                                        rvComments.scrollToPosition(commentsAdapter.itemCount - 1)
                                    }

                                    etComment.text.clear()
                                    Toast.makeText(context, "EXITO", Toast.LENGTH_SHORT).show()
                                } else {
                                    // Error al crear el comentario, manejar el error de acuerdo a tus necesidades
                                    Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<Comment>, t: Throwable) {
                                // Error en la comunicación con el servidor, manejar el error de acuerdo a tus necesidades
                                Toast.makeText(context, "RESPONSE FAILURE", Toast.LENGTH_SHORT).show()
                            }
                        })
                }


                return@setOnKeyListener true
            }
            false
        }

        dialog.show()
    }

}