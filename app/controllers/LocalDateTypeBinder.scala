package controllers

import play.data.binding.TypeBinder
import org.joda.time.LocalDate
import java.lang.annotation.Annotation
import java.lang.reflect.Type
import models.DateUtil

/**
 * User: joris
 * Date: 8/9/11
 * Time: 12:05 AM
 *
 *
 */

class LocalDateTypeBinder extends TypeBinder[LocalDate]{
  def bind(name: String, anns: Array[Annotation], value: String, clazz : Class[_], tipe: Type) = {
    val ld = DateUtil.DATE_FORMATTER.parseDateTime(value).toLocalDate
    ld
  }
}