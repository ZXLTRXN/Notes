package com.example.feature.navigation

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import java.io.Serializable


const val NAVIGATION_DATA_KEY = "NAVIGATION_DATA_KEY"
fun Fragment.navigate(actionId: Int, data: Serializable? = null, hostId: Int? = null) {
    val navController = if (hostId == null) {
        findNavController()
    } else {
        Navigation.findNavController(requireActivity(), hostId)
    }

    val bundle = Bundle().apply { putSerializable(NAVIGATION_DATA_KEY, data) }

    navController.navigate(actionId, bundle)
}

inline fun <reified T : Serializable> Fragment.getNavigationData(): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        arguments?.getSerializable(NAVIGATION_DATA_KEY, T::class.java)
    else @Suppress("DEPRECATION") arguments?.getSerializable(NAVIGATION_DATA_KEY) as? T
