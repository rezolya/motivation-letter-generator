package rezolya.motivation.enrichment

abstract class Enricher {
  def getTimeAdverb: String
  def getAdverb: String
  def getAdjective: String
  def getIntroduction: String
}
