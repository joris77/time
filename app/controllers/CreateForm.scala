package controllers

import models.{TimeSlot, DateUtil}
import java.util.{Calendar, Date}
import play.db.anorm.{NotAssigned, Pk}

/**
 * User: joris
 * Date: 8/5/11
 * Time: 1:03 AM
 *
 *
 */

case class CreateForm(id : Pk[Long] = NotAssigned,date : Date = new Date, beginTimeHours: Int , beginTimeMinutes: Int, endTimeHours: Option[Int] = None, endTimeMinutes: Option[Int] = None, description: Option[String] = None) {

  def this(timeSlot :TimeSlot) = {
    this(timeSlot.id,timeSlot.beginTime,DateUtil.getHours(timeSlot.beginTime),DateUtil.getMinutes(timeSlot.beginTime),DateUtil.getOptionalHours(timeSlot.endTime),DateUtil.getOptionalMinutes(timeSlot.endTime),timeSlot.description)
  }

  def beginTime = {
    DateUtil.adjustTimeForDate(beginTimeHours, beginTimeMinutes, date)
  }

  def endTime: Option[Date] = if (endTimeHours.isDefined) Some(DateUtil.adjustTimeForDate(endTimeHours.get,endTimeMinutes.get,date)) else None

  def createTimeSlot(): TimeSlot = {
    TimeSlot(id, beginTime, endTime, description)
  }
}
