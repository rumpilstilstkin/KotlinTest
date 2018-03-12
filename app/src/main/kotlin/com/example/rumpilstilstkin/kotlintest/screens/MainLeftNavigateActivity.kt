package com.example.rumpilstilstkin.kotlintest.screens

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.example.rumpilstilstkin.kotlintest.R
import com.example.rumpilstilstkin.kotlintest.screens.fragments.ExampleColorFragment
import com.example.rumpilstilstkin.kotlintest.screens.fragments.ExampleListFragment
import com.example.rumpilstilstkin.kotlintest.screens.home.HomeScreens
import com.example.rumpilstilstkin.kotlintest.utils.fragmentTag
import kotlinx.android.synthetic.main.activity_main_left.nav_drawer
import kotlinx.android.synthetic.main.activity_main_left.navigation_menu
import kotlinx.android.synthetic.main.activity_main_left.toolbar


///////////////////////////////////////////////////////////////////////////
// Main Left Activity
///////////////////////////////////////////////////////////////////////////

class MainLeftActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        private const val EXTRA_SCREEN_DATA = "screen_data_extra"
        private const val EXTRA_SHOW_SCREEN = "show_screen_extra"
    }

    ///////////////////////////////////////////////////////////////////////////
    // Properties
    ///////////////////////////////////////////////////////////////////////////

    private val FRAGMENT_TAG_FIRST = fragmentTag<ExampleListFragment>()
    private val FRAGMENT_TAG_SECOND = fragmentTag<ExampleColorFragment>()
    private val FRAGMENT_TAG_THIRD = fragmentTag<ExampleListFragment>()

    private var current: HomeScreens = HomeScreens.DEFAULT_SCREEN

    private val drawerListener = object : DrawerLayout.SimpleDrawerListener() {
        private var currentOffset = 0F
        private var updateRequested = false

        override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {
            if (slideOffset > currentOffset) {
                currentOffset = slideOffset
                if (!updateRequested) {
                    updateRequested = true
                }
            }
        }

        override fun onDrawerClosed(drawerView: View?) {
            currentOffset = 0F
            updateRequested = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_left)

        if (savedInstanceState != null) {
            current = savedInstanceState.getSerializable(EXTRA_SHOW_SCREEN) as? HomeScreens ?: HomeScreens.DEFAULT_SCREEN
        }
        initNavDrawer()
        selectScreen(current)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)

        current = intent.getBundleExtra(EXTRA_SHOW_SCREEN) as? HomeScreens ?: HomeScreens.DEFAULT_SCREEN
        selectScreen(current)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(EXTRA_SHOW_SCREEN, current)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        nav_drawer.removeDrawerListener(drawerListener)
        super.onDestroy()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Initializations
    ///////////////////////////////////////////////////////////////////////////

    private fun initNavDrawer() {

        setSupportActionBar(toolbar)

        nav_drawer.addDrawerListener(drawerListener)

        val toggle = ActionBarDrawerToggle(this, nav_drawer, toolbar, R.string.app_name, R.string.app_name)
        nav_drawer.addDrawerListener(toggle)
        toggle.syncState()

        navigation_menu.setNavigationItemSelectedListener(this)
    }


    ///////////////////////////////////////////////////////////////////////////
    // Navigators
    ///////////////////////////////////////////////////////////////////////////

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_first -> selectScreen(HomeScreens.FIRST)
            R.id.action_second -> selectScreen(HomeScreens.SECOND)
            R.id.action_third -> selectScreen(HomeScreens.THIRD)
        }
        closeDrawer()
        return true

    }

    private fun selectScreen(screen: HomeScreens){
        current = screen
        when(screen){
            HomeScreens.FIRST -> {
                placeFragment(FRAGMENT_TAG_FIRST)
            }
            HomeScreens.SECOND -> {
                placeFragment(FRAGMENT_TAG_SECOND)
            }
            HomeScreens.THIRD -> {
                placeFragment(FRAGMENT_TAG_THIRD)
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Helpers
    ///////////////////////////////////////////////////////////////////////////

    fun placeFragment(fragmentTag: String, args: Bundle? = null): Fragment? {
        if (isFinishing)
            return null

        val transaction = supportFragmentManager.beginTransaction()

        val fragment = Fragment.instantiate(this, fragmentTag, args)
        transaction.setCustomAnimations(
                android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_out, android.R.anim.fade_in)
        transaction.replace(R.id.container_fragments, fragment, fragmentTag)
        transaction.commit()

        return fragment
    }

    private fun closeDrawer(): Boolean =
            if (nav_drawer.isDrawerOpen(GravityCompat.START)) {
                nav_drawer.closeDrawer(GravityCompat.START)
                true
            }
            else false
}
