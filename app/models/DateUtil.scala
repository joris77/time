package models

import java.text.SimpleDateFormat
import java.util.{GregorianCalendar, Calendar, Date}
import xsbti.api.Val
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}
import org.joda.time.{LocalDateTime, DateTimeFieldType, LocalDate}

/**
 * User: joris
 * Date: 8/4/11
 * Time: 1:09 AM
 *
 *
 */

object DateUtil {

  val DATE_FORMAT = "yyyy-MM-dd"
  val DATE_TIME_FORMAT = DATE_FORMAT + " HH:mm"
  val DATE_TIME_FORMATTER = DateTimeFormat.forPattern(DATE_TIME_FORMAT)
  val DATE_FORMATTER = DateTimeFormat.forPattern(DATE_FORMAT)



  def format(date: LocalDateTime) = {
    DATE_TIME_FORMATTER.print(date)
  }

  def format(date: LocalDate) = {
    DATE_FORMATTER.print(date)
  }

  def currentHours = new LocalDateTime().getHourOfDay

  def currentMinutes = new LocalDateTime().getMinuteOfHour

  def endOfMonth(date: LocalDate)  = {
    date.property(DateTimeFieldType.dayOfMonth()).withMaximumValue()
  }

  def beginOfMonth(date: LocalDate) = {
    date.property(DateTimeFieldType.dayOfMonth()).withMinimumValue()
  }
}