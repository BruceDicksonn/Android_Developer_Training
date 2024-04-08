package com.example.learntonavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.learntonavigation.databinding.FragmentContentMainBinding
import com.example.learntonavigation.databinding.FragmentHomeBinding
import com.example.learntonavigation.ui.views.Home.HomeFragment
import com.example.learntonavigation.ui.views.Movies.MoviesFragment
import com.example.learntonavigation.ui.views.Settings.SettingsFragment
import com.example.learntonavigation.ui.views.commons.FragmentPageAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ContentMain() : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentContentMainBinding

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

        binding = FragmentContentMainBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillTabs()
    }

    private fun fillTabs(){

        val tabLayout = binding.tabLayout
        val adapter = FragmentPageAdapter(childFragmentManager)

        val home = HomeFragment()
        val movie = MoviesFragment()
        val settings = SettingsFragment()

        adapter.add(home, "Home")
        adapter.add(movie, "Movies")
        adapter.add(settings, "Settings")

        binding.viewPager.adapter = adapter
        tabLayout.setupWithViewPager( binding.viewPager )

    }

    companion object {

        fun newInstance(param1: String, param2: String, supportFragmentManager: FragmentManager) =
            ContentMain().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}