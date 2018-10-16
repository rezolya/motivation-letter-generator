package rezolya.motivation.model
import rezolya.motivation.enrichment.Enricher

class TestEnricher extends Enricher {
  override def getTimeAdverb: String = "sometimes"

  override def getAdverb: String = "quite"

  override def getAdjective: String = "quite good"

  override def getIntroduction: String = "Also,"
}
