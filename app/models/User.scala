package models

import play.db.anorm.defaults._
import java.lang.Long
import play.db.anorm._

/**
 * User: joris
 * Date: 8/3/11
 * Time: 11:36 PM
 *
 *
 */

case class User(id : Pk[Long], username : String, password : String)

object User extends Magic[User] {

}