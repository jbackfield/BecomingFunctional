object CommandLine {

  def wrapOutput(wrapper : String, output : String) : Unit = {
    println(wrapper)
    print(output)
    println(wrapper)
  }

  def optionPrompt(options : Map[String, CommandLineOption]) : 
                             Option[CommandLineOption] = {
    println()
    println("----[Options]----")
    options.foreach(option => println(option._1 + ") " + option._2.name))
    options.get(prompt("Action").toLowerCase)
  }

  def prompt(msg : String) : String = {
  	print(msg + ": ")
  	readLine()
  }

}