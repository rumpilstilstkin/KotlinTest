package com.example.rumpilstilstkin.kotlintest.screens.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rumpilstilstkin.kotlintest.R

///////////////////////////////////////////////////////////////////////////
// Example Color Fragment
///////////////////////////////////////////////////////////////////////////

class ExampleColorFragment: Fragment(){

    ///////////////////////////////////////////////////////////////////////////
    // Lifecycle
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.fragment_colored, container, false)

}
