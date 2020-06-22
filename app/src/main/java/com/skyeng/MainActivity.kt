package com.skyeng

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.skyeng.fragment.SearchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment, SearchFragment.newInstance())
                .commit()
        }
    }

    fun navigate(f: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, f)
            .addToBackStack(null)
            .commit()
    }
}