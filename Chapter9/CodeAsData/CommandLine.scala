case class CommandLineOption(description : String, func : () => Unit)

object CommandLine {

  val options : Map[String, CommandLineOption] = Map(
    "1" -> new CommandLineOption("Add Customer", Customer.createCustomer),
    "2" -> new CommandLineOption("List Customers", Customer.list),
    "3" -> new CommandLineOption("List Enabled Contacts for Enabled Customers", 
      () => Customer.eachEnabledContact(contact => println(contact))
    ),
    "q" -> new CommandLineOption("Quit", sys.exit)
  )

  def askForInput(question : String) : String = {
  	print(question + ": ")
  	readLine()
  }

  def prompt() = {
    options.foreach(option => println(option._1 + ") " + option._2.description))
    options.get(askForInput("Option").trim.toLowerCase) match {
      case Some(CommandLineOption(_, exec)) => exec()
      case _ => println("Invalid input")
    }
  }

  def main(args : Array[String]) = {
  	Customer.allCustomers = List()
  	while(true) {
      prompt()
  	}
  }
}