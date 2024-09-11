package hangman

import scala.util.Random

class WordChoosingUtils {
  def getDifficultyInput(): Int =
    println("Enter the game difficulty (number from 5 to 10, press Enter for random difficulty):")

    val input = scala.io.StdIn.readLine()

    if (input.isEmpty) {
      val randomDifficult = Random.nextInt(6) + 5
      println(s"Randomly choose difficult $randomDifficult")

      randomDifficult
    } else {
      try {
        val difficulty = input.toInt

        if (difficulty >= 5 && difficulty <= 10) {
          difficulty
        } else {
          println("Incorrect input. Difficulty should be between 5 and 10.")
          getDifficultyInput()
        }
      } catch {
        case _: NumberFormatException =>
          println("Incorrect input. Please enter a number.")
          getDifficultyInput()
      }
    }

  def getCategoryInput(): Category = {
    println("Select a category (press Enter for random category):")

    Category.values.zipWithIndex.foreach { case (category, i) =>
      println(s"$i - ${category.toString}")
    }

    val inputCategory = scala.io.StdIn.readLine()
    if (inputCategory.isEmpty) {
      val categoriesList = Category.values.toList
      val randomCategory = categoriesList(Random.nextInt(categoriesList.length))
      println(s"Randomly choose category $randomCategory")

      randomCategory
    } else {
      try {
        val categoryIndex = inputCategory.toInt

        if (categoryIndex >= 0 && categoryIndex < Category.values.length) {
          Category.values(categoryIndex)
        } else {
          println("Invalid input. Please enter a number between 0 and " + (Category.values.length - 1) + ".")

          getCategoryInput()
        }
      } catch {
        case _: NumberFormatException =>
          println("Incorrect input. Please enter a number.")

          getCategoryInput()
      }
    }
  }

  def chooseWord(category: Category): (String, String) = {
    category.words(Random.nextInt(category.words.length))
  }
}
