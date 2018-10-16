package rezolya.motivation.enrichment
import rezolya.motivation.model.ConfidenceLevel

trait ProvideConfidenceBasedEnricher {
  def confidenceLevel: ConfidenceLevel.EnumVal
  implicit def dictionary: Dictionary

  implicit lazy val enricher: Enricher =
    new ConfidenceBasedEnricher(confidenceLevel)
}
