package rezolya.motivation

import rezolya.motivation.Dsl._
import rezolya.motivation.model._

class DslSpec extends BaseSpec {
  "The DSL" should {
    "create a Statement" when {
      "AM is used" in {
        I AM "passionate about programming" shouldBe
          Am("passionate about programming")
      }

      "AM_A a is used" in {
        I AM_A "developer" shouldBe
          AmA("developer")
      }

      "DO is used" in {
        I DO "like learning" shouldBe
          Do("like learning")
      }

      "HAVE is used" in {
        I HAVE "computer" shouldBe
          Have("computer")
      }
    }
  }

  it should {
    "extend a Statement" when {
      "AND is used" in {
        I AM "passionate about programming" AND
          I HAVE "programming skills" shouldBe
          StatementWithSupporting(Am("passionate about programming"),
                                  List(Have("programming skills")))
      }

      "ANDs are used" in {
        I AM_A "developer" AND
          I HAVE "programming skills" AND
          I DO "like learning" AND
          I AM "passionate about programming" AND
          I AM_A "team member" shouldBe
          StatementWithSupporting(AmA("developer"),
                                  List(Have("programming skills"),
                                       Do("like learning"),
                                       Am("passionate about programming"),
                                       AmA("team member")))
      }
    }
  }
}
