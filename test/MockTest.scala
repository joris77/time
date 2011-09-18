import models.Factory
import org.mockito.Mockito._


/**
 * User: joris
 * Date: 9/8/11
 * Time: 11:47 PM
 *
 *
 */

trait MockTest {
  val mockFactory: Factory = mock(classOf[Factory])
  Factory.mockFactory(mockFactory)


}