package com.example.multigame

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.multigame.ui.NumberGuessingGameScreen
import com.example.multigame.ui.TicTacToe
import com.example.multigame.ui.StartScreen
import com.example.multigame.ui.MathQuizGameScreen

enum class MultiGameScreen(val title: String) {
    Start(title = "Multi Game"),
    NumberGuessingGame(title = "Number Guessing Game"),
    MathQuizGame(title = "Math Quiz Game"),
    TicTacToeGame(title = "Tic Tac Toe"),
}

@Composable
fun MultiGameAppBar(
    currentScreen: MultiGameScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreen.title) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun MultiGameApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MultiGameScreen.valueOf(
        backStackEntry?.destination?.route ?: MultiGameScreen.Start.name
    )

    Scaffold(
        topBar = {
            MultiGameAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MultiGameScreen.Start.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = MultiGameScreen.Start.name) {
                StartScreen(
                    onNextButtonClicked = { game ->
                        if(game == 1) navController.navigate(MultiGameScreen.NumberGuessingGame.name)
                        else if (game == 2) navController.navigate(MultiGameScreen.MathQuizGame.name)
                        else navController.navigate(MultiGameScreen.TicTacToeGame.name)
                    }
                )
            }
            composable(route = MultiGameScreen.NumberGuessingGame.name) {
                NumberGuessingGameScreen()
            }

            composable(route = MultiGameScreen.MathQuizGame.name) {
                MathQuizGameScreen()
            }

            composable(route = MultiGameScreen.TicTacToeGame.name) {
                TicTacToe()
            }
        }
    }
}
