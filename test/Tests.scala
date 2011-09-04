import org.mockito.Mockito._
import org.mockito.Matchers._

import models.{TimeSlotCalculations, TimeSlot}
import org.joda.time.{Duration, LocalDateTime}
import org.scalatest.FunSuite
import play._
import db.anorm.NotAssigned
import play.test._





class TimeSlotTotalTests extends  FunSuite  {
  test("total"){
    val t1 = mock(classOf[TimeSlot])
    val t2 = mock(classOf[TimeSlot])
    when(t1.duration).thenReturn(Some(new Duration(1000 * 60 * 210)))
    when(t2.duration).thenReturn(Some(new Duration(1000 * 60 * 240)))
    expect("7:30"){
      new TimeSlotCalculations(List(t1,t2)).total
    }
  }

  test("total with None"){
    val t1 = mock(classOf[TimeSlot])
    val t2 = mock(classOf[TimeSlot])
    when(t1.duration).thenReturn(Some(new Duration(1000 * 60 * 210)))
    when(t2.duration).thenReturn(None)
    expect("3:30"){
      new TimeSlotCalculations(List(t1,t2)).total
    }
  }

  test("empty collection"){
    expect("0:0"){
      new TimeSlotCalculations(List()).total
    }
  }
}