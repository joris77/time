package models

import play.db.anorm.Pk

/**
 * User: joris
 * Date: 9/3/11
 * Time: 11:34 PM
 *
 *
 */

case class Executor(id: Pk[Long],person_id : Long,task_id : Long){

}