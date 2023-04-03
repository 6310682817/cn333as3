package com.example.multigame.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material.*
import androidx.compose.ui.res.stringResource
import com.example.multigame.R

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onNextButtonClicked: (Int) -> Unit,
) {
    Column(
        modifier = modifier.padding(16.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(R.string.app_name), style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onNextButtonClicked(1) },
            modifier = modifier.widthIn(min = 250.dp)
        ) {
            Text(stringResource(R.string.numberguessinggame))
        }
        Button(
            onClick = { onNextButtonClicked(2) },
            modifier = modifier.widthIn(min = 250.dp)
        ) {
            Text(stringResource(R.string.mathquizgame))
        }
        Button(
            onClick = { onNextButtonClicked(3) },
            modifier = modifier.widthIn(min = 250.dp)
        ) {
            Text(stringResource(R.string.tictactoe))
        }
    }
}