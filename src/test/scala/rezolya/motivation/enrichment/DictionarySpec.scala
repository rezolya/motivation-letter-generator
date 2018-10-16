package rezolya.motivation.enrichment
import rezolya.motivation.BaseSpec
import rezolya.motivation.model.ConfidenceLevel

class DictionarySpec extends BaseSpec {

  "ResourceDictionary" should {
    val sut = new ResourcesDictionary

    "provide at least 3 introductions" in {
      sut.introductions.size should be >= 3
    }

    "provide dictionary for all confidence levels" in {
      val contains = ConfidenceLevel.values.map { cl =>
        cl -> sut.byConfidenceLevel.contains(cl)
      }
      contains.map(_._2) should contain only true
    }
  }
}
