package hangman

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class HangmanDisplayUtilsSpec extends AnyFunSuite with Matchers {

  test("printHangman method should print correct hangman for attempts 0-10") {
    captureOutput {
      HangmanDisplayUtils().printHangman(0)
    } shouldEqual
      """
        |   ______
        |  |      |
        |  |      O
        |  |     -|-
        |  |     / \
        |__|__
        |
        |""".stripMargin

    captureOutput {
      HangmanDisplayUtils().printHangman(1)
    } shouldEqual
      """
        |   ______
        |  |      |
        |  |      O
        |  |     -|-
        |  |       \
        |__|__
        |
        |""".stripMargin

    captureOutput {
      HangmanDisplayUtils().printHangman(2)
    } shouldEqual
      """
        |   ______
        |  |      |
        |  |      O
        |  |     -|-
        |  |
        |__|__
        |
        |""".stripMargin

    captureOutput {
      HangmanDisplayUtils().printHangman(3)
    } shouldEqual
      """
        |   ______
        |  |      |
        |  |      O
        |  |      |-
        |  |
        |__|__
        |
        |""".stripMargin

    captureOutput {
      HangmanDisplayUtils().printHangman(4)
    } shouldEqual
      """
        |   ______
        |  |      |
        |  |      O
        |  |      |
        |  |
        |__|__
        |
        |""".stripMargin

    captureOutput {
      HangmanDisplayUtils().printHangman(5)
    } shouldEqual
      """
        |   ______
        |  |      |
        |  |      O
        |  |
        |  |
        |__|__
        |
        |""".stripMargin

    captureOutput {
      HangmanDisplayUtils().printHangman(6)
    } shouldEqual
      """
        |   ______
        |  |      |
        |  |
        |  |
        |  |
        |__|__
        |
        |""".stripMargin

    captureOutput {
      HangmanDisplayUtils().printHangman(7)
    } shouldEqual
      """
        |   ______
        |  |
        |  |
        |  |
        |  |
        |__|__
        |
        |""".stripMargin

    captureOutput {
      HangmanDisplayUtils().printHangman(8)
    } shouldEqual
      """
        |
        |  |
        |  |
        |  |
        |  |
        |__|__
        |
        |""".stripMargin

    captureOutput {
      HangmanDisplayUtils().printHangman(9)
    } shouldEqual
      """
        |
        |
        |
        |  |
        |  |
        |__|__
        |
        |""".stripMargin

    captureOutput {
      HangmanDisplayUtils().printHangman(10)
    } shouldEqual
      """
        |
        |
        |
        |
        |
        |____
        |
        |""".stripMargin
  }

  test("printHangman method should print same hangman for attempts more than 10") {
    Array.range(10, 40).foreach(elem =>
      captureOutput {
        HangmanDisplayUtils().printHangman(elem)
      } shouldEqual captureOutput {
        HangmanDisplayUtils().printHangman(elem + 1)
      }
    )
  }

  test("printHangman method should throw IllegalArgumentException for invalid attempts count") {
    Array.range(-40, 0).foreach(elem =>
      intercept[IllegalArgumentException] {
        HangmanDisplayUtils().printHangman(elem)
      }
    )
  }
}