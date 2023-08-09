package com.example.core.navigation

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController


const val NAVIGATION_DATA_KEY = "NAVIGATION_DATA_KEY"
fun Fragment.navigate(actionId: Int, data: Parcelable? = null, hostId: Int? = null) {
    val navController = if (hostId == null) {
        findNavController()
    } else {
        Navigation.findNavController(requireActivity(), hostId)
    }

    val bundle = Bundle().apply { putParcelable(NAVIGATION_DATA_KEY, data) }

    navController.navigate(actionId, bundle)
}

inline fun <reified T : Parcelable> Fragment.getNavigationData(): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        arguments?.getParcelable(NAVIGATION_DATA_KEY, T::class.java)
    else @Suppress("DEPRECATION") arguments?.getParcelable(NAVIGATION_DATA_KEY) as? T
