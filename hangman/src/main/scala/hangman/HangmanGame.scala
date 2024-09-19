package hangman

import scala.annotation.tailrec

object HangmanGame {
  private enum GameResult:
    case Won, Lost

  private sealed trait GuessResult

  private case class Correct(remainingAttempts: Int, wordState: String)
      extends GuessResult

  private case class Incorrect(remainingAttempts: Int, wordState: String)
      extends GuessResult

  private case class Game(
      word: String,
      wordState: String, // <- Можно было пойти еще глуюже и сделать для стейта отдельный ADT по типу угадана буква или нет
      remainingAttempts: Int,
      maxAttempts: Int,
      hint: String
  ) // <- ADT выше круто написано

  private val hangmanDisplay = HangmanDisplayUtils()
  private val wordChoosingHelper = WordChoosingUtils()

  def startGame(
      wordChoose: (String, String) =
        wordChoosingHelper.chooseWord(wordChoosingHelper.getCategoryInput()),
      difficulty: Int = wordChoosingHelper.getDifficultyInput()
  ) = {
    val (chosenWord, hint) = wordChoose
    val initialWordState = "_" * chosenWord.length // <- я бы разделил отображение стейта в консоли и то, как он у нас хранится внутри игры

    val game =
      Game(chosenWord, initialWordState, 15 - difficulty, 15 - difficulty, hint)
    val gameResult = playGame(game)

    gameResult match {
      case GameResult.Won =>
        println("Congratulations! You guessed it!")
        println(s"The hidden word: $chosenWord")
      case GameResult.Lost =>
        hangmanDisplay.printHangman(0)
        println(s"You lost! The hidden word: $chosenWord")
    }
  }

  @tailrec
  private def playGame(game: Game): GameResult = {
    hangmanDisplay.printHangman(game.remainingAttempts)
    println(s"Current word state: ${game.wordState}")
    println(s"Remaining attempts: ${game.remainingAttempts}")

    val guess = getGuess()

    guess match {
      case "hint" =>
        println(s"Hint: ${game.hint}")

        playGame(game)
      case _ =>
        guessResult(game, guess) match {
          case Correct(remainingAttempts, wordState) =>
            if (wordState == game.word) {
              GameResult.Won
            } else {
              playGame(
                game.copy(
                  remainingAttempts = remainingAttempts,
                  wordState = wordState
                )
              )
            }
          case Incorrect(remainingAttempts, wordState) =>
            if (remainingAttempts == 0) {
              GameResult.Lost
            } else {
              playGame(
                game.copy(
                  remainingAttempts = remainingAttempts,
                  wordState = wordState
                )
              )
            }
        }
    }
  }

  private def guessResult(game: Game, guess: String): GuessResult = {
    if (game.word.contains(guess)) {
      val newWordState = game.word
        .zip(game.wordState)
        .map {
          case (letter, state) if letter == guess.charAt(0) => letter // <- чтобы не делать каждый раз guess.charAt(0), лучше проверить выше, что там действительно один элемент и преобразовать его к Char
          case (_, state)                                   => state
        }
        .mkString

      Correct(game.remainingAttempts, newWordState)
    } else {
      Incorrect(game.remainingAttempts - 1, game.wordState)
    }
  }

  @tailrec
  private def getGuess(): String = {
    println("Enter a letter (or 'hint' for a hint):")

    val input = scala.io.StdIn.readLine().toLowerCase

    if (
      (input.length != 1 && input != "hint") || (input.length == 1 && !input
        .matches("[a-z]"))
    ) {
      println("Incorrect input. Please enter one letter.")
      getGuess()
    } else {
      input
    }
  }
}
