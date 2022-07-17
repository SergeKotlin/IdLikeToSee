package com.example.idliketosee.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.idliketosee.R
import com.example.idliketosee.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var toolbar:  Toolbar
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var appNavBar: NavigationView
    private lateinit var contentFragmentController: NavController
    private var navHomeFragment by Delegates.notNull<Int>() // todo Прилично так делать?
    private var navMovieSelectionFragment by Delegates.notNull<Int>() // todo -//-
    private lateinit var drawerLayout: DrawerLayout

    private var menuMain by Delegates.notNull<Int>() // todo -//-

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        setupToolbar()
        setupClickListeners()
    }

    private fun initViews() {
        //todo Как лучше объявить, если через binding не получилось (а точно ли через него не получится?)
        toolbar = binding.appBarMain.mainToolbar
        appNavBar = binding.appNavBar
        contentFragmentController = findNavController(R.id.content_main_fragment)
        navHomeFragment = R.id.home_screen
        navMovieSelectionFragment = R.id.movie_selection_screen
        drawerLayout = binding.drawerLayout
        menuMain = R.menu.menu_main

    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        appBarConfiguration = AppBarConfiguration(
            setOf( navHomeFragment,  navMovieSelectionFragment),
            drawerLayout
        )
        setupActionBarWithNavController(contentFragmentController, appBarConfiguration)
        appNavBar.setupWithNavController(contentFragmentController)
    }

    override fun onSupportNavigateUp(): Boolean { // Open side-bar from menu of toolbar
        return contentFragmentController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setupClickListeners() {
        binding.appBarMain.floActionBtn.setOnClickListener { view ->
            Snackbar.make(view, "Future action of Calendar", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show() // TODO
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(menuMain, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.first_action_menu_main -> { Toast.makeText(this,
                getString(R.string.first_action_menu_main), Toast.LENGTH_SHORT).show() }
        }
        return super.onOptionsItemSelected(item)
    }
}

/* Варианты месседжей:
● через Snackbar; // com.google.android.material:material:1.1.0
● через диалоговые окна;
● через отображения разных layout — менять их видимость в зависимости от состояния.  */