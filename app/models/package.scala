import java.sql.{Timestamp, PreparedStatement}
import org.joda.time.LocalDateTime
import play.db.anorm._
import reflect.Manifest

package object models {

  implicit val rowToLocalDateTime: Column[LocalDateTime] =
    Column[LocalDateTime](transformer = {
      (value, meta) =>
        value match {
          case time: Timestamp => Right(new LocalDateTime(time.getTime))
          case _ => Left(TypeDoesNotMatch("Cannot convert " +
            value + " to LocalDateTime for column " + meta))
        }
    })

  implicit val ldtToStatement = new ToStatement[LocalDateTime] {
    def set(s: java.sql.PreparedStatement, index: Int,
            aValue: LocalDateTime): Unit =
      s.setTimestamp(index, new Timestamp(aValue.toDateTime.getMillis))
  }

  object TimeExtendSupport extends ExtendSupport {
    override def extendExtractor[C](f: (Manifest[C] => Option[ColumnTo[C]])) = {
      case m if m == Manifest.classType(classOf[LocalDateTime]) =>
        Some(rowToLocalDateTime).asInstanceOf[Option[ColumnTo[C]]]
    }

    def extendSetAny(original: ToStatement[Any]): ToStatement[Any] = {
      new ToStatement[Any] {
        def set(s: PreparedStatement, index: Int, value: Any) {
          value match {
            case ldt: LocalDateTime => ldtToStatement.set(s, index, ldt)
            case o => original.set(s, index, value)
          }
        }
      }
    }
  }

  val defaults = new Convention(asIs, TimeExtendSupport) with WithDefaults {
    override lazy val defaultConvention = asIs
  }

}
