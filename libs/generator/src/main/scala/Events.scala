object Events {
  case class User(userId: Long, gender: Int, age: Int)
  case class Client(ip: String, countryCode: String, software: String)

  trait Event{
    val time: String
    val nonce: Int
    val gameId: Int
  }

  case class LevelComplete(user: User, client: Client, gameId: Int,
                           levelNumber: Int, time: String, nonce: Int) extends Event

  case class InGamePurchase(user: User, client: Client, gameId: Int,
                            gameItem: Int, time: String, nonce: Int) extends Event

  case class GameInstalled(user: User, client: Client, gameId: Int,
                           time: String, nonce: Int) extends Event
}
