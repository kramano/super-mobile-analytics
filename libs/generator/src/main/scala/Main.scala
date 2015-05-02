import scala.pickling.Defaults._
import scala.pickling.json._
import Events._

object Main extends App {
  val u = User(123132L, 1, 23)
  val c = Client("255.255.255.255", "US", "Ubuntu")

  val e1 = LevelComplete(u, c, 10, 1, 1L, 20L)
  val e2 = GameInstalled(u, c, 10, 1, 20L)
  val e3 = InGamePurchase(u, c, 10, 3, 1L, 20L)

  println(e1.pickle.value)
  println(e2.pickle.value)
  println(e3.pickle.value)

}
