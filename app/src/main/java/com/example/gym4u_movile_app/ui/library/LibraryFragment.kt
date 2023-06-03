package com.example.gym4u_movile_app.ui.library

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.gym4u_movile_app.LibraryAdapter
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.databinding.FragmentLibraryBinding
import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.Library
import com.example.gym4u_movile_app.entities.Post
import com.example.gym4u_movile_app.entities.Tag
import com.example.gym4u_movile_app.services.LibraryService
import com.example.gym4u_movile_app.services.PostService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val ARG_PARAM1 = "param1"
const val ARG_PARAM2 = "param2"
const val ARG_PARAM3 = "param3"
const val ARG_PARAM4 = "param4"
/**
 * A simple [Fragment] subclass.
 * Use the [LibraryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding?= null

    private val binding get()= _binding!!

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    private var param4: String? = null


    var library= ArrayList<Library>()
    var libraryadapter= LibraryAdapter(library)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //MOSTRAR POPUP
        val btnpopup= binding.btAddexcercise
        btnpopup.setOnClickListener {
            val intent = Intent(requireContext(), addexercise_popup::class.java)
            startActivity(intent)
        }


        initview()
        loadlibrary()

        return root

    }

    private fun initview() {
        val rvLibrary = binding.rvlibrary

       // Configurar el LayoutManager
        val layoutManager = LinearLayoutManager(context)
        rvLibrary.layoutManager = layoutManager

        //Configurar el adaptador
        rvLibrary.adapter = libraryadapter



    }

    private fun loadlibrary() {
        /*library.add(Library(1,"sdsdsdsds","asd","Asds","https://i.pinimg.com/originals/10/4c/5f/104c5f7ea76d834b4a7c730de66cb580.gif",Tag(1,"sd")))
        library.add(Library(1,"as","asd","Asds","https://i.pinimg.com/originals/77/d1/81/77d18189c5f1a74180eb0fe8b2a426fa.gif",Tag(1,"sd")))
        library.add(Library(1,"as","asd","Asds","https://i.pinimg.com/originals/06/dd/ab/06ddaba445f28cfba01fdca4f0d36286.gif",Tag(1,"sd")))
        library.add(Library(1,"as","asd","Asds","https://i.pinimg.com/originals/af/28/f5/af28f56592f20b94b085a588a66db44f.gif",Tag(1,"sd")))
*/

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gym4u-api-388317.rj.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val libraryService: LibraryService = retrofit.create(LibraryService::class.java)

        val request = libraryService.getAll()

        request.enqueue(object: Callback<BaseResponse<Library>> {
            override fun onResponse(
                call: Call<BaseResponse<Library>>,
                response: Response<BaseResponse<Library>>
            ) {
                response.body()!!.content.forEach {
                    library.add(it)
                }

                libraryadapter.notifyDataSetChanged()
                Log.d("Library: ", library[0].id.toString())
            }

            override fun onFailure(call: Call<BaseResponse<Library>>, t: Throwable) {
                Log.d("libraryAtivity", t.toString())
            }
        })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LibraryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LibraryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}