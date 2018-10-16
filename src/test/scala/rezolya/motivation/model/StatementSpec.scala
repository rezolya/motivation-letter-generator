package rezolya.motivation.model

import rezolya.motivation.BaseSpec
import rezolya.motivation.enrichment.Enricher

class StatementSpec extends BaseSpec {

  implicit val testEnricher: Enricher = new TestEnricher

  "Do Statement" should {
    "prepare string correctly" in {
      val sut = Do("like learning")
      sut.prepare shouldBe "I sometimes like learning."
    }
  }

  "Am Statement" should {
    "prepare string correctly" in {
      val sut = Am("passionate about programming")
      sut.prepare shouldBe "I am quite passionate about programming."
    }
  }

  "AmA Statement" should {
    "prepare string correctly" in {
      val sut = AmA("developer")
      sut.prepare shouldBe "I am a quite good developer."
    }
  }

  "Have Statement" should {
    "prepare string correctly" when {
      "single value is used" in {
        val sut = Have("computer")
        sut.prepare shouldBe "My computer is quite good."
      }

      "multiple value is used" in {
        val sut = Have("communication skills")
        sut.prepare shouldBe "My communication skills are quite good."
      }
    }
  }

  "StatementWithSupporting" should {
    "prepare string correctly" when {
      "it has one supporting statement" in {
        val sut =
          StatementWithSupporting(AmA("developer"),
                                  List(Am("passionate about programming")))
        sut.prepare shouldBe "I am a quite good developer. I am quite passionate about programming."
      }

      "it has many supporting statements" in {
        val sut =
          StatementWithSupporting(AmA("developer"),
                                  List(Am("passionate about programming"),
                                       Have("programming skills"),
                                       Do("like learning")))
        sut.prepare shouldBe "I am a quite good developer. I am quite passionate about programming. My programming skills are quite good. I sometimes like learning."
      }

      "it has no supporting statement" in {
        val sut = StatementWithSupporting(AmA("developer"), List.empty)
        sut.prepare shouldBe "I am a quite good developer."
      }
    }
  }
}
