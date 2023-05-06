package com.example.gym4u_movile_app.ui.clients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.databinding.FragmentClientsBinding
import com.example.gym4u_movile_app.ui.clients.Client
import com.example.gym4u_movile_app.ui.clients.ClientAdapter
class ClientsFragment : Fragment() {

    private var _binding: FragmentClientsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var clients = ArrayList<Client>()
    var clientAdapter = ClientAdapter(clients)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClientsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rootView = inflater.inflate(R.layout.fragment_clients, container, false)
        val rvClients = rootView.findViewById<RecyclerView>(R.id.rvClients)

        loadClients()
        initView()

        return root
    }

    private fun initView() {
        val rvClients = binding.rvClients

        // Configurar el LayoutManager
        val layoutManager = LinearLayoutManager(context)
        rvClients.layoutManager = layoutManager

        rvClients.adapter = clientAdapter
    }

    private fun loadClients() {
        clients.add(Client("Juan Pablo Palacios"))
        clients.add(Client("Breydi Ramos"))
        clients.add(Client("Susana Villaran"))
        clients.add(Client("Alberto Fujimori"))
        clients.add(Client("Keiko Fujimori"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}