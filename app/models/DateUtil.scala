package models

import java.text.SimpleDateFormat
import java.util.{GregorianCalendar, Calendar, Date}

/**
 * User: joris
 * Date: 8/4/11
 * Time: 1:09 AM
 *
 *
 */

object DateUtil {
  def getMinutes(date: Date)= gregCal(date).get(Calendar.MINUTE)

  def getOptionalMinutes(date: Option[Date]): Option[Int] = if(date.isDefined) Some(getMinutes(date.get)) else None


  def getHours(date : Date) = gregCal(date).get(Calendar.HOUR_OF_DAY)

  def getOptionalHours(date: Option[Date]): Option[Int] = if(date.isDefined) Some(getHours(date.get)) else None


  val DATETIME_FORMAT = "yyyy-MM-dd HH:mm"
  val DATE_FORMAT = "yyyy-MM-dd"

   def format(date : Date ) = {
     new SimpleDateFormat(DATETIME_FORMAT).format(date)
   }

  def formatDate(date : Date ) = {
     new SimpleDateFormat(DATE_FORMAT).format(date)
   }

  def adjustTimeForDate(hours: Int, minutes: Int, date: Date): Date = {
    var cal = new GregorianCalendar()
    cal.setTime(date)
    cal.set(Calendar.HOUR_OF_DAY, hours)
    cal.set(Calendar.MINUTE, minutes)
    cal.getTime
  }

  def endOfMonth(date : Date) = {
    val cal = new GregorianCalendar()
    cal.setTime(date)
    val maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), maxDay).getTime
  }

  def beginOfMonth(date :Date) = {
    val cal: GregorianCalendar = new GregorianCalendar()
    cal.setTime(date)
    new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1).getTime
  }

  def gregCal(date : Date) = {
    val cal = new GregorianCalendar()
    cal.setTime(date)
    cal
  }
}