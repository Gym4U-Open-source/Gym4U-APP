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
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.models.BaseResponse
import com.example.gym4u_movile_app.models.Client
import com.example.gym4u_movile_app.services.ClientService
import com.example.gym4u_movile_app.util.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClientsFragment : Fragment() {

    lateinit var clientRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_clients,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clientRecyclerView = view.findViewById<RecyclerView>(R.id.rvClients)
        loadClients(view.context)
    }

    private fun loadClients(context: Context) {

        val retrofit = RetrofitBuilder.build()

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

}