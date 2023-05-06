package com.example.gym4u_movile_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.ui.clients.Client
import com.example.gym4u_movile_app.ui.clients.ClientAdapter

class ClientsFragment : AppCompatActivity() {

    var clients = ArrayList<Client>()
    var clientAdapter = ClientAdapter(clients)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_clients)

        loadClients()
        initView()

    }

    private fun initView() {
        val rvClients = findViewById<RecyclerView>(R.id.rvClients)

        rvClients.adapter = clientAdapter
        rvClients.layoutManager = LinearLayoutManager(this)
    }

    private fun loadClients() {
        clients.add(Client("Juan Pablo Palacios"))
        clients.add(Client("Breydi Ramos"))
        clients.add(Client("Susana Villaran"))
        clients.add(Client("Alberto Fujimori"))
        clients.add(Client("Keiko Fujimori"))
    }
}