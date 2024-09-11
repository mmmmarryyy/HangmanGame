package hangman

import org.scalatest.PrivateMethodTester
import org.scalatest.funsuite.AnyFunSuite

class HangmanGameSpec extends AnyFunSuite with PrivateMethodTester {
  private val testCategory = Category.Animals
  private val (testWord, testHint) = testCategory.words.head
  private val testDifficulty = 10
  private val numberOfAttepmts = 15 - testDifficulty

  test("winning scenario") {
    Console.withIn(
      new java.io.ByteArrayInputStream(
        s"${Category.values.indexOf(testCategory)}\n$testDifficulty\n".getBytes
      )
    ) {
      val (testWord, testHint) =
        WordChoosingUtils().chooseWord(WordChoosingUtils().getCategoryInput())
      val testDifficulty = WordChoosingUtils().getDifficultyInput()

      val rand = new scala.util.Random
      val testInputString = "hint\n" + testWord.toSeq.distinct.map { c =>
        if (rand.nextDouble() < 0.5)
          s"${c.toUpper}\n" // make some enter letters upper case
        else
          s"$c\n"
      }.mkString

      val testOutput = captureOutput {
        Console.withIn(
          new java.io.ByteArrayInputStream(testInputString.getBytes)
        ) {
          HangmanGame.startGame((testWord, testHint), testDifficulty)
        }
      }

      val printedHangman = captureOutput {
        HangmanDisplayUtils().printHangman(numberOfAttepmts)
      }

      assert(testOutput.contains(s"Hint: $testHint"))
      assert(testOutput.contains(printedHangman))
      assert(
        testOutput.contains(
          s"Congratulations! You guessed it!"
        )
      )
      assert(
        testOutput.contains(
          s"The hidden word: $testWord"
        )
      )
      assert(
        testOutput
          .split("\n")
          .count(str =>
            str.contains(
              s"Remaining attempts: $numberOfAttepmts"
            )
          ) == testWord.toSeq.distinct.size + 1
      )

      val statesList = testOutput
        .split("\n")
        .filter(str => str.contains("Current word state: "))
        .map(str => str.substring(20))
        .toList

      statesList.drop(1).zipWithIndex.foreach { elem =>
        if (elem._2 == 0) {
          assert(
            elem._1.count(c => c == '_') == testWord.length
          ) // assert that initial state is something like ______
        } else {
          // assert for each state that it is correct
          testWord.toList.zipWithIndex.foreach { c =>
            if (
              testInputString.toLowerCase
                .split("\n")
                .toList
                .indexOf(c._1.toString) <= elem._2
            )
              assert(elem._1(c._2) == c._1)
            else
              assert(elem._1(c._2) == '_')
          }
        }
      }
    }
  }

  test("losing scenario") {
    Console.withIn(
      new java.io.ByteArrayInputStream(
        s"${Category.values.indexOf(testCategory)}\n$testDifficulty\n".getBytes
      )
    ) {
      val (testWord, testHint) =
        WordChoosingUtils().chooseWord(WordChoosingUtils().getCategoryInput())
      val testDifficulty = WordChoosingUtils().getDifficultyInput()

      val wrongLetter = "abcdefghijklmnopqrstuvwxyz".toCharArray.filterNot(c =>
        testWord.contains(c)
      )(0)

      val testInputString = s"$wrongLetter\n" * numberOfAttepmts

      val testOutput = captureOutput {
        Console.withIn(
          new java.io.ByteArrayInputStream(testInputString.getBytes)
        ) {
          HangmanGame.startGame((testWord, testHint), testDifficulty)
        }
      }

      assert(testOutput.contains(s"You lost! The hidden word: $testWord"))

      Array
        .range(0, numberOfAttepmts)
        .foreach(elem =>
          val printedHangman = captureOutput {
            HangmanDisplayUtils().printHangman(elem)
          }
          assert(testOutput.contains(printedHangman))
          if (elem != 0)
            assert(testOutput.contains(s"Remaining attempts: $elem"))
        )

      assert(
        testOutput
          .split("\n")
          .count(str =>
            str.contains(
              "Current word state: " + "_" * testWord.length
            )
          ) == numberOfAttepmts
      )
    }
  }
}
