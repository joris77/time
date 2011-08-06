package controllers

import play._
import play.db.anorm._
import java.util.{Calendar, Date}
import models.{DateUtil, User, TimeSlot}
import models.DateUtil._
import javax.swing.text.html.HTML

object Application extends Secure {

  import views.Application._

  def index = {
    val now = new Date()
    html.index("Time writing", formatDate(beginOfMonth(now)), formatDate(endOfMonth(now)))
  }

  def create(createForm: CreateForm) = {
    val timeSlot = createForm.createTimeSlot()
    if (createForm.id.isAssigned) {
      TimeSlot.update(timeSlot)
    } else {
      TimeSlot.create(timeSlot)
    }
    html.index("Time writing", formatDate(beginOfMonth(createForm.date)), formatDate(endOfMonth(createForm.date)), TimeSlot.findTimeSlots(DateUtil.beginOfMonth(createForm.date), DateUtil.endOfMonth(createForm.date)))
  }

  def search(beginDate: Date, endDate: Date) = {
    val timeSlots: scala.List[TimeSlot] = TimeSlot.findTimeSlots(beginDate, endDate)
    html.index("Time writing", formatDate(beginDate), formatDate(endDate), timeSlots)
  }

  def url(url: String) = {
    val context: String = play.configuration.conf.getProperty("context")
    val urlWithoutContextRoot: String = "/" + url
    if (context.isEmpty) urlWithoutContextRoot else "/" + context + urlWithoutContextRoot
  }

  def delete(id: Long) = {
    SQL("delete from TimeSlot where id = {id}").on("id" -> id).executeUpdate()
  }

  def show(id: Long) = {
    val createForm = new CreateForm(TimeSlot.find("id = {id}").on("id" -> id).first().get)
    html.createForm(createForm)
  }

}


object Security extends Secure.Security {
  def authenticate(username: String, password: String) = {
    User.find("username = {un} and password = {pw}").on("un" -> username).on("pw" -> password).first().isDefined
  }
}
