package controllers

import play._
import mvc.results.RenderBinary
import play.data.binding.As
import play.db.anorm._
import models.{DateUtil, User, TimeSlot}
import models.DateUtil._
import org.joda.time.{LocalDateTime, LocalDate}
import models.TimeSlot._
import com.itextpdf.text.pdf.PdfWriter
import com.itextpdf.text.{Document, Paragraph}
import java.io.{ByteArrayInputStream, ByteArrayOutputStream, FileOutputStream, FileInputStream}

object TimeWriting extends Secure {

  import views.TimeWriting._

  def index = {
    val now = new LocalDate()
    html.index("Time writing", format(beginOfMonth(now)), format(endOfMonth(now)))
  }

  def save(id: Option[Long] = None,
           @As(binder = classOf[LocalDateTypeBinder]) date: LocalDate = new LocalDate(),
           beginTimeHours: Option[Int] = None,
           beginTimeMinutes: Option[Int] = None,
           endTimeHours: Option[Int] = None,
           endTimeMinutes: Option[Int] = None,
           description: Option[String] = None) = {

    def createDateTime(hours: Int, minutes: Int): LocalDateTime = {
      new LocalDateTime(date.getYear, date.getMonthOfYear, date.getDayOfMonth, hours, minutes)
    }

    val beginTime = if (beginTimeHours.isDefined) Some(createDateTime(beginTimeHours.get, beginTimeMinutes.get)) else None

    val endTime = if (endTimeHours.isDefined) Some(createDateTime(endTimeHours.get, endTimeMinutes.get)) else None

    if (id.isDefined) {
      TimeSlot.update(new TimeSlot(Id(id.get), beginTime, endTime, description, 1))
    } else {
      TimeSlot.create(new TimeSlot(NotAssigned, beginTime, endTime, description, 1))
    }
    html.index("Time writing", format(beginOfMonth(date)), format(endOfMonth(date)),
      TimeSlot.findTimeSlots(Some(DateUtil.beginOfMonth(date)), Some(DateUtil.endOfMonth(date))))
  }

  def search(@As(binder = classOf[LocalDateTypeBinder]) beginDate: Option[LocalDate], @As(binder = classOf[LocalDateTypeBinder]) endDate: Option[LocalDate]) = {
    val timeSlots = findTimeSlots(beginDate, endDate)
    html.index("Time writing", format(beginDate.get), if (endDate.isDefined) format(endDate.get) else "", timeSlots)
  }

  def delete(id: Long) = {
    SQL("delete from TimeSlot where id = {id}").on("id" -> id).executeUpdate()
  }

  def show(id: Long) = {
    val timeSlot = TimeSlot.find("id = {id}").on("id" -> id).first().get
    html.timeSlotForm(timeSlot.id.get,
      timeSlot.beginTime.map {
        t => format(t.toLocalDate)
      },
      timeSlot.beginTime.map {
        _.getHourOfDay
      },
      timeSlot.beginTime.map {
        _.getMinuteOfHour
      },
      timeSlot.endTime.map {
        _.getHourOfDay
      },
      timeSlot.endTime.map {
        _.getMinuteOfHour
      },
      timeSlot.description)
  }

  def timesheet(@As(binder = classOf[LocalDateTypeBinder]) beginDate: LocalDate,
                @As(binder = classOf[LocalDateTypeBinder]) endDate: LocalDate) = {

    val document = new Document();
    // step 2
    val outputStream: ByteArrayOutputStream = new ByteArrayOutputStream
    PdfWriter.getInstance(document, outputStream);
    // step 3
    document.open();
    // step 4
    document.add(new Paragraph("Hello World!"));
    // step 5
    document.close();

    outputStream

    new RenderBinary(new ByteArrayInputStream(outputStream.toByteArray())
      , "Timesheet.pdf", "application/pdf", true)

  }

}