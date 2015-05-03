import scala.pickling.Defaults._
import scala.pickling.json._
import scalaj.http._

object Main extends App {
  while (true) {
    val event1 = Generator.getGameInstalled.sample.get
    val event2 = Generator.getLevelCompleteGen.sample.get
    val event3 = Generator.getInGamePurchase.sample.get

    Http("http://localhost:8080/orders").
      postData(event1.pickle.value).header("Content-Type", "application/json").
      charset("UTF-8").asString
    Http("http://localhost:8080/orders").
      postData(event2.pickle.value).header("Content-Type", "application/json").
      charset("UTF-8").asString
    Http("http://localhost:8080/orders").
      postData(event3.pickle.value).header("Content-Type", "application/json").
      charset("UTF-8").asString

    Thread.sleep(10000)
  }
}
