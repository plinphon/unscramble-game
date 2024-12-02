package tat.mukhutdinov.lesson10unscramble.data

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertNotEquals
import org.junit.Test
import tat.mukhutdinov.lesson10unscramble.ui.GameViewModel

class GameViewModelTest {
    private val viewModel = GameViewModel()

    @Test
    fun correctWordGuessed_ScoreUpdatedAndErrorFlagUnset() {
        //given
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        //when
        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        //then
        currentGameUiState = viewModel.uiState.value
        assertFalse(currentGameUiState.isGuessedWordWrong)
        assertEquals(20, currentGameUiState.score)
    }

    @Test
    fun incorrectGuess_ErrorFlagSet() {
        val incorrectPlayerWords = "and"

        viewModel.updateUserGuess(incorrectPlayerWords)
        viewModel.checkUserGuess()

        //then
        val currentGameUiState = viewModel.uiState.value
        assertTrue(currentGameUiState.isGuessedWordWrong)
        assertEquals(0, currentGameUiState.score)
    }

    @Test
    fun initialization_FirstWordLoaded() {
        val gameUiState = viewModel.uiState.value
        val unScrambledWord = getUnscrambledWord(gameUiState.currentScrambledWord)


        assertNotEquals(unScrambledWord, gameUiState.currentScrambledWord)
        assertTrue(gameUiState.currentWordCount == 1)
        assertTrue(gameUiState.score == 0)
        assertFalse(gameUiState.isGuessedWordWrong)
        assertFalse(gameUiState.isGameOver)
    }

    @Test
    fun ScoreUpToDate_WordCount_IsGameOver() {
        var gameUiState = viewModel.uiState.value


        for (i in 1..MAX_NO_OF_WORDS) {
            val unScrambledWord = getUnscrambledWord(gameUiState.currentScrambledWord)
            viewModel.updateUserGuess(unScrambledWord)
            viewModel.checkUserGuess()
            gameUiState = viewModel.uiState.value
            assertEquals(i * 20, gameUiState.score)

        }
        assertTrue(gameUiState.isGameOver)
    }

    @Test
    fun isSkip() {
        val gameUiState = viewModel.uiState.value

        viewModel.skipWord()

        assertEquals(0, gameUiState.score)
        assertEquals(1, gameUiState.currentWordCount)
        assertFalse(gameUiState.isGameOver)
        assertEquals("", gameUiState.userGuess)

    }
}


