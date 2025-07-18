package edu.ucne.freimyhidalgo_ap2_p2.presentation.navegation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.toRoute
import edu.ucne.freimyhidalgo_ap2_p2.presentation.colaboradores.ColaboradorListScreen
import edu.ucne.freimyhidalgo_ap2_p2.presentation.repositoryy.RepositoryListScreen


@Composable
fun HostNavigation(
    navHostController: NavHostController,

    ) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    NavHost(
        navController = navHostController,
        startDestination = Screen.RepositoryList
    ) {

        composable<Screen.RepositoryList> {
            RepositoryListScreen(
                goToRepository = { id ->
                    navHostController.navigate(Screen.Repository(null))
                },
                createRepository = {
                    navHostController.navigate(Screen.Repository(null))
                },
                drawerState = drawerState,
                scope = scope,

                goToContributors = { owner, repoName ->
                    navHostController.navigate(Screen.ColaboradorList(owner, repoName))
                }

            )
        }

        composable<Screen.ColaboradorList> { backStackEntry ->
            val owner = backStackEntry.toRoute<Screen.ColaboradorList>().owner
            val repo = backStackEntry.toRoute<Screen.ColaboradorList>().repo

            ColaboradorListScreen(
                owner = owner,
                repo = repo,
                onBack = { navHostController.popBackStack() }
            )
            }

        }

}

