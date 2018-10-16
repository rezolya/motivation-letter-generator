package rezolya.motivation

import scala.util.Random

package object util {
  implicit class RichSeq[T](seq: Seq[T]) {
    def pickRandom: T = {
      require(seq.nonEmpty, "Cannot pick a random value from an empty list")
      seq(Random.nextInt(seq.size))
    }

    def mkStringNonEmpty(start: => String,
                         sep: => String,
                         end: => String): String =
      if (seq.nonEmpty) {
        val stringList = seq.map(_.toString)
        start + stringList.tail.fold(stringList.head)(_ + sep + _) + end
      } else ""
  }
}
