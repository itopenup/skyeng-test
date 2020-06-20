package com.skyeng

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skyeng.fragment.SearchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment, SearchFragment.newInstance())
            .commit()
    }
}