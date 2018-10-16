package rezolya.motivation

import rezolya.motivation.model._

object Dsl {

  object I {
    def AM(str: String): Statement = Am(str)
    def AM_A(str: String): Statement = AmA(str)
    def HAVE(str: String) = Have(str)
    def DO(str: String) = Do(str)
  }

  class I(maybeStatement: Option[Statement]) {
    def AM(str: String): Statement = addSupporting(I.AM(str))
    def AM_A(str: String): Statement = addSupporting(I.AM_A(str))
    def HAVE(str: String): Statement = addSupporting(I.HAVE(str))
    def DO(str: String): Statement = addSupporting(I.DO(str))

    private def addSupporting(supporting: Statement): Statement =
      maybeStatement.fold[Statement](supporting)(_.addSupporting(supporting))
  }

  //Could add the methods to Statement directly, but I'd like to keep all the DSL logic together
  implicit class RichReason(statement: Statement) {
    def AND(i: I.type) = new I(Some(statement))

    def addSupporting(supporting: Statement): Statement = statement match {
      case StatementWithSupporting(mainStatement, supportingStatements) =>
        StatementWithSupporting(mainStatement,
                                supportingStatements :+ supporting)
      case simpleStatement: Statement =>
        StatementWithSupporting(simpleStatement, List(supporting))
    }
  }
}
