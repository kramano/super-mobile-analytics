object Events {
  case class User(userId: Long, gender: Int, age: Int)
  case class Client(ip: String, countryCode: String, software: String)

  trait Event{
    val time: Long
    val nonce: Long
    val gameId: Long
  }

  case class LevelComplete(user: User, client: Client, gameId: Long,
                           levelNumber: Int, time: Long, nonce: Long) extends Event {
  }

  case class InGamePurchase(user: User, client: Client, gameId: Long,
                            gameItem: Int, time: Long, nonce: Long) extends Event

  case class GameInstalled(user: User, client: Client, gameId: Long,
                           time: Long, nonce: Long) extends Event
}
