package nl.smartworkx.time

import java.util.Date
import java.text.SimpleDateFormat

/**
 * User: joris
 * Date: 8/4/11
 * Time: 1:09 AM
 *
 *
 */

object DateUtil {

   val DATE_FORMAT = "yyyy-MM-dd HH:mm"

   def format(date : Date ) = {
     new SimpleDateFormat(DATE_FORMAT).format(date)
   }
}