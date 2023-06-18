package com.example.gym4u_movile_app.ui.clients

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.R
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

class ClientsFragment : Fragment(), ClientAdapter.OnItemClickListener {

    lateinit var clientRecyclerView: RecyclerView
    private var currentFragment: Fragment? = null

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

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.18.26:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val clientService: ClientService
        clientService = retrofit.create(ClientService::class.java)
        val request = clientService.getClients()

        request.enqueue(object : Callback<BaseResponse<Client>>{
            override fun onResponse(call: Call<BaseResponse<Client>>, response: Response<BaseResponse<Client>>) {
                if(response.isSuccessful){
                    val clients: List<Client> = response.body()!!.content
                    clientRecyclerView.adapter = ClientAdapter(clients, this@ClientsFragment)
                    clientRecyclerView.layoutManager = LinearLayoutManager(context)
                }
            }

            override fun onFailure(call: Call<BaseResponse<Client>>, t: Throwable) {
                Log.d("Activity Fail", "Error: "+t.toString())
            }

        })
    }

    override fun OnItemClicked(client: Client) {


        val clientFragment = ClientFragment()
        val bundle = Bundle().apply {
            putSerializable("client", client)
        }
        clientFragment.arguments = bundle

        /*
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.container, clientFragment)
            .addToBackStack(null)
            .commit()

         */

        /*
        val newFragment = ClientFragment.newInstance(client)
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, newFragment)
        fragmentTransaction.commit()

         */


    }


}