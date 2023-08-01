package com.example.loginmvpmvvm.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginmvpmvvm.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        intent?.getStringExtra("id")?.let { id ->
            supportFragmentManager.beginTransaction().add(
                R.id.contentFragment,
                HomeFragment.newInstance(id)
            ).commit()
        }
    }
}