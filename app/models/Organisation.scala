package models

import play.db.anorm._
import models.defaults._
import play.db.anorm.SqlParser._

/**
 * User: joris
 * Date: 9/14/11
 * Time: 10:33 PM
 *
 */
case class Organisation(id: Pk[Long],name: String, addressId: Long) {
  private var _address: Address = _

  def address = _address

  def address_=(address: Address) {
    _address = address
  }
}


object Organisation extends Magic[Organisation] {
  def findOrganisation(id: Long) = {
    val Some(organisation ~ address) = SQL(
      """
          select * from Organisation o
          join Address a on a.id = o.addressId
          where o.id = {id}
      """
    )
      .on("id" -> id)
      .as(Organisation ~< Address ?)

    organisation.address = address
    organisation
  }

}