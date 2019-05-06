package Pokemon.util
import scalikejdbc._
import Pokemon.model.PokemonBoard
trait Database {
  val derbyDriverClassname = "org.apache.derby.jdbc.EmbeddedDriver"

  val dbURL = "jdbc:derby:myDB;create=true;";
  // initialize JDBC driver & connection pool
  Class.forName(derbyDriverClassname)

  ConnectionPool.singleton(dbURL, "me", "mine")

  // ad-hoc session provider on the REPL
  implicit val session = AutoSession


}
object Database extends Database{
  def setupDB() = {
    if (!hasDBInitialize)
      PokemonBoard.initializeTable()
  }
  def hasDBInitialize : Boolean = {

    DB getTable "pokemon" match {
      case Some(x) => true
      case None => false
    }

  }
}
