package rezolya.motivation.model

object ConfidenceLevel {
  sealed trait EnumVal

  case object Modest extends EnumVal {
    override def toString: String = "modest"
  }

  case object Normal extends EnumVal {
    override def toString: String = "normal"
  }

  case object Arrogant extends EnumVal {
    override def toString: String = "arrogant"
  }

  val values = List(Modest, Normal, Arrogant)
}
