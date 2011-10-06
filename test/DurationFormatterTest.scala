import controllers.Formatting.DurationFormatter
import org.joda.time.Duration
import org.scalatest.FunSuite

/**
 * User: joris
 * Date: 10/6/11
 * Time: 5:54 PM
 *
 *
 */

class DurationFormatterTest extends FunSuite with MockTest{



  test("when hours and minutes are less than 10 then prepend a zero") {

       val formatter = new DurationFormatter(new Duration(60000))

    expect("00:01"){
      formatter.format
    }
  }

  test("format zero hours") {

       val formatter = new DurationFormatter(new Duration(0))

    expect("00:00"){
      formatter.format
    }
  }


  test("format") {

       val formatter = new DurationFormatter(new Duration(40260000))

    expect("11:11"){
      formatter.format
    }
  }

}