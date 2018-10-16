package rezolya.motivation.model

import rezolya.motivation.BaseSpec
import rezolya.motivation.enrichment.Enricher

class LetterOutlineSpec extends BaseSpec {
  import rezolya.motivation.Dsl._

  implicit val testEnricher: TestEnricher = new TestEnricher

  "LetterOutline" should {
    "prepare letter correctly" when {
      "it has one paragraph" in {
        val outline =
          LetterOutline("Starbucks should hire me because ",
                        List(I AM_A "barista"))

        val expectedResult =
          """Starbucks should hire me because I am a quite good barista."""
        outline.enrich shouldBe expectedResult
      }

      "it has two paragraphs" in {
        val outline =
          LetterOutline("Starbucks should hire me because",
                        List(I AM_A "barista", I DO "like coffee myself"))

        val expectedResult =
          """Starbucks should hire me because I am a quite good barista.
            |Also, I sometimes like coffee myself.""".stripMargin
        outline.enrich shouldBe expectedResult
      }

      "it has many paragraphs" in {
        val outline =
          LetterOutline("Starbucks should hire me because",
                        List(I AM_A "barista" AND I HAVE "coffee-making skills",
                             I DO "like coffee myself",
                             I AM "communicative",
                             I DO "spell names wrongly"))

        val expectedResult =
          """Starbucks should hire me because I am a quite good barista. My coffee-making skills are quite good.
            |Also, I sometimes like coffee myself.
            |Also, I am quite communicative.
            |Also, I sometimes spell names wrongly.""".stripMargin
        outline.enrich shouldBe expectedResult
      }
    }

    "use different paragraph introductions every time" in {
      val incrementEnricher: Enricher = new Enricher {
        override def getTimeAdverb: String = "sometimes"
        override def getAdverb: String = "quite"
        override def getAdjective: String = "quite good"

        var nr = 0
        override def getIntroduction: String = {
          nr = nr + 1
          s"Also$nr,"
        }
      }

      val outline =
        LetterOutline("Starbucks should hire me because",
                      List(I AM_A "barista",
                           I DO "like coffee myself",
                           I AM "communicative",
                           I DO "spell names wrongly"))

      val expectedResult =
        """Starbucks should hire me because I am a quite good barista.
          |Also1, I sometimes like coffee myself.
          |Also2, I am quite communicative.
          |Also3, I sometimes spell names wrongly.""".stripMargin
      outline.enrich(incrementEnricher) shouldBe expectedResult
    }
  }
}
