package com.example.gym4u_movile_app.ui.clients

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.MainActivity
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.databinding.FragmentClientBinding
import com.example.gym4u_movile_app.databinding.FragmentClientsBinding
import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.Client
import com.example.gym4u_movile_app.entities.ClientWorkout
import com.example.gym4u_movile_app.services.ClientWorkoutService
import kotlinx.coroutines.internal.artificialFrame
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientFragment : Fragment() {

    lateinit var rootView: View
    lateinit var tvClientName: TextView
    lateinit var btAssign: Button
    lateinit var rvClientWorkout: RecyclerView
    private var clientId: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.fragment_client, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvClientWorkout = view.findViewById<RecyclerView>(R.id.rvClientWorkout)
        btAssign = view.findViewById<Button>(R.id.btAssign)
        tvClientName = view.findViewById<TextView>(R.id.tvClientName)



        val clientObject = arguments?.getSerializable("client") as? Client

        if (clientObject != null) {
            val fullName = "${clientObject.name} ${clientObject.lastName}"
            tvClientName.text = fullName
            clientId = clientObject.id
        } else {
            tvClientName.text = "No se encontró ningún cliente"
        }

        loadWorkouts(view.context)

        btAssign.setOnClickListener {

            val assignWorkout = AssignWorkout()
            val args = Bundle()
            args.putSerializable("client", clientObject)
            assignWorkout.arguments = args

            val navController = Navigation.findNavController(btAssign)
            navController.navigate(R.id.action_clientFragment_to_assignWorkout,args)
        }

    }

    private fun loadWorkouts(context: Context?) {

        val clientIdLong: Long = clientId?.toLong() ?:0L

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.18.26:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val clientWorkoutService: ClientWorkoutService
        clientWorkoutService = retrofit.create(ClientWorkoutService::class.java)
        val request = clientWorkoutService.getWorkouts(clientIdLong)

        request.enqueue(object : Callback<BaseResponse<ClientWorkout>>{
            override fun onResponse(call: Call<BaseResponse<ClientWorkout>>, response: Response<BaseResponse<ClientWorkout>>) {
                if(response.isSuccessful){
                    val clientWorkouts: List<ClientWorkout> = response.body()!!.content
                    rvClientWorkout.adapter = ClientWorkoutAdapter(clientWorkouts)
                    rvClientWorkout.layoutManager = LinearLayoutManager(context)
                }
            }

            override fun onFailure(call: Call<BaseResponse<ClientWorkout>>, t: Throwable) {
                Log.d("Activity Fail", "Error: "+t.toString())
            }

        })



    }

    private fun initClient(){
        val clientObject = arguments?.getSerializable("client") as? Client

        if (clientObject != null) {
            val fullName = "${clientObject.name} ${clientObject.lastName}"
            tvClientName.text = fullName
            clientId = clientObject.id
        } else {
            tvClientName.text = "No se encontró ningún cliente"
        }
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