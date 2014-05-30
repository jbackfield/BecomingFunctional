import java.util.Calendar

object Customer {

  var allCustomers = List[Customer]()

  def EnabledCustomer(customer : Customer) : Boolean = customer.enabled == true
  def DisabledCustomer(customer : Customer) : Boolean = customer.enabled == false

  def getDisabledCustomerNames() : List[String] = {
    Customer.allCustomers.filter(DisabledCustomer).map({ customer => 
      customer.name
    })
  }

  def getEnabledCustomerStates() : List[String] = {
    Customer.allCustomers.filter(EnabledCustomer).map({ customer => 
      customer.state
    })
  }

  def getEnabledCustomerDomains() : List[String] = {
    Customer.allCustomers.filter(EnabledCustomer).map({ customer => 
      customer.domain
    })
  }

  def getEnabledCustomerSomeoneEmail(someone : String) : List[String] = {
    Customer.allCustomers.filter(EnabledCustomer).map({ customer => 
      someone + "@" + customer.domain
    })
  }

  def getCustomerById(inList : List[Customer], 
                      customer_id : Integer) : List[Customer] = {
    inList.filter(customer => customer.customer_id == customer_id)
  }

  def eachEnabledContact(cls : Contact => Unit) {
    Customer.allCustomers.filter({ customer => 
      customer.enabled && customer.contract.enabled
    }).foreach({ customer => 
      customer.contacts.foreach(cls)
    })
  }

  def updateCustomerByIdList(initialIds : List[Customer], 
                           ids : List[Integer], 
                           cls : Customer => Customer) : List[Customer] = {
    (initialIds, ids) match {
      case (List(), _) => initialIds
      case (_, List()) => initialIds
      case (_, id :: tailIds) => {
        val precust = initialIds.find(cust => cust.customer_id == id)
        precust match {
          case None => updateCustomerByIdList(initialIds, tailIds, cls)
          case Some(cust) => updateCustomerByIdList(
            initialIds.filter(cust => cust.customer_id == id),
            tailIds,
            cls
          )
        }  
      }
    }
  }

  def updateContactForCustomerContact(customer_id : Integer,  
                                      contact_id : Integer, 
                                      cls : Contact => Contact) : List[Customer] = {
    updateCustomerByIdList(Customer.allCustomers, List(customer_id), { customer =>
      new Customer(
        customer.customer_id,
        customer.name,
        customer.state,
        customer.domain,
        customer.enabled,
        customer.contract,
        customer.contacts.map { contact =>
          if(contact.contact_id == contact_id) {
            cls(contact)
          } else {
            contact
          }
        }
      )
    })
  }

  def updateContractForCustomerList(ids : List[Integer], 
                                    cls : Contract => Contract) : List[Customer] = {
    updateCustomerByIdList(Customer.allCustomers, ids, { customer =>
      new Customer(
        customer.customer_id,
        customer.name,
        customer.state,
        customer.domain,
        customer.enabled,
        cls(customer.contract),
        customer.contacts
      )
    })
  }

  def countEnabledCustomersWithNoEnabledContacts(customers : List[Customer], 
                                                 sum : Integer) : Integer = {
    customers match {
      case List() => sum
      case Customer(_,_,_,_,true,_,List()) :: custs => 
        countEnabledCustomersWithNoEnabledContacts(custs, sum)
      case Customer(_,_,_,_,true,_,cont) :: custs if cont.exists({ contact => 
        contact.enabled}) => countEnabledCustomersWithNoEnabledContacts(custs, sum + 1)
      case cust :: custs => countEnabledCustomersWithNoEnabledContacts(custs, sum)
    }
  }

  def createCustomer(name : String, state : String, domain : String) : Option[Customer] = {
    def error(message : String) : Option[Customer] = {
      println(message)
      None
    }
    (name, state, domain) match {
      case ("", _, _) => error("Name cannot be blank")
      case (_, "", _) => error("State cannot be blank") 
      case (_, _, "") => error("Domain cannot be blank")
      case _ => new Some(new Customer(
          0,
          name,
          state,
          domain,
          true,
          new Contract(Calendar.getInstance, true),
          List()
        )
      )
    }
  }

  def createCustomer() : Unit = {
    Customer.allCustomers = Customer.createCustomer(
      CommandLine.askForInput("Name"),
      CommandLine.askForInput("State"),
      CommandLine.askForInput("Domain")
    ).toList :::  Customer.allCustomers
  }

  def list() : Unit = {
    Customer.allCustomers.foreach(customer => println(customer))
  }

  /*def addContact() = {
    val id = CommandLine.askForInput("Customer ID to add contact to").toInt
    Customer.allCustomers = Customer.updateCustomerByIdList(Customer.allCustomers, List(id), customer =>
      println("Got customer " + customer.toString)
      new Customer(
        customer.customer_id,
        customer.name,
        customer.state,
        customer.domain,
        customer.enabled,
        customer.contract,
        Contact.createContact() :: customer.contacts
      )
    )
  }*/
}

case class Customer(val customer_id : Integer, 
               val name : String, 
               val state : String, 
               val domain : String, 
               val enabled : Boolean, 
               val contract : Contract, 
               val contacts : List[Contact]) {
}
