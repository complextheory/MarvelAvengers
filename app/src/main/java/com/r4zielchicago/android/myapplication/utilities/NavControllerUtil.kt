package com.r4zielchicago.android.myapplication.utilities

import androidx.navigation.NavController
import androidx.navigation.NavDirections

class NavControllerUtil {
    val yes = true

    fun NavController.safeNavigate(direction: NavDirections) {
        currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
    }
}