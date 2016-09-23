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

  public Customer() {}

  private interface Function1<A1,B> {
    public B call(A1 in1);
  }

  static private class CustomerAddress implements Function1<Customer, String> {
    public String call(Customer customer) { return customer.address; }
  }

  static private class CustomerName implements Function1<Customer, String> {
    public String call(Customer customer) { return customer.name; }
  }

  static private class CustomerState implements Function1<Customer, String> {
    public String call(Customer customer) { return customer.state; }
  }

  static private class CustomerPrimaryContact implements Function1<Customer, String>
  {
    public String call(Customer customer) { return customer.primaryContact; }
  }

  static private class CustomerDomain implements Function1<Customer, String> {
    public String call(Customer customer) { return customer.domain; }
  }

  static private class CustomerAsCustomer implements Function1<Customer, Customer> {
    public Customer call(Customer customer) { return customer; }
  }

  public static List<String> getEnabledCustomerAddresses() {
    return Customer.getEnabledCustomerField(new CustomerAddress());
  }

  public static List<String> getEnabledCustomerNames() {
    return Customer.getEnabledCustomerField(new CustomerName());
  }

  public static List<String> getEnabledCustomerStates() {
    return Customer.getEnabledCustomerField(new CustomerState());
  }

  public static List<String> getEnabledCustomerPrimaryContacts() {
    return Customer.getEnabledCustomerField(new CustomerPrimaryContact());
  }

  public static List<String> getEnabledCustomerDomains() {
    return Customer.getEnabledCustomerField(new CustomerDomain());
  }

  public static <B> List<B> getEnabledCustomerField(Function1<Customer,B> func) {
    ArrayList<B> outList = new ArrayList<B>();
    for(Customer customer : Customer.allCustomers) {
      if(customer.enabled) {
        outList.add(func.call(customer));
      }
    }
    return outList;
  }
}
