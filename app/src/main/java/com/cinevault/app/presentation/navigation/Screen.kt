package com.cinevault.app.presentation.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Search : Screen("search")
    data object Watchlist : Screen("watchlist")
    data object Profile : Screen("profile")
    data object Detail : Screen("detail/{movieId}") {
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
}
