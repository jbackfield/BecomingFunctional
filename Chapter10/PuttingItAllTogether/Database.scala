
object Database {

  def createTable(database : Database) : Database = {
    new Database(database.tables + 
                          (CommandLine.prompt("Table Name") -> Table.create()))
  }

  def describeTable(database : Database) : Database = {
    database.tables.get(CommandLine.prompt("Table Name")) match {
      case Some(table) => table.describe()
      case _ => println("Table does not exist")
    }
    database
  }

  def insert(database : Database) : Database = {
    val tableName = CommandLine.prompt("Table Name")
    database.tables.get(tableName) match {
      case Some(table) => {
        new Database(database.tables + (tableName -> table.insert()))
      }
      case _ => { println("Table does not exist"); database }
    }
  }

  def select(database : Database) : Database = {
    database.tables.get(CommandLine.prompt("Table Name")) match {
      case Some(table) => table.select()
      case _ => println("Table does not exist")
    }
    database
  }

  def delete(database : Database) : Database = {
    val tableName = CommandLine.prompt("Table Name")
    database.tables.get(tableName) match {
      case Some(table) => new Database(
                            database.tables + (tableName -> table.delete()))
      case _ => { println("Table does not exist"); database }
    }
  }
}

case class Database(tables : Map[String, Table]) {}