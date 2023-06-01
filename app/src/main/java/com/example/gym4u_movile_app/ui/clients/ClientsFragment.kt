package com.example.gym4u_movile_app.ui.clients

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.databinding.FragmentClientsBinding
import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.Client
import com.example.gym4u_movile_app.entities.Post
import com.example.gym4u_movile_app.services.ClientService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientsFragment : Fragment() {

    private var _binding: FragmentClientsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var clientRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentClientsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        clientRecyclerView = binding.rvClients
        loadClients()
        //initView()

        return root
    }

    /*
    private fun initView() {
        val rvClients = binding.rvClients

        // Configurar el LayoutManager
        val layoutManager = LinearLayoutManager(context)
        rvClients.layoutManager = layoutManager
        rvClients.adapter = clientAdapter
    }

     */

    private fun clientFragment() {

    }

    /*
    private fun loadClients() {
        clients.add(Client("Juan Pablo Palacios"))
        clients.add(Client("Breydi Ramos"))
        clients.add(Client("Susana Villaran"))
        clients.add(Client("Alberto Fujimori"))
        clients.add(Client("Keiko Fujimori"))
    }

     */

    private fun loadClients() {

        val context: Context = requireContext()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gym4u-api-388317.rj.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val clientService: ClientService
        clientService = retrofit.create(ClientService::class.java)
        val request = clientService.getClients()

        request.enqueue(object : Callback<BaseResponse<Client>>{
            override fun onResponse(call: Call<BaseResponse<Client>>, response: Response<BaseResponse<Client>>) {
                if(response.isSuccessful){
                    val clients: List<Client> = response.body()!!.content
                    clientRecyclerView.adapter = ClientAdapter(clients)
                    clientRecyclerView.layoutManager = LinearLayoutManager(context)
                }
            }

            override fun onFailure(call: Call<BaseResponse<Client>>, t: Throwable) {
                Log.d("Activity Fail", "Error: "+t.toString())
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}