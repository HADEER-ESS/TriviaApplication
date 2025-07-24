package com.hadeer.triviaapplication

import android.content.Context
import android.os.Bundle
import android.text.Layout.Alignment
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.hadeer.triviaapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    //nav controller
    private lateinit var navController : NavController
    private lateinit var appBarConfig : AppBarConfiguration
    //create Drawer
    private lateinit var navView : NavigationView
    private lateinit var drawer : DrawerLayout
    private lateinit var drawertoggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        // Get the NavController from the NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as? NavHostFragment
        navController = navHostFragment?.navController!!
        // Configure app bar to work with the drawer layout
        //                                  navigation graph,       drawer layout
        appBarConfig = AppBarConfiguration(navController.graph, binding.applicationDrawer)

        // Set up the custom toolbar as ActionBar
        setSupportActionBar(binding.toolbarInclude.applicationActionToolbar)
        //set drawer
        handleDrawer()
        // Setup Navigation and toggle between burger/back icons
        handleNavigation()
    }


    private fun handleDrawer(){
        navView = binding.applicationNavigationView
        drawer = binding.applicationDrawer
        // Initialize drawer toggle (hamburger ↔ back arrow)
        drawertoggle = ActionBarDrawerToggle(this,
            drawer,
            binding.toolbarInclude.applicationActionToolbar,
            R.string.nav_open,
            R.string.nav_close)
        //Listen for drawer open/close
        drawer.addDrawerListener(drawertoggle)
        // Sync toggle state with drawer
        drawertoggle.syncState()
        //change the color of BurgerMenu
        drawertoggle.drawerArrowDrawable.color = ContextCompat.getColor(this, R.color.white)
        // Display back arrow if needed
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Sync toolbar with NavController and AppBarConfiguration
        binding.toolbarInclude.applicationActionToolbar.setupWithNavController(navController,appBarConfig)

    }

    private fun handleNavigation() {
        binding.toolbarInclude.applicationActionToolbar.setTitleTextColor(resources.getColor(R.color.white))
        navController.addOnDestinationChangedListener{_,destination,_ ->
            if(destination.id == R.id.titleFragment){
                //show BurgerMenu
                drawertoggle.isDrawerIndicatorEnabled = true
                drawertoggle.syncState()
            }
            else{
                //to hide the burger menu from toolbar when navigate in
                drawertoggle.isDrawerIndicatorEnabled = false
                //display BACK ARROW instead of removed MENU
                drawertoggle.setHomeAsUpIndicator(R.drawable.back_icv)
            }
        }
        // Let NavigationView automatically handle navigation item clicks
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        // Navigate up in the NavController stack, fallback to default
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Let the drawer toggle handle the item
        return if(drawertoggle.onOptionsItemSelected(item)){
            true
        }
        else if(item.itemId == R.id.aboutFragment){
            navController.navigate(R.id.aboutFragment)
            true
        }
        else{
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return true
    }
}