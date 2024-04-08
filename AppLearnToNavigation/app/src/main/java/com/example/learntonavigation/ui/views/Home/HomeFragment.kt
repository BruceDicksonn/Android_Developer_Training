package com.example.learntonavigation.ui.views.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.children
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.example.learntonavigation.R
import com.example.learntonavigation.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startNavigation()
    }

    fun startNavigation() {

        binding.containerButtons.children.forEach {
            if (it is Button) {
                val button = it as Button
                val nameID = resources.getResourceEntryName(button.id)

                val route = when (nameID) {
                    "moviesFragment" -> R.id.moviesFragment
                    "settingsFragment" -> R.id.settingsFragment
                    else -> -1
                }

                button.setOnClickListener {
                    findNavController()
                        .navigate(route)
                }
            }
        }

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}