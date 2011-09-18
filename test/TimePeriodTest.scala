import models._
import org.joda.time.{LocalDateTime, LocalDate}
import org.mockito.cglib.core.Local
import org.scalatest.FunSuite

import org.mockito.Mockito._
import org.mockito.Matchers._
import play.db.anorm.NotAssigned

/**
 * User: joris
 * Date: 9/6/11
 * Time: 10:58 PM
 *
 *
 */

class TimePeriodTest extends FunSuite with MockTest{

  val repo = mock(classOf[TimeSlotRepository])
  when(mockFactory.timeSlot).thenReturn(repo)

  test("period with timeslots") {

    val timeSlot = mock(classOf[TimeSlot])

    when(timeSlot.beginTime).thenReturn(Some(new LocalDateTime(2011, 8, 3, 7, 0)))

    val slots = List[TimeSlot](timeSlot)



    when(repo.findTimeSlots(any(classOf[LocalDate]), any(classOf[LocalDate]))).thenReturn(slots)

    val p = new TimePeriod(new Organisation(NotAssigned,"Fa-med",1L),new LocalDate(2011, 8, 1), new LocalDate(2011, 8, 31))

    expect(31) {
      p.days.size
    }

    expect(timeSlot) {
      p.days(2).slots(0)
    }
  }

}