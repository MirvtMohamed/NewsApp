package com.example.newsapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import android.widget.Toast

class SettingsFragment : Fragment() {

    private lateinit var radioGroupCountries: RadioGroup
    private lateinit var buttonSave: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // Initialize the views
        radioGroupCountries = view.findViewById(R.id.radioGroupCountries)
        buttonSave = view.findViewById(R.id.buttonSave)
        sharedPreferences = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

        // Load saved country choice and set it
        val savedCountry = sharedPreferences.getString("selectedCountry", "")
        if (savedCountry != null && savedCountry.isNotEmpty()) {
            when (savedCountry) {
                "Egypt" -> radioGroupCountries.check(R.id.radioButtonEgypt)
                "USA" -> radioGroupCountries.check(R.id.radioButtonUSA)
                "Canada" -> radioGroupCountries.check(R.id.radioButtonCanada)
            }
        }

        // Save button listener
        buttonSave.setOnClickListener {
            val selectedRadioButtonId = radioGroupCountries.checkedRadioButtonId
            if (selectedRadioButtonId != -1) {
                val selectedCountry = view.findViewById<RadioButton>(selectedRadioButtonId).text.toString()

                // Save selected country in SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("selectedCountry", selectedCountry)
                editor.apply()

                Toast.makeText(requireContext(), "Country saved: $selectedCountry", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please select a country", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
