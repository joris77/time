package models


import controllers.Formatting.DurationFormatter
import collection.Seq

/**
 * User: joris
 * Date: 9/2/11
 * Time: 3:00 PM
 *
 *
 */

class TimeSlotCalculations(val slots : Seq[TimeSlot]) {
     def total = {
       val slotsWithDuration = slots.filter(_.duration.isDefined)
       if(slotsWithDuration.nonEmpty) new DurationFormatter(slotsWithDuration.map(_.duration.get).reduceLeft(_.plus(_))).format else "0:0"
     }

}