package hangman

import java.io.{ByteArrayOutputStream, PrintStream}

def captureOutput(f: => Any): String = {
  val stream = new ByteArrayOutputStream()
  val oldOut = System.out
  Console.withOut(PrintStream(stream)) {
    f
  }
  System.setOut(oldOut)
  stream.toString
}
