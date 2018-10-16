package rezolya.motivation

import rezolya.motivation.enrichment._
import rezolya.motivation.model.ConfidenceLevel._
import rezolya.motivation.model.LetterOutline

object BaristaMotivationLetter extends App with ProvideConfidenceBasedEnricher {
  import Dsl._

  override val dictionary: Dictionary = new ResourcesDictionary
  override val confidenceLevel = Arrogant

  val reasons = List(
    I AM "passionate about rafting the perfect cup of coffee" AND
      I HAVE "attention for details" AND
      I AM "committed to making good coffee",
    I AM_A "multitasker" AND
      I HAVE "customer service skills",
    I DO "perform in high-pressure situations" AND
      I HAVE "experience with making coffee during the morning rush" AND
      I AM "calm even when line is long",
    I AM "able to divide my attention between tasks" AND
      I DO "provide consistent service",
    I AM "proud on my ability to remember the names and orders" AND
      I DO "write names on cups incorrectly",
    I DO "provide service with a smile" AND
      I AM_A "good addition to your team"
  )

  val letter = LetterOutline(
    "I am writing to apply for a barista job at Starbucks. I am a suitable candidate because",
    reasons
  )

  println(letter.enrich)
}
