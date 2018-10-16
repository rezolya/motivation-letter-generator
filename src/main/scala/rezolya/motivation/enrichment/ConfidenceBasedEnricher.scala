package rezolya.motivation.enrichment

import rezolya.motivation.model.ConfidenceLevel

class ConfidenceBasedEnricher(confidenceLevel: ConfidenceLevel.EnumVal)(
    implicit val dictionary: Dictionary
) extends Enricher {
  import rezolya.motivation.util._

  private val timeAdverbs =
    dictionary.byConfidenceLevel(confidenceLevel).timeAdverbs
  private val adverbs = dictionary.byConfidenceLevel(confidenceLevel).adverbs
  private val adjectives =
    dictionary.byConfidenceLevel(confidenceLevel).adjectives

  override def getTimeAdverb: String = timeAdverbs.pickRandom

  override def getAdverb: String = adverbs.pickRandom

  override def getAdjective: String = adjectives.pickRandom

  override def getIntroduction: String = dictionary.introductions.pickRandom
}
