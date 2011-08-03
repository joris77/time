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
  override def toString = {
    var durationString = ""
    if(duration.isDefined) durationString = duration.get.toString
    var endTimeString = ""
    if(endTime.isDefined) endTimeString = DateUtil.format(endTime.get)

    DateUtil.format(beginTime) + " - " + endTimeString + " :  " + durationString + " " + (if(description.isDefined) description.get else "")
  }

  def duration = {
    if(endTime.isDefined){
      val milliseconds1 = beginTime.getTime;
      val milliseconds2 = endTime.get.getTime;
      val diff = milliseconds2 - milliseconds1;
      val diffHours : Double = diff / (60.0 * 60.0 * 1000.0);
      Some(diffHours)
    }else{
      None
    }
  }
}

object TimeSlot extends Magic[TimeSlot]   {
  def findTimeSlotsByMonth(year: Int, month: Int): scala.List[TimeSlot] = {
    val startOfMonthCalendar: GregorianCalendar = new GregorianCalendar(year, month - 1, 1)
    val startOfMonth = startOfMonthCalendar.getTime
    val maxDay = startOfMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    val endOfMonth = new GregorianCalendar(year, month - 1, maxDay).getTime
    val timeSlots: List[TimeSlot] = SQL("select * from TimeSlot where beginTime > {startOfMonth} and (endTime < {endOfMonth} or endTime is null) order by beginTime")
      .on("startOfMonth" -> startOfMonth)
      .on("endOfMonth" -> endOfMonth).as(TimeSlot *)
    timeSlots
  }
}