package com.topibatu.sibarong

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.topibatu.sibarong.database.entity.HistoryEntity
import com.topibatu.sibarong.databinding.ActivityMainBinding
import com.topibatu.sibarong.databinding.FragmentAnalyzeBinding

class MainActivity : AppCompatActivity() {

    private lateinit var dataHistory: HistoryEntity
    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        navigationView = binding.bottomNavigation

        val navigationController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_analyze, R.id.navigation_history))
        setupActionBarWithNavController(navigationController, appBarConfiguration)
        navigationView.setupWithNavController(navigationController)
    }

    private fun moveNavigation(){
        val view: View = navigationView.findViewById(R.id.navigation_analyze)
        view.performClick()
    }

    fun receiveHistory(data: HistoryEntity){
        moveNavigation()
        dataHistory = HistoryEntity(data.textNews, data.result)
    }

    fun applyToFragment(fragmentBinding: FragmentAnalyzeBinding): Boolean{
        return if(::dataHistory.isInitialized){
            fragmentBinding.newsText.setText(dataHistory.textNews)
            fragmentBinding.percentage.text = StringBuilder().append(dataHistory.result).append("%")
            true
        }else{
            false
        }
    }
}