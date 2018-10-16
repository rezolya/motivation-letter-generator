package rezolya.motivation.util

import rezolya.motivation.BaseSpec

class RichSeqSpec extends BaseSpec {
  "RichSeq" should {
    "pick a random value from the list" in {
      val list = (1 to 100).toList
      val result = list.pickRandom
      list.contains(result) shouldBe true
    }

    "not throw and exception" when {
      "list is empty" in {
        val list = List.empty[String]
        an[IllegalArgumentException] should be thrownBy {
          list.pickRandom
        }
      }
    }

    "not throw and exception" when {
      "list has 1 value" in {
        val list = List("hello")
        noException should be thrownBy {
          (1 to 10).foreach(_ => list.pickRandom)
        }
      }

      "list has 3 values" in {
        val list = (1 to 3).toList
        noException should be thrownBy {
          (1 to 100).foreach(_ => list.pickRandom)
        }
      }
    }
  }
}
