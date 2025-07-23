package com.hadeer.triviaapplication

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
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
import androidx.navigation.ui.NavigationUI
import com.hadeer.triviaapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    //nav controller
    private lateinit var navController : NavController
    //create Drawer
    private lateinit var drawer : DrawerLayout
    private lateinit var drawertoggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        //set toolbar
        setSupportActionBar(binding.toolbarInclude.applicationActionToolbar)
        //set drawer
        handleDrawer()
        // Setup Navigation and toggle between burger/back icons
        handleNavigation()
    }


    private fun handleDrawer(){
        drawer = binding.applicationDrawer
        // init action bar drawer toggle
        drawertoggle = ActionBarDrawerToggle(this, drawer,binding.toolbarInclude.applicationActionToolbar,  R.string.nav_open, R.string.nav_close)
        //add drawer listener
        drawer.addDrawerListener(drawertoggle)
        drawertoggle.syncState()
        //change the color of BurgerMenu
        drawertoggle.drawerArrowDrawable.color = ContextCompat.getColor(this, R.color.white)
    }

    private fun handleNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as? NavHostFragment

        navController = navHostFragment?.navController!!
        navController.addOnDestinationChangedListener{_,destination,_ ->
            if(destination.id == R.id.titleFragment){
                //show BurgerMenu
                drawertoggle.isDrawerIndicatorEnabled = true
                drawertoggle.setHomeAsUpIndicator(R.drawable.menu_icv)
                drawertoggle.syncState()
            }
            else{
                //show Back Arrow
                drawertoggle.isDrawerIndicatorEnabled = false
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                drawertoggle.setHomeAsUpIndicator(R.drawable.back_icv)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle drawer toggle click
        if (drawertoggle.isDrawerIndicatorEnabled && drawertoggle.onOptionsItemSelected(item)) {
            return true
        }
        // Handle back arrow
        if (item.itemId == R.id.titleFragment) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}