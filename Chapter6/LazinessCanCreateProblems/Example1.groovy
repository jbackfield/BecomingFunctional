
class Customer {
  final Integer id
  final Boolean enabled
  public Customer(id, enabled) { this.id = id; this.enabled = enabled; }
}

class CustomerContainer {
  public List<Customer> customers = []
  @Lazy public volatile List<Customer> onlyEnabled = { 
    customers.findAll { customer -> 
      customer.enabled 
    } 
  }()
  public CustomerContainer() { this([]) }
  public CustomerContainer(customers) { this.customers = customers }
  def addCustomer(c) {
    new CustomerContainer(customers.plus(customers.size(), [c]))
  }
  def removeCustomer(c) {
    new CustomerContainer(customers.findAll { customer -> customer.id != c.id })
  }
}

def cc = new CustomerContainer()
cc = cc.addCustomer(new Customer(1, true))
cc = cc.addCustomer(new Customer(2, false))
println(cc.customers)
