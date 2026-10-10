package com.cinevault.app.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cinevault.app.presentation.detail.DetailScreen
import com.cinevault.app.presentation.home.HomeScreen
import com.cinevault.app.presentation.profile.ProfileScreen
import com.cinevault.app.presentation.profile.TealAccent
import com.cinevault.app.presentation.search.SearchScreen
import com.cinevault.app.presentation.watchlist.WatchlistScreen
import kotlinx.coroutines.flow.SharedFlow

private data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
)

@Composable
fun CineVaultNavGraph(
    authApprovedFlow: SharedFlow<Unit>? = null
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavItems = listOf(
        BottomNavItem(Screen.Home.route, "Home", Icons.Default.Home),
        BottomNavItem(Screen.Search.route, "Search", Icons.Default.Search),
        BottomNavItem(Screen.Watchlist.route, "Watchlist", Icons.Default.Star),
        BottomNavItem(Screen.Profile.route, "Profile", Icons.Default.Person)
    )

    val showBottomBar = currentRoute in bottomNavItems.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                ) {
                    bottomNavItems.forEach { item ->
                        val selected = currentRoute == item.route
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = { Icon(item.icon, contentDescription = item.title) },
                            label = { Text(item.title) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = TealAccent,
                                selectedTextColor = TealAccent,
                                indicatorColor = Color.Transparent,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onMovieClick = { id -> navController.navigate(Screen.Detail.createRoute(id)) },
                    onSearchClick = { navController.navigate(Screen.Search.route) }
                )
            }

            composable(Screen.Search.route) {
                SearchScreen(onMovieClick = { id -> navController.navigate(Screen.Detail.createRoute(id)) })
            }

            composable(Screen.Watchlist.route) {
                WatchlistScreen(onMovieClick = { id -> navController.navigate(Screen.Detail.createRoute(id)) })
            }

            composable(Screen.Profile.route) {
                ProfileScreen(authApprovedFlow = authApprovedFlow)
            }

            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt("movieId") ?: return@composable
                DetailScreen(movieId = movieId, onBackClick = { navController.popBackStack() })
            }
        }
    }
}
