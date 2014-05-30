
import java.util.List;
import java.util.Calendar;

public class Contract {

	public final Calendar begin_date;
	public final Calendar end_date;
	public final Boolean enabled = true;

	public Contract(Calendar begin_date, Calendar end_date, Boolean enabled) {
		this.begin_date = begin_date;
		this.end_date = end_date;
		this.enabled = enabled;
	}

	public Contract(Calendar begin_date, Boolean enabled) {
		this.begin_date = begin_date;
		this.end_date = this.begin_date.getInstance();
		this.end_date.setTimeInMillis(this.begin_date.getTimeInMillis());
		this.end_date.add(Calendar.YEAR, 2);
		this.enabled = enabled;
	}

	public static List<Customer> setContractForCustomerList(
			List<Integer> ids, 
			Boolean status) {
		Customer.updateContractForCustomerList(ids) { contract -> 
			new Contract(contract.begin_date, contract.end_date, status) 
		}
	}

}
