package rezolya.motivation.model
import rezolya.motivation.enrichment.Enricher

import scala.compat.Platform.EOL

case class LetterOutline(intro: String, paragraphs: List[Statement]) {
  import rezolya.motivation.util._

  private def getParagraphsSeparator(implicit enricher: Enricher): String =
    s"$EOL${enricher.getIntroduction} "

  def enrich(implicit enricher: Enricher): String = {
    val start = if (intro.endsWith(" ")) intro else intro + " "
    paragraphs
      .map(_.prepare).mkStringNonEmpty(start, getParagraphsSeparator, "")
  }
}
