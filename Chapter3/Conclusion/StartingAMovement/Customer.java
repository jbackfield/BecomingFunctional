import java.util.ArrayList;
import java.util.List;

public class Customer {

  static public ArrayList<Customer> allCustomers = new ArrayList<Customer>();
  public Integer id = 0;
  public String name = "";
  public String address = "";
  public String state = "";
  public String primaryContact = "";
  public String domain = "";
  public Boolean enabled = true;
  public Contract contract;

  public Customer() {}


  public Customer setCustomerId(Integer customer_id) {
    this.customer_id = customer_id;
    return this;
  }       

  public Customer setName(String name) {
    this.name = name;
    return this;
  }

  public Customer setState(String state) {
    this.state = state;
    return this;
  }

  public Customer setDomain(String domain) {
    this.domain = domain;
    return this;
  }

  public Customer setEnabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public Customer setContract(Contract contract) {
    this.contract = contract;
    return this;
  }

  static def EnabledCustomer = { customer -> customer.enabled == true }
  static def DisabledCustomer = { customer -> customer.enabled == false }

  public static List<String> getDisabledCustomerNames() {
    Customer.allCustomers.findAll(DisabledCustomer).collect({cutomer -> 
      cutomer.name 
    })
  }

  public static List<String> getEnabledCustomerStates() {
    Customer.allCustomers.findAll(EnabledCustomer).collect({cutomer -> 
      cutomer.state
    })
  }

  public static List<String> getEnabledCustomerDomains() {
    Customer.allCustomers.findAll(EnabledCustomer).collect({cutomer -> 
      cutomer.domain
    })
  }

  public static List<String> getEnabledCustomerSomeoneEmail(String someone) {
    Customer.allCustomers.findAll(EnabledCustomer).collect({cutomer -> 
      someone + "@" + cutomer.domain
    })
  }

  public static ArrayList<Customer> getCustomerById(ArrayList<Customer> inList, 
                                                    final Integer customer_id) {
    inList.findAll({customer -> customer.customer_id == customer_id })
  }
}