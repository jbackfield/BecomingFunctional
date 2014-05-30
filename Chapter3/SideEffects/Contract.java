import java.util.Calendar;

public class Contract {

  public Calendar begin_date;
  public Calendar end_date;
  public Boolean enabled = true;

  public Contract(Calendar begin_date) {
    this.begin_date = begin_date;
    this.end_date = this.begin_date.getInstance();
    this.end_date.setTimeInMillis(this.begin_date.getTimeInMillis());
    this.end_date.add(Calendar.YEAR, 2);
  }

  public Contract setBeginDate(Calendar begin_date) {
    this.begin_date = begin_date;
    return this;
  }

  public Contract setEndDate(Calendar end_date) {
    this.end_date = end_date;
    return this;
  }

  public Contract setEnabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public static void setContractEnabledForCustomer(Integer customer_id) {
    for(Customer customer : Customer.allCustomers) {
      if(customer.id == customer_id) {
        customer.contract.enabled = true;
      }
    }
  }

}
