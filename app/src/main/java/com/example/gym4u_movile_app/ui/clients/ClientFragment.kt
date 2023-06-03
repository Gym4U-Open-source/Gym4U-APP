package com.example.gym4u_movile_app.ui.clients

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.databinding.FragmentClientBinding
import com.example.gym4u_movile_app.databinding.FragmentClientsBinding
import com.example.gym4u_movile_app.entities.Client
import kotlinx.coroutines.internal.artificialFrame

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ClientFragment : Fragment() {

    lateinit var rootView: View
    lateinit var tvClientName: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.fragment_client, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvClientName = view.findViewById<TextView>(R.id.tvClientName)
        initClient(view.context)

    }

    private fun initClient(context: Context){
        val intent = requireActivity().intent

        //val clientObject: Client? = intent.getSerializableExtra("Client") as? Client
        val clientObject = arguments?.getSerializable("client") as? Client

        if (clientObject != null) {
            val fullName = "${clientObject.name} ${clientObject.lastName}"
            tvClientName.text = fullName
        } else {
            tvClientName.text = "No se encontró ningún cliente"
        }

        //tvClientName.text = clientObject?.name + " " + clientObject?.lastName

    }

    companion object {
        fun newInstance(client: Client): ClientFragment {
            val fragment = ClientFragment()
            val args = Bundle()
            args.putSerializable("client", client)
            fragment.arguments = args
            return fragment
        }
    }

}