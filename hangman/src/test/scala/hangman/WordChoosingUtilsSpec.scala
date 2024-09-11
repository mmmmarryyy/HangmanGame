package hangman

import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite

class WordChoosingUtilsSpec extends AnyFunSuite with BeforeAndAfterAll {

  test(
    "getDifficultyInput should return valid difficulty if input is between 5 and 10"
  ) {
    Array
      .range(5, 11)
      .foreach(elem =>
        Console.withIn(new java.io.ByteArrayInputStream(s"$elem\n".getBytes)) {
          val result = WordChoosingUtils().getDifficultyInput()
          assert(result == elem)
        }
      )
  }

  test(
    "getDifficultyInput should return random valid difficulty if input is empty"
  ) {
    Console.withIn(new java.io.ByteArrayInputStream("\n".getBytes)) {
      val result = WordChoosingUtils().getDifficultyInput()
      assert(result >= 5 && result <= 10)
    }
  }

  test(
    "getDifficultyInput should prompt for valid input if input is outside of range"
  ) {
    Array
      .concat(Array.range(-20, 4), Array.range(11, 25))
      .foreach(elem =>
        val result = captureOutput {
          Console
            .withIn(new java.io.ByteArrayInputStream(s"$elem\n\n".getBytes)) {
              WordChoosingUtils().getDifficultyInput()
            }
        }
        assert(
          result
            .contains("Incorrect input. Difficulty should be between 5 and 10.")
        )
      )
  }

  test(
    "getDifficultyInput should prompt for valid input if input is not a number"
  ) {
    "abcdefghijklmnopqrstuvwxyz".toCharArray.foreach(elem =>
      val result = captureOutput {
        Console.withIn(
          new java.io.ByteArrayInputStream(s"$elem\n\n".getBytes)
        ) {
          WordChoosingUtils().getDifficultyInput()
        }
      }
      assert(result.contains("Incorrect input. Please enter a number."))
    )
  }

  test(
    "getCategoryInput should return valid category if input is between 0 and the number of categories"
  ) {
    Array
      .range(0, Category.values.size)
      .foreach(elem =>
        Console.withIn(new java.io.ByteArrayInputStream(s"$elem\n".getBytes)) {
          val result = WordChoosingUtils().getCategoryInput()
          assert(result == Category.values(elem))
        }
      )
  }

  test("getCategoryInput should return random category if input is empty") {
    Console.withIn(new java.io.ByteArrayInputStream("\n".getBytes)) {
      val result = WordChoosingUtils().getCategoryInput()
      assert(Category.values.contains(result))
    }
  }

  test(
    "getCategoryInput should prompt for valid input if input is outside of range"
  ) {
    Array
      .concat(Array.range(-20, 0), Array.range(8, 25))
      .foreach(elem =>
        val result = captureOutput {
          Console
            .withIn(new java.io.ByteArrayInputStream(s"$elem\n\n".getBytes)) {
              WordChoosingUtils().getCategoryInput()
            }
        }
        assert(
          result.contains(
            "Invalid input. Please enter a number between 0 and " + (Category.values.length - 1) + "."
          )
        )
      )
  }

  test(
    "getCategoryInput should prompt for valid input if input is not a number"
  ) {
    "abcdefghijklmnopqrstuvwxyz".toCharArray.foreach(elem =>
      val result = captureOutput {
        Console.withIn(
          new java.io.ByteArrayInputStream(s"$elem\n\n".getBytes)
        ) {
          WordChoosingUtils().getCategoryInput()
        }
      }
      assert(result.contains("Incorrect input. Please enter a number."))
    )
  }

  test("chooseWord should return word from given category") {
    Category.values.foreach(category =>
      assert(category.words.contains(WordChoosingUtils().chooseWord(category)))
    )
  }
}
