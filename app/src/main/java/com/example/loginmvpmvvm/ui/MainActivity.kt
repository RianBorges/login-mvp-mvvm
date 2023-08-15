package com.example.loginmvpmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginmvpmvvm.adapters.ViewPagerAdapter
import com.example.loginmvpmvvm.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var mail  = ""
    var pass = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpWithTabs()
    }

    fun setUpWithTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
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

}
