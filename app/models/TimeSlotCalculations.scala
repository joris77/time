package models


import controllers.Formatting.DurationFormatter

/**
 * User: joris
 * Date: 9/2/11
 * Time: 3:00 PM
 *
 *
 */

class TimeSlotCalculations(val slots : Seq[TimeSlot]) {
     def total = if(slots.size > 0) new DurationFormatter(slots.filter(_.duration.isDefined).map(_.duration.get).reduceLeft(_.plus(_))).format else "0:0"
}