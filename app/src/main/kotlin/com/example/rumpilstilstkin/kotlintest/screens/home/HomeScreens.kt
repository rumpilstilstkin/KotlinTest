package com.example.rumpilstilstkin.kotlintest.screens.home

import com.example.rumpilstilstkin.kotlintest.R


///////////////////////////////////////////////////////////////////////////
// Home Screens
///////////////////////////////////////////////////////////////////////////

enum class HomeScreens(val itemId: Int) {
    FIRST(R.id.action_first),
    SECOND(R.id.action_second),
    THIRD(R.id.action_third);
    companion object {
        val DEFAULT_SCREEN = FIRST
    }
}
