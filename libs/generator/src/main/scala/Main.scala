import scala.pickling.Defaults._
import scala.pickling.json._
import Events._
import java.util.Calendar

object Main extends App {
  val u = User(123132L, 1, 23)
  val c = Client("255.255.255.255", "US", "Ubuntu")

  val event1 = Generator.getGameInstalled.sample
  println(event1.get.pickle.value)

  val event2 = Generator.getLevelCompleteGen.sample
  println(event2.get.pickle.value)
}
