package controllers

import play._
import data.binding.As
import play.db.anorm._
import java.util.{Calendar, Date}
import models.{DateUtil, User, TimeSlot}
import models.DateUtil._
import javax.swing.text.html.HTML
import org.joda.time.{LocalTime, LocalDateTime, LocalDate}
import models.TimeSlot._

object Security extends Secure.Security {
  def authenticate(username: String, password: String) = {
    User.find("username = {un} and password = {pw}").on("un" -> username).on("pw" -> password).first().isDefined
  }
}
