import scala.annotation.tailrec

object fDB {

  val options = Map[String, CommandLineOption](
    "create" -> new CommandLineOption("Create Table", Database.createTable),
    "describe" -> new CommandLineOption("Describe Table", Database.describeTable),
    "insert" -> new CommandLineOption("Insert Record", Database.insert),
    "delete" -> new CommandLineOption("Delete Record", Database.delete),
    "select" -> new CommandLineOption("Select Record", Database.select),
    "exit" -> new CommandLineOption("Exit", db => sys.exit)
  )

  @tailrec
  def mainLoop(database : Database) : Unit = mainLoop(
    CommandLine.optionPrompt(options) match {
      case Some(opt) => opt.exec(database)
      case _ => { println("Invalid option"); database }
    }
  )

  def main(args : Array[String]) = {
  	mainLoop(new Database(Map()))
  }

}