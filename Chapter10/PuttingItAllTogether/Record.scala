object Record {

  def create(fields : List[String], 
             fieldValues : Map[String, String]) : Record = fields match {
    case List() => new Record(fieldValues)
    case f :: fs => create(
                            fs, 
                            fieldValues + (f -> CommandLine.prompt("Value [" + f + "]"))
                          )
  }

  def print(fields : List[String], id : Long, record : Record) : Unit = {
    def _print(fieldList : List[String], output : String) : String = fieldList match {
      case List() => output
      case f :: fs => _print(fs, output + f + ": " + record.fieldValues(f) + "\n")
    }
    CommandLine.wrapOutput("------------", "id: " + id + "\n" + _print(fields, ""))
  }

}

case class Record(fieldValues : Map[String, String]) {

  def print(fields : List[String], id : Long) : Unit = {
  	Record.print(fields, id, this)
  }

}