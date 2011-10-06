package models

import org.joda.time.LocalDate
import java.io.ByteArrayOutputStream
import com.itextpdf.text._
import pdf.{PdfPCell, PdfPTable, PdfWriter}

/**
 * User: joris
 * Date: 9/8/11
 * Time: 1:24 PM
 *
 *
 */
class TimeSheet(organisation: Organisation, beginDate: LocalDate, endDate: LocalDate) {
  val document = new Document();

  val outputStream = new ByteArrayOutputStream
  PdfWriter.getInstance(document, outputStream);
  document.open();

  val table = new PdfPTable(2)
  table.getDefaultCell.setBorderWidth(0)
  //table.getDefaultCell.s
  table.setWidths(Array(4, 1))

  val leftColumn = new PdfPCell()
  leftColumn.setBorderWidth(0)
  val period = new TimePeriod(organisation, beginDate, endDate)
  leftColumn.addElement(new TimeSheetTable(period))
  val firstTimeSlot = period.timeSlots
  table.addCell(leftColumn)

  val rightColumn = new PdfPCell()
  rightColumn.setBorderWidth(0)
  //rightColumn.addElement(new ProjectTable(organisation, beginDate.getYear, beginDate.getMonthOfYear))
  //rightColumn.addElement(new PersonnelTable())
  //rightColumn.addElement(new RemarkTable())
  //rightColumn.addElement(new SignatureTable())
  table.addCell(rightColumn)

  document.add(table);
  document.close();

  def asOutputStream = outputStream

}

class ProjectTable(organisation: Organisation, year: Int, month: Int) extends Table {
  resetColumnCount(2)
  add("projectgegevens", _.setColspan(2))
  add("projectnummer")
  add("3678")
  add("maand")
  add(month.toString)
  add("jaar")
  add(year.toString)
  add("opdrachtgever")
  add(organisation.name)
  add("adres")
  add(organisation.address.street + " " + organisation.address.houseNumber)
  add("postcode")
  add(organisation.address.postalCode)
  add("plaats")
}

class PersonnelTable() extends Table {
  resetColumnCount(2)
  add("personeelsgegevens", _.setColspan(2))
  add("Naam")
  add("Joris")
  add("Personeelsnr")
  add("102469")
}

class RemarkTable() extends Table {
  resetColumnCount(1)
  add("opmerkingen", _.setColspan(2))
  add("")
  add("")
  add("")
  add("")
}

class SignatureTable() extends Table {
  resetColumnCount(2)
  add("handtekening opdrachtgever", _.setColspan(2))
  add("Opdrachtgever tekent voor akkoord en bevestigt dat de ingevulde", _.setColspan(2))
  add("uren en door te berekenen kosten juist zijn, is tevreden over de", _.setColspan(2))
  add("verrichte werkzaamheden en verklaart bekend te zijn met de", _.setColspan(2))
  add("Algemene Voorwaarden en deze te aanvaarden.", _.setColspan(2))
  add("", _.setColspan(2))
  add("", _.setColspan(2))
  add("", _.setColspan(2))
  add("Naam")
  add("Jan-Kees Hartog")
  add("Datum")
  add("")
}

case class TimeSheetTable(timePeriod: TimePeriod) extends Table {
  resetColumnCount(4)
  setWidthPercentage(100)


  add("")
  add("Te factureren", _.setColspan(3))

  add("PERIODE")
  add("NORMAAL")
  add("OVERUREN")
  add("STANDBY")

  timePeriod.days.foreach(d => {
    add(d.date.toString)
    add(d.total)
    add("")
    add("")
  })

  addEmptyRow

  add("TOTAAL")
  add(timePeriod.total)
  add("")
  add("")

}

class Table extends PdfPTable {

  def add(text: String, f: (PdfPCell) => Unit = {
    p => Unit
  }) {
    val cell = new PdfPCell(new Phrase(text))
    f.apply(cell)
    addCell(cell)
  }

  def addEmptyRow {
    add(" ", _.setBorderWidth(0))
    add(" ", _.setBorderWidth(0))
    add(" ", _.setBorderWidth(0))
    add(" ", _.setBorderWidth(0))
  }

}