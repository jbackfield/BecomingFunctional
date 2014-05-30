import java.util.ArrayList;
import java.util.List;

public class Customer {

  static public List<Customer> allCustomers = new ArrayList<Customer>();
  public final Integer id = 0;
  public final String name = "";
  public final String state = "";
  public final String domain = "";
  public final Boolean enabled = true;
  public final Contract contract = null;
  public final List<Contact> contacts = new ArrayList<Contact>();

  public Customer(Integer id, 
                  String name, 
                  String state, 
                  String domain, 
                  Boolean enabled, 
                  Contract contract, 
                  List<Contact> contacts) {
    this.id = id;
    this.name = name;
    this.state = state;
    this.domain = domain;
    this.enabled = enabled;
    this.contract = contract;
    this.contacts = contacts;
  }


  public Customer setId(Integer id) {
    this.id = id;
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
    Customer.allCustomers.findAll(DisabledCustomer).collect({cutomer -> cutomer.name })
  }

  public static List<String> getEnabledCustomerStates() {
    Customer.allCustomers.findAll(EnabledCustomer).collect({cutomer -> cutomer.state})
  }

  public static List<String> getEnabledCustomerDomains() {
    Customer.allCustomers.findAll(EnabledCustomer).collect({cutomer -> cutomer.domain})
  }

  public static List<String> getEnabledCustomerSomeoneEmail(String someone) {
    Customer.allCustomers.findAll(EnabledCustomer).collect({cutomer -> someone + "@" + cutomer.domain})
  }

  public static ArrayList<Customer> getCustomerById(ArrayList<Customer> inList, final Integer id) {
    inList.findAll({customer -> customer.id == id })
  }
}