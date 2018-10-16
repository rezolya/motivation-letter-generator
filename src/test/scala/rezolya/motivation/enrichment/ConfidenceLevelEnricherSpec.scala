package rezolya.motivation.enrichment

import rezolya.motivation.BaseSpec
import rezolya.motivation.model.ConfidenceLevel._

class ConfidenceLevelEnricherSpec extends BaseSpec {
  val adjectives: Seq[String] = (1 to 5).map(i => s"adjective$i")
  val adverbs: Seq[String] = (1 to 5).map(i => s"adverb$i")
  val timeAdverbs: Seq[String] = (1 to 5).map(i => s"time adverb$i")
  val intros: Seq[String] = (1 to 5).map(i => s"intro$i")

  implicit val testDictionary: Dictionary = new Dictionary {
    override def byConfidenceLevel: Map[EnumVal, ConfidenceLevelDictionary] =
      Map(Modest -> ConfidenceLevelDictionary(adjectives, adverbs, timeAdverbs))
    override def introductions: Seq[String] = intros
  }

  "ConfidenceLevelEnricher" should {
    val sut = new ConfidenceBasedEnricher(Modest)

    "get time adverb correctly" in {
      timeAdverbs should contain(sut.getTimeAdverb)
    }

    "get adverb correctly" in {
      adverbs should contain(sut.getAdverb)
    }

    "get adjective correctly" in {
      adjectives should contain(sut.getAdjective)
    }

    "get introduction correctly" in {
      intros should contain(sut.getIntroduction)
    }
  }
}
