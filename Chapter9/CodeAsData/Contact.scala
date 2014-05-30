object Contact {

	def setNameAndEmailForContactAndCustomer(
      customer_id : Integer, 
	    contact_id : Integer, 
	    name : String, 
	    email : String) : List[Customer] = {
    Customer.updateContactForCustomerContact(
    	customer_id,
    	contact_id,
    	{ contact => 
    		new Contact(
          contact.contact_id, 
          contact.firstName, 
          name, 
          email, 
          contact.enabled
        )
    	}
    )
	}

  def createContact() : Contact = {
    new Contact(
      0,
      CommandLine.askForInput("First Name"),
      CommandLine.askForInput("Last Name"),
      CommandLine.askForInput("E-Mail"),
      true
    )
  }
}

class Contact(val contact_id : Integer, 
              val firstName : String, 
              val lastName : String, 
              val email : String, 
              val enabled : Boolean) {

  def sendEmail() = {
    new Email(this.email, "My Subject", "My Body", true, this.firstName).send()
  }

}
