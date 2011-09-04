package models

import models.defaults._
import java.util.{Calendar, GregorianCalendar, Date}
import play.db.anorm._
import models.DateUtil._
import java.sql.Timestamp
import models.DateUtil._
import play.utils.Scala.MayErr
import reflect.Manifest
import org.joda.time._

/**
 * User: joris
 * Date: 8/1/11
 * Time: 12:16 AM
 *
 *
 */
case class TimeSlot(id: Pk[Long], beginTime: Option[LocalDateTime], endTime: Option[LocalDateTime], description: Option[String],taskId : Long) {


  override def toString = {
    beginTime + " - " + endTime.map {
      _.toString
    } + " :  " + duration + " " + description.getOrElse("")
  }

  def duration = {
    if (beginTime.isDefined && endTime.isDefined) {
      Some(new Duration(beginTime.get.toDateTime(DateTimeZone.UTC), endTime.get.toDateTime(DateTimeZone.UTC)));
    } else {
      None
    }
  }

  def task = Task.find("id = {id}").on("id" -> taskId).first()

}

object TimeSlot extends Magic[TimeSlot] {

  def findTimeSlots(beginDate: Option[LocalDate], endDate: Option[LocalDate]) = {
    SQL("select * from TimeSlot where beginTime > {beginDate} and (endTime < {endDate} or endTime is null) order by beginTime")
      .on("beginDate" -> beginDate.get.toDateTimeAtStartOfDay().toLocalDateTime, "endDate" -> endDate.get.plusDays(1).toDateTimeAtStartOfDay.toLocalDateTime).as(TimeSlot *)
  }
}