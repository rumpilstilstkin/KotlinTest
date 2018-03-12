package com.example.rumpilstilstkin.kotlintest.utils

import android.support.v4.app.Fragment

inline fun <reified T : Fragment> fragmentTag(): String = T::class.java.name
