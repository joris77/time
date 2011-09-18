package models

import play.db.anorm._
import models.defaults._
import play.db.anorm.SqlParser._

/**
 * User: joris
 * Date: 9/14/11
 * Time: 10:59 PM
 *
 */
case class Address(id: Pk[Long],street : String, houseNumber : String, city : String, postalCode : String)

object Address extends Magic[Address]