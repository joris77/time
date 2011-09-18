import java.io.FileOutputStream
import models._
import org.joda.time.{LocalDateTime, LocalDate}
import org.mockito.Matchers._
import org.mockito.Mockito._
import org.scalatest.FunSuite
import play.db.anorm.NotAssigned

/**
 * User: joris
 * Date: 9/8/11
 * Time: 1:28 PM
 *
 *
 */

class TimeSheetTest extends FunSuite with MockTest {


  val repo = mock(classOf[TimeSlotRepository])
  when(mockFactory.timeSlot).thenReturn(repo)




  test("create pdf") {

    val timeSlot = new TimeSlot(NotAssigned, Some(new LocalDateTime(2011, 8, 1, 7, 0)), Some(new LocalDateTime(2011, 8, 1, 17, 0)), Some("geen pauze"), 1)

    val slots = List[TimeSlot](timeSlot)

    when(repo.findTimeSlots(any(classOf[LocalDate]), any(classOf[LocalDate]))).thenReturn(slots)

    val famed : Organisation = new Organisation(NotAssigned, "Fa-med", 1L)
    famed.address = new Address(NotAssigned,"Plotterweg", "26-28", "Amersfoort","3821 BB")
    val output = new TimeSheet(famed,new LocalDate(2011, 8, 1), new LocalDate(2011, 8, 31))

    val pdf = new FileOutputStream("/tmp/timesheet.pdf")
    pdf.write(output.asOutputStream.toByteArray)
    pdf.close()
  }

}