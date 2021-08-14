package com.r4zielchicago.android.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.r4zielchicago.android.myapplication.databinding.ActivityMainBinding
import com.r4zielchicago.android.myapplication.ui.HeroFragment
import com.r4zielchicago.android.myapplication.ui.HeroViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.hero_fragment_container,
                    HeroFragment.newInstance(),
                    HeroFragment::class.java.simpleName
                )
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}