object Contact {
}

class Contact(val contact_id : Integer, 
              val firstName : String, 
              val lastName : String, 
              val email : String, 
              val enabled : Boolean) {

  def sendEmail() = {
    new Email(email, "My Subject", "My Body").send()
  }

}
