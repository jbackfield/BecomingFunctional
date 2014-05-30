object Table {
  
  def createFields(count : Int, 
                   fields : List[String]) : List[String] = if(count <= 0) {
    fields
  } else {
    createFields(count - 1, fields ::: List(CommandLine.prompt("Field")))
  }

  def create() : Table = new Table(
    createFields(
      CommandLine.prompt("Number of fields").toInt, 
      List()
    ), 
    Map(),
    1
  )

  def insert(table : Table) : Table = new Table(
    table.fields, 
    table.records + (table.id -> Record.create(table.fields, Map())), 
    table.id + 1
  )

  def describe(table : Table) : Table = {
    println("(implied) id")
    table.fields.foreach(field => println(field))
    table
  }

  def select(table : Table) : Table = {
    CommandLine.prompt("Filter By Field? (y/n)").toLowerCase match {
      case "y" => selectWithFilter(table)
      case "n" => selectAll(table)
      case _ => { println("Invalid selection"); select(table); }
    }
  }

  def selectAll(table : Table) : Table = {
    table.records.foreach(record => record._2.print(table.fields, record._1))
    table
  }

  def selectWithFilter(table : Table) : Table = {
    performFilter(
      table,
      CommandLine.prompt("Filter Field"),
      CommandLine.prompt("Field Value")
    ).foreach(record =>
      record._2.print(table.fields, record._1)
    )
    table
  }

  def performFilter(table : Table, 
                    fieldName : String, 
                    fieldValue : String) : Map[Long, Record] = {
    if(fieldName == "id") {
      table.records.get(fieldValue.toLong) match {
        case Some(record) => Map(fieldValue.toLong -> record)
        case _ => Map()
      }
    } else {
      table.records.filter(record => 
        record._2.fieldValues.get(fieldName) match {
          case Some(value) => value == fieldValue
          case _ => false
        }
      )
    }    
  }

  def delete(table : Table) : Table = {
    new Table(table.fields, table.records - CommandLine.prompt("ID").toLong, table.id)
  }

}

case class Table(fields : List[String], records : Map[Long, Record], id : Long) {

  def delete() : Table = {
    Table.delete(this)
  }

  def select() : Table = {
    Table.select(this)
  }

  def insert() : Table = {
    Table.insert(this)
  }

  def describe() : Table = {
    Table.describe(this)
  }

}