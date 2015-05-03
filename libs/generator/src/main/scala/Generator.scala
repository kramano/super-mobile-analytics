import Events._
import org.scalacheck.Gen
import java.util.Calendar

object Generator {
  private def getGameIdGen: Gen[Int] = {
    Gen.choose(0, 1000)
  }

  private def getUserGen: Gen[User] = {
    val genId = Gen.choose(100000L, 999999L)
    val genGender = Gen.oneOf(0, 1)
    val genAge = Gen.choose(18, 80)

    for {
      id     <- genId
      gender <- genGender
      age    <- genAge
    } yield User(id, gender, age)
  }

  private def getClientGen: Gen[Client] = {
    val ipGen = Gen.choose(0, 255)
    val codeGen = Gen.oneOf("US", "CA", "BY", "DE", "FR", "BE", "GB")
    val softwareGen = Gen.oneOf("iOS 7", "Android 5.0", "Windows 10", "Ubuntu 14.04", "OSX 10.10.3")

    val ipAddressGen = for {
      ip <- Gen.listOfN(4, ipGen)
    } yield ip.map(_.toString).foldLeft("")((e, acc) => e + "." + acc).tail

    for {
      ip       <- ipAddressGen
      code     <- codeGen
      software <- softwareGen
    } yield Client(ip, code, software)
  }

  private def getTimeAndNonce: (String, Int) = {
    val time = Calendar.getInstance().getTime.toString
    (time, math.abs(time.hashCode))
  }

  def getLevelCompleteGen: Gen[LevelComplete] = {
    val (time, nonce) = getTimeAndNonce

    for {
      user        <- getUserGen
      client      <- getClientGen
      gameId      <- getGameIdGen
      levelNumber <- Gen.choose(1, 100)
    } yield LevelComplete(user, client, gameId, levelNumber, time, nonce)
  }

  def getInGamePurchase: Gen[InGamePurchase] = {
    val (time, nonce) = getTimeAndNonce
    for {
      user       <- getUserGen
      client     <- getClientGen
      gameId     <- getGameIdGen
      gameItemId <- Gen.choose(1, 200)
    } yield InGamePurchase(user, client, gameId, gameItemId, time, nonce)
  }

  def getGameInstalled: Gen[GameInstalled] = {
    val (time, nonce) = getTimeAndNonce
    for {
      user   <- getUserGen
      client <- getClientGen
      gameId <- getGameIdGen
    } yield GameInstalled(user, client, gameId, time, nonce)
  }
}
