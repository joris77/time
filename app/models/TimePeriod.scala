package models

import org.joda.time.LocalDate
import collection.mutable.ListBuffer

case class TimePeriod(organisation : Organisation, beginDate: LocalDate, endDate: LocalDate) {
  val _timeSlots = Factory.instance.timeSlot.findTimeSlots(beginDate, endDate)

  var iterDate = beginDate

  val days = new ListBuffer[Day]
      while (iterDate.isBefore(endDate) || iterDate.isEqual(endDate)) {
                days += new Day(iterDate,_timeSlots.filter(_.beginTime.get.toLocalDate.isEqual(iterDate)))
        iterDate = iterDate.plusDays(1)
      }

  def total = new TimeSlotCalculations(_timeSlots).total

  def timeSlots = _timeSlots

}

case class Day(date: LocalDate, slots: List[TimeSlot]) {
  def total = new TimeSlotCalculations(slots).total

}