package com.example.gym4u_movile_app.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.databinding.ActivityTermsAndConditionsBinding
import com.example.gym4u_movile_app.ui.register.adapter.TermsAndConditionsAdapter

class TermsAndConditionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTermsAndConditionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsAndConditionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTermsAndConditions.layoutManager = LinearLayoutManager(this)
        binding.rvTermsAndConditions.adapter = TermsAndConditionsAdapter(getTerms())

    }

    private fun getTerms(): List<Pair<String, String>> {

        val terms = resources.getStringArray(R.array.terms_and_conditions)
        val pairs = ArrayList<Pair<String, String>>()
        for(i in 0..terms.size - 2 step 2)
            pairs.add(Pair(terms[i].trim(), terms[i + 1]))

        return pairs
    }

}