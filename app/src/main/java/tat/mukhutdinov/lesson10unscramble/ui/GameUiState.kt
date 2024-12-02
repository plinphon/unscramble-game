package tat.mukhutdinov.lesson10unscramble.ui

data class GameUiState(
    val userGuess: String = "",
    val currentScrambledWord: String = "",
    val isGuessedWordWrong: Boolean = false,
    val score: Int = 0,
    val currentWordCount: Int = 1,
    val isGameOver: Boolean = false
)
