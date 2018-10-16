package rezolya.motivation

import rezolya.motivation.enrichment._
import rezolya.motivation.model.ConfidenceLevel._
import rezolya.motivation.model.LetterOutline

object DeveloperMotivationLetter
    extends App with ProvideConfidenceBasedEnricher {
  import Dsl._

  override val dictionary: Dictionary = new ResourcesDictionary
  override val confidenceLevel = Normal

  val reasons = List(
    I AM "passionate about programming" AND
      I DO "enjoy learning new technologies" AND
      I AM "enthusiastic to apply those in practice" AND
      I DO "strive for ways to make the code more readable, maintainable and elegant",
    I AM_A "developer" AND
      I HAVE "problem solving skills" AND
      I DO "understand unfamiliar code fast" AND
      I AM "good at fixing bugs",
    I DO "appreciate the need for continuous integration" AND
      I DO "know how bad it is when you don't have it" AND
      I AM "ready to invest time and effort in it",
    I AM_A "team member" AND
      I AM "ready to help colleagues" AND
      I HAVE "communication skills" AND
      I DO "finish the tasks I take on" AND
      I DO "feel responsible for team progress" AND
      I AM_A "fit for your team"
  )

  val letter = LetterOutline("I feel suitable for this job because", reasons)

  println(letter.enrich)
}
