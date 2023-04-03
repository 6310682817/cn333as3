package com.example.multigame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun TicTacToe() {
    var gameState by remember { mutableStateOf(listOf("", "", "", "", "", "", "", "", "")) }
    var player by remember { mutableStateOf("X") }
    var winner by remember { mutableStateOf("") }

    fun resetGame() {
        gameState = listOf("", "", "", "", "", "", "", "", "")
        player = "X"
        winner = ""
    }

    fun checkWinner() {
        val winnerPositions = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )

        for (position in winnerPositions) {
            val (a, b, c) = position
            if (gameState[a] != "" && gameState[a] == gameState[b] && gameState[b] == gameState[c]) {
                winner = gameState[a]
            }
        }
    }

    fun onTileClick(index: Int) {
        if (gameState[index] == "" && winner == "") {
            gameState = gameState.toMutableList().apply {
                set(index, player)
            }
            checkWinner()
            player = if (player == "X") "O" else "X"
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Tic Tac Toe Game",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 32.dp, top = 32.dp)
        )
        Text(
            text = if (winner.isNotEmpty()) "Winner: $winner" else "",
            style = MaterialTheme.typography.h4,
            color = Color.Green,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyVerticalGrid(GridCells.Fixed(3)) {
            items(gameState.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(64.dp)
                        .background(Color.LightGray)
                        .clickable {
                            onTileClick(index)
                        }
                ) {
                    Text(
                        text = gameState[index],
                        style = MaterialTheme.typography.h3,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
        Text(
            text = "Turn of $player",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Button(
            onClick = { resetGame() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Restart Game")
        }
    }
}
