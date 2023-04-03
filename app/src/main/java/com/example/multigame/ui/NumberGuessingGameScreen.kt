package com.example.multigame.ui

import com.example.multigame.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import java.util.*

@Composable
fun NumberGuessingGameScreen() {
    var randomNumber by remember { mutableStateOf(Random().nextInt(1000 - 0) + 1) }
    var guessInput by remember { mutableStateOf("") }
    var countGuess by remember { mutableStateOf(0) }
    var countGuessText by remember { mutableStateOf("") }
    val guessNumber = guessInput.toIntOrNull()
    var result by remember { mutableStateOf("") }
    var cheating by remember { mutableStateOf("") }

    fun calculateGuessNumber(guessNumber: Int, randomNumber: Int): String {
        if(guessNumber < randomNumber) {
            return "Hint: It 's Lower!"
        } else if (guessNumber > randomNumber) {
            return "Hint: It 's higher!"
        }
        countGuessText = "You guessed a total of $countGuess times."
        return "Correct!"
    }
    fun reset() {
        randomNumber = Random().nextInt(1000 - 0) + 1
        guessInput = ""
        countGuessText = ""
        countGuess = 0
        result = ""
        cheating = ""
    }

    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.numberguessinggame),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.how_to_play),
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        EditNumberField(
            value = guessInput,
            onValueChange = { guessInput = it }
        )
        Text(
            text = result,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = countGuessText,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                if (guessNumber != null && result != "Correct!") {
                    countGuess += 1
                    result = calculateGuessNumber(guessNumber, randomNumber)
                    if(guessNumber == -9999) {
                        cheating = "Result is $randomNumber"
                        countGuess -= 1
                    }
                }
            }) {
            Text("SUBMIT")
        }
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { reset() }) {
            Text("PLAY AGAIN")
        }
        Text(
            text = cheating,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.your_guess)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}

