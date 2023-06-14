import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.entities.Comment
import kotlin.random.Random

class CommentsAdapter : RecyclerView.Adapter<CommentViewHolder>() {
    private val comments: MutableList<Comment> = mutableListOf()

    fun setComments(comments: List<Comment>) {
        this.comments.clear()
        this.comments.addAll(comments)
        notifyDataSetChanged()
    }

    fun addComment(comment: Comment) {
        comments.add(comment)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.prototype_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]
        holder.bind(comment)
    }

    override fun getItemCount(): Int {
        return comments.size
    }
}

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvComment: TextView = itemView.findViewById(R.id.tvComment)
    private val tvCommentEmail: TextView = itemView.findViewById(R.id.tvCommentEmail)
    private val ivCircleAvatar: ImageView = itemView.findViewById(R.id.ivCircleAvatar)
    private val tvCircleAvatarText: TextView = itemView.findViewById(R.id.tvCircleAvatarText)

    fun bind(comment: Comment) {
        tvComment.text = comment.review
        tvCommentEmail.text = comment.user.email

        // Obtener las dos primeras letras del email
        val email = comment.user.email
        val initials = getInitials(email)

        // Establecer las iniciales como texto circular en el ImageView
        tvCircleAvatarText.text = initials

        // Crear un drawable con el fondo circular y color aleatorio
        val circleBackground = itemView.context.getDrawable(R.drawable.random_background) as GradientDrawable
        val randomColor = getRandomColor()
        circleBackground.setColor(randomColor)

        // Establecer el drawable como fondo del ImageView
        ivCircleAvatar.background = circleBackground
    }

    private fun getInitials(email: String): String {
        val parts = email.split("@")
        if (parts.size == 2) {
            val username = parts[0]
            return if (username.length >= 2) {
                username.substring(0, 2).toUpperCase()
            } else {
                username.toUpperCase()
            }
        }
        return ""
    }

    private fun getRandomColor(): Int {
        val random = Random.Default
        val r = random.nextInt(256)
        val g = random.nextInt(256)
        val b = random.nextInt(256)
        return Color.rgb(r, g, b)
    }

}
