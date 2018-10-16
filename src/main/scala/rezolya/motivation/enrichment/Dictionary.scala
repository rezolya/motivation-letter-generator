package rezolya.motivation.enrichment

import rezolya.motivation.model.ConfidenceLevel
import rezolya.motivation.model.ConfidenceLevel._

import scala.io.Source

trait Dictionary {
  def byConfidenceLevel: Map[ConfidenceLevel.EnumVal, ConfidenceLevelDictionary]
  def introductions: Seq[String]
}

case class ConfidenceLevelDictionary(adjectives: Seq[String],
                                     adverbs: Seq[String],
                                     timeAdverbs: Seq[String])

class ResourcesDictionary extends Dictionary {
  override val byConfidenceLevel
    : Map[ConfidenceLevel.EnumVal, ConfidenceLevelDictionary] =
    ConfidenceLevel.values
      .map(cl => cl -> getConfidenceLevelDictionary(cl)).toMap

  override val introductions: Seq[String] = getLines(
    "dictionary/introductions.txt"
  )

  private def getConfidenceLevelDictionary(
      confidenceLevel: ConfidenceLevel.EnumVal
  ) =
    ConfidenceLevelDictionary(
      adjectives = getFileTypeLines(confidenceLevel, "adjectives"),
      adverbs = getFileTypeLines(confidenceLevel, "adverbs"),
      timeAdverbs = getFileTypeLines(confidenceLevel, "timeAdverbs")
    )

  private def getFileTypeLines(confidenceLevel: ConfidenceLevel.EnumVal,
                               fileType: String): Seq[String] =
    getLines(getFileTypePath(confidenceLevel, fileType))

  private def getFileTypePath(confidenceLevel: ConfidenceLevel.EnumVal,
                              fileType: String): String =
    s"dictionary/$confidenceLevel/$fileType.txt"

  private def getLines(path: String): Seq[String] =
    Source.fromResource(path).getLines().toList
}
