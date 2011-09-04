package models

import play.db.anorm.Pk

/**
 * User: joris
 * Date: 9/3/11
 * Time: 11:32 PM
 *
 *
 */

case class Person(id: Pk[Long],initials : String, firstName : String, lastName : String) {

}