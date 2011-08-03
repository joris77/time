package nl.smartworkx.time

import play.db.anorm.defaults._
import java.util.{Calendar, GregorianCalendar, Date}
import play.db.anorm._

/**
 * User: joris
 * Date: 8/1/11
 * Time: 12:16 AM
 *
 *
 */

case class TimeSlot(id:Pk[Long],beginTime : Date, endTime : Option[Date], description : Option[String]) {
  override def toString = beginTime + " - " + endTime.get + " : " + description.get
}

object TimeSlot extends Magic[TimeSlot]   {
  def findTimeSlotsByMonth(year: Int, month: Int): scala.List[TimeSlot] = {
    val startOfMonthCalendar: GregorianCalendar = new GregorianCalendar(year, month - 1, 1)
    val startOfMonth = startOfMonthCalendar.getTime
    val maxDay = startOfMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    val endOfMonth = new GregorianCalendar(year, month - 1, maxDay).getTime
    val timeSlots: List[TimeSlot] = SQL("select * from TimeSlot where beginTime > {startOfMonth} and endTime < {endOfMonth} order by beginTime")
      .on("startOfMonth" -> startOfMonth)
      .on("endOfMonth" -> endOfMonth).as(TimeSlot *)
    timeSlots
  }
}