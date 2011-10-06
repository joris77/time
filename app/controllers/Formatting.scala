package controllers

import models.DateUtil
import org.joda.time.{Duration, LocalDateTime}
import org.joda.time.chrono.GregorianChronology
import java.text.Normalizer.Form
import controllers.Formatting.DurationFormatter

object Formatting {

  implicit def localDateTimeToRich(time: Option[LocalDateTime]): Formatter = time match {
    case Some(time) => new Formatter {
      def format = DateUtil.format(time)
    }
    case _ => NoneFormatter
  }

  implicit def durationToRich(time: Option[Duration]): Formatter = time match {
    case Some(d) =>  new DurationFormatter(d)
    case _ => NoneFormatter
  }

  class DurationFormatter(d : Duration) extends  Formatter {
      def format : String = "%02d".format(d.toPeriod(GregorianChronology.getInstance()).getHours()) + ":" + "%02d".format(d.toPeriod(GregorianChronology.getInstance()).getMinutes())
    }



  object NoneFormatter extends Formatter {
    def format = ""
  }

  trait Formatter {
    def format : String
  }

}