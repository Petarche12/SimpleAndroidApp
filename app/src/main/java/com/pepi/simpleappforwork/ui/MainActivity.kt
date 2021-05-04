package com.pepi.simpleappforwork.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.pepi.simpleappforwork.R
import com.pepi.simpleappforwork.databinding.ActivityMainBinding
import com.pepi.simpleappforwork.ui.terms.TermsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //da nema up button i vo all fragment
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment2, R.id.allFragment)
        )


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
        navController = navHostFragment!!.findNavController()

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return if (item.itemId == R.id.termsAndConditions) {
            navController.navigate(TermsFragmentDirections.actionGlobalTermsFragment())
            true
        } else item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    fun setToolbarVisibility(boolean: Boolean) {
        if (boolean) {
            binding.toolbar.visibility = View.VISIBLE
        } else {
            binding.toolbar.visibility = View.GONE
        }
    }

    fun setBottomNavVisibility(boolean: Boolean) {
        if (boolean) {
            binding.bottomNav.visibility = View.VISIBLE
        } else {
            binding.bottomNav.visibility = View.GONE
        }
    }

}