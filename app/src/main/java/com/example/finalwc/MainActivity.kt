package com.example.finalwc

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.finalwc.ui.settings.SettingsFragment
import com.example.finalwc.ui.station.StationFragment
import com.example.finalwc.ui.train.TrainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // android studio default bottom nav
//        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//        val navController = findNavController(R.id.nav_host_fragment)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_station, R.id.navigation_train, R.id.navigation_settings
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
    }

    // bottom nav from other source: https://code.tutsplus.com/tutorials/how-to-code-a-bottom-navigation-bar-for-an-android-app--cms-30305
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_station -> {
                val songsFragment = StationFragment.newInstance()
                openFragment(songsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_train -> {
                val trainFragment = TrainFragment.newInstance()
                openFragment(trainFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                val settingsFragment = SettingsFragment.newInstance()
                openFragment(settingsFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
