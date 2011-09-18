package models


/**
 * User: joris
 * Date: 9/8/11
 * Time: 11:34 PM
 *
 *
 */

object Factory {

  private var factory : Option[Factory] = None

  def instance : Factory = {
    if(!factory.isDefined){ factory = Some(new Factory) }
    factory.get
  }

  def mockFactory(factory : Factory) {
    this.factory = Some(factory)
  }
}

class Factory {
  def timeSlot : TimeSlotRepository = TimeSlot
}