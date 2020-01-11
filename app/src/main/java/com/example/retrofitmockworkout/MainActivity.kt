package com.example.retrofitmockworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.retrofitmockworkout.screens.CarsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            openFragment()
        }
    }

    private fun openFragment(fragment: Fragment = CarsFragment.newInstance()) {
        val backStack = supportFragmentManager.findFragmentById(R.id.fragmentContainer) != null

        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)

        if (backStack) {
            transaction.addToBackStack(fragment::javaClass.name)
        }

        transaction.commit()
    }
}
