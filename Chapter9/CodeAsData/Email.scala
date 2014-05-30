object Email {

  def send(to : String, subject : String, body : String) : Boolean = {
    println("To: " + to + "\nSubject: " + subject + "\nBody: " + body)
    true
  }

  def send(msg : Email) : Boolean = {
    msg match {
      case Email(address, subject, body, true, name) => 
        send(address, subject, "Dear " + name + ",\n" + body)
      case Email(address, subject, body, _, _) => 
        send(address, subject, body)
    }
    true
  }
}

case class Email(val address : String,
      val subject : String,
      val body : String,
      val isDearReader : Boolean,
      val name : String) {

  def send() : Boolean = Email.send(this)

}