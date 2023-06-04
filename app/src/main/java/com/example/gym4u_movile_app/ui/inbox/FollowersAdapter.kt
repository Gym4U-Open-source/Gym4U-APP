import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.databinding.PrototypeFollowersBinding
import com.example.gym4u_movile_app.entities.Follower
import com.example.gym4u_movile_app.ui.inbox.InboxFragmentDirections
import com.example.gym4u_movile_app.util.UtilFn.Companion.toUTF8String

class FollowersAdapter(private val followers: List<Follower>) : RecyclerView.Adapter<FollowersAdapter.Holder>() {
    class Holder(private val binding: PrototypeFollowersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(follower: Follower) {
            val username = toUTF8String(follower.clientUser.username)
            binding.tvChatAvatar.text = username[0].uppercase()
            binding.tvChatName.text = username
            binding.tvLastMessage.text = toUTF8String(follower.clientUser.email)
            binding.cvInbox.setOnClickListener {
                binding.root.findNavController().navigate(InboxFragmentDirections.actionNavigationInboxToInboxNavigation(follower.coachUser, follower.clientUser))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(PrototypeFollowersBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun getItemCount(): Int = followers.size
    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(followers[position])
}