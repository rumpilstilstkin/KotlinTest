package com.example.rumpilstilstkin.kotlintest.screens

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.rumpilstilstkin.kotlintest.R
import com.example.rumpilstilstkin.kotlintest.screens.fragments.ExampleColorFragment
import com.example.rumpilstilstkin.kotlintest.screens.fragments.ExampleListFragment
import com.example.rumpilstilstkin.kotlintest.screens.home.HomeScreens
import com.example.rumpilstilstkin.kotlintest.utils.fragmentTag
import kotlinx.android.synthetic.main.activity_main_bottom.navigation


///////////////////////////////////////////////////////////////////////////
// Main Bottom Activity
///////////////////////////////////////////////////////////////////////////

class MainBottomActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_bottom)

        if (savedInstanceState != null) {
            current = savedInstanceState.getSerializable(EXTRA_SHOW_SCREEN) as? HomeScreens ?: HomeScreens.DEFAULT_SCREEN
        }

        navigation.setOnNavigationItemSelectedListener(this)

        navigation.selectedItemId = current.itemId
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

    ///////////////////////////////////////////////////////////////////////////
    // Actions
    ///////////////////////////////////////////////////////////////////////////

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_first -> selectScreen(HomeScreens.FIRST)
            R.id.action_second -> selectScreen(HomeScreens.SECOND)
            R.id.action_third -> selectScreen(HomeScreens.THIRD)
        }
        return true
    }

    ///////////////////////////////////////////////////////////////////////////
    // Navigators
    ///////////////////////////////////////////////////////////////////////////

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
}
