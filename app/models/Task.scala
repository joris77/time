package models

import play.db.anorm.Pk
import models.defaults._
import play.db.anorm._

/**
 * User: joris
 * Date: 9/3/11
 * Time: 11:31 PM
 *
 *
 */
case class Task(id: Pk[Long],name : String) {
             override def toString = name
}

object Task extends Magic[Task]{

}