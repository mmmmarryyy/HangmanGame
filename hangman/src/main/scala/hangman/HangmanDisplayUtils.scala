package hangman

class HangmanDisplayUtils {
  def printHangman(attempts: Int): Unit =
    print(attempts match {
      case x if x > 9 =>
        """
          |
          |
          |
          |
          |
          |____
          |
          |""".stripMargin
      case 9 =>
        """
          |
          |
          |
          |  |
          |  |
          |__|__
          |
          |""".stripMargin
      case 8 =>
        """
          |
          |  |
          |  |
          |  |
          |  |
          |__|__
          |
          |""".stripMargin
      case 7 =>
        """
          |   ______
          |  |
          |  |
          |  |
          |  |
          |__|__
          |
          |""".stripMargin
      case 6 =>
        """
          |   ______
          |  |      |
          |  |
          |  |
          |  |
          |__|__
          |
          |""".stripMargin
      case 5 =>
        """
          |   ______
          |  |      |
          |  |      O
          |  |
          |  |
          |__|__
          |
          |""".stripMargin
      case 4 =>
        """
          |   ______
          |  |      |
          |  |      O
          |  |      |
          |  |
          |__|__
          |
          |""".stripMargin
      case 3 =>
        """
          |   ______
          |  |      |
          |  |      O
          |  |      |-
          |  |
          |__|__
          |
          |""".stripMargin
      case 2 =>
        """
          |   ______
          |  |      |
          |  |      O
          |  |     -|-
          |  |
          |__|__
          |
          |""".stripMargin
      case 1 =>
        """
          |   ______
          |  |      |
          |  |      O
          |  |     -|-
          |  |       \
          |__|__
          |
          |""".stripMargin
      case 0 =>
        """
          |   ______
          |  |      |
          |  |      O
          |  |     -|-
          |  |     / \
          |__|__
          |
          |""".stripMargin
      case _ =>
        throw new IllegalArgumentException(
          "Invalid attempts count: " + attempts
        )
    })
}
