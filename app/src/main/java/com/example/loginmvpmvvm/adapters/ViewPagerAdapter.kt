package com.example.loginmvpmvvm.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.loginmvpmvvm.ui.access.signup.SignUpFragment
import com.example.loginmvpmvvm.ui.access.login.LoginFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    var listFragments: List<Fragment> = listOf()
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return listFragments[position]
    }
}