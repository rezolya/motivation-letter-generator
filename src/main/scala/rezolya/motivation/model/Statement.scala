package rezolya.motivation.model
import rezolya.motivation.enrichment.Enricher
import rezolya.motivation.util._

abstract class Statement {
  def prepare(implicit enricher: Enricher): String
}

case class Do(value: String) extends Statement {
  override def prepare(implicit enricher: Enricher) =
    s"I ${enricher.getTimeAdverb} $value."
}

case class Am(value: String) extends Statement {
  override def prepare(implicit enricher: Enricher) =
    s"I am ${enricher.getAdverb} $value."
}

case class AmA(value: String) extends Statement {
  override def prepare(implicit enricher: Enricher) =
    s"I am a ${enricher.getAdjective} $value."
}

case class Have(value: String) extends Statement {
  override def prepare(implicit enricher: Enricher) =
    s"My $value ${if (value.endsWith("s")) "are" else "is"} ${enricher.getAdjective}."
}

case class StatementWithSupporting(main: Statement, supporting: List[Statement])
    extends Statement {
  import rezolya.motivation.util._

  override def prepare(implicit enricher: Enricher): String = {
    val preparedSupporting =
      supporting.map(_.prepare).mkStringNonEmpty(" ", " ", "")
    s"${main.prepare}$preparedSupporting"
  }
}
