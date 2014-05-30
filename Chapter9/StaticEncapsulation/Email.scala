object Email {

  def send(msg : Email) : Boolean = {
    println("To: " + msg.address + "\nSubject: " + msg.subject + "\nBody: " + msg.body)
    true
  }

}

case class Email(val address : String,
      val subject : String,
			val body : String) {

	def send() : Boolean = Email.send(this)

}