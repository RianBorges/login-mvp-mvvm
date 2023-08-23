package com.example.loginmvpmvvm.ui.access

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.loginmvpmvvm.R
import com.example.loginmvpmvvm.adapters.ViewPagerAdapter
import com.example.loginmvpmvvm.databinding.FragmentAccessBinding
import com.example.loginmvpmvvm.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class AccessFragment : Fragment() {

    private var _binding: FragmentAccessBinding? = null
    private val binding get() = _binding!!
    var mail = ""
    var pass = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccessBinding.inflate(inflater, container, false)
        setUpWithTabs()
        return binding.root
    }
    fun setUpWithTabs(){
        val adapter = ViewPagerAdapter(parentFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position ->
            when (position){
                0 ->{
                    tab.text = "Login"
                }
                1 ->{
                    tab.text = "Cadastro"
                }
            }
        }.attach()
    }

    fun openSignIn(mail: String, pass: String) {
        this.mail = mail
        this.pass = pass
        binding.viewPager.currentItem = 0
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}