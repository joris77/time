package controllers

import play._
import play.db.anorm._
import java.util.{Calendar, GregorianCalendar, Date}
import nl.smartworkx.time.{User, TimeSlot}

object Application extends Secure {

  import views.Application._

  def index = {
    val now = Calendar.getInstance()
    val year: Int = now.get(Calendar.YEAR)
    val month: Int = now.get(Calendar.MONTH) + 1
    html.index("Time writing",year,month)
  }

  def create(year: Int, month: Int, day: Int, beginTimeHours: Int, beginTimeMinutes: Int, endTimeHours: Option[Int], endTimeMinutes: Option[Int], description: Option[String]) = {
    val beginTime = new GregorianCalendar(year, month - 1, day, beginTimeHours, beginTimeMinutes).getTime
    var endTime: Option[Date] = None
    if (endTimeHours.isDefined) endTime = Some(new GregorianCalendar(year, month - 1, day, endTimeHours.get, endTimeMinutes.get).getTime)
    val timeSlot = TimeSlot(NotAssigned, beginTime, endTime, description)
    print("timeslot: " + timeSlot)
    TimeSlot.create(timeSlot)
    html.index("Time writing",year,month, TimeSlot.findTimeSlotsByMonth(year,month))
  }



  def search(year: Int, month: Int) = {
    print("search month " + month + " year " + year)
    val timeSlots: scala.List[TimeSlot] = TimeSlot.findTimeSlotsByMonth(year, month)
    html.index("Time writing",year,month, timeSlots)
  }

  def delete(id : Long) = {
    SQL("delete from TimeSlot where id = {id}").on("id" -> id).executeUpdate()
  }

}

object Security extends Secure.Security {
  def authenticate(username : String, password : String ) = {
    User.find("username = {un} and password = {pw}").on("un" -> username).on("pw" -> password).first().isDefined
  }
}
