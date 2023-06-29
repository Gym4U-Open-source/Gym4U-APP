import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.databinding.PrototypeFollowersBinding
import com.example.gym4u_movile_app.models.Follower
import com.example.gym4u_movile_app.models.FollowerUser
import com.example.gym4u_movile_app.ui.inbox.InboxFragmentDirections
import com.example.gym4u_movile_app.util.UtilFn.Companion.toUTF8

class FollowersAdapter(
    private val followers: List<Follower>,
    private val senderIsCoach: Boolean
) : RecyclerView.Adapter<FollowersAdapter.Holder>() {
    inner class Holder(private val binding: PrototypeFollowersBinding) : RecyclerView.ViewHolder(binding.root) {

        private fun initFollower(user: FollowerUser) {
            val username = user.username.toUTF8()
            binding.tvChatAvatar.text = username[0].uppercase()
            binding.tvChatName.text = username
            binding.tvLastMessage.text = user.email.toUTF8()
        }
        private fun navigateFollower(follower: Follower): NavDirections {
            return if(senderIsCoach)
                InboxFragmentDirections.actionNavigationInboxToInboxNavigation(follower.coachUser, follower.clientUser)
            else InboxFragmentDirections.actionNavigationInboxToInboxNavigation(follower.clientUser, follower.coachUser, false)
        }
        fun bind(follower: Follower) {
            if(senderIsCoach)
                initFollower(follower.clientUser)
            else initFollower(follower.coachUser)

            val direction = navigateFollower(follower)
            binding.cvInbox.setOnClickListener { binding.root.findNavController().navigate(direction) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(PrototypeFollowersBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun getItemCount(): Int = followers.size
    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(followers[position])
}