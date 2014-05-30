import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class Customer {

	static public List<Customer> allCustomers = new ArrayList<Customer>();
	public final Integer id = 0;
	public final String name = "";
	public final String state = "";
	public final String domain = "";
	public final Boolean enabled = true;
	public final Contract contract = null;
	public final List<Contact> contacts = new ArrayList<Contact>();
	@Lazy public List<Contact> enabledContacts = contacts.findAll { contact -> 
		contact.enabled 
	} 

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

	public static ArrayList<Customer> getCustomerById(
			ArrayList<Customer> inList, 
			final Integer id) {
		inList.findAll({customer -> customer.id == id })
	}

	public static void eachEnabledContact(Closure cls) {
		Customer.allCustomers.findAll { customer -> 
			customer.enabled && customer.contract.enabled
		}.each { customer -> 
			customer.contacts.each(cls)
		}
	}

	public static List<Customer> updateCustomerByIdList(
			List<Customer> initialIds, 
			List<Integer> ids, 
			Closure cls) {
		if(ids.size() <= 0) {
			initialIds
		} else if(initialIds.size() <= 0) {
			[]
		} else {
			def idx = ids.indexOf(initialIds[0].id)
			def cust = idx >= 0 ? cls(initialIds[0]) : initialIds[0]
			[cust] + updateCustomerByIdList(
				initialIds.drop(1), 
				idx >= 0 ? ids.minus(initialIds[0].id) : ids, 
				cls
			)
		}
	}

	public static List<Customer> updateContactForCustomerContact(
			Integer id, 
			Integer contact_id, 
			Closure cls) {
		updateCustomerByIdList(Customer.allCustomers, [id], { customer ->
			new Customer(
				customer.id,
				customer.name,
				customer.state,
				customer.domain,
				customer.enabled,
				customer.contract,
				customer.contacts.collect { contact ->
					if(contact.contact_id == contact_id) {
						cls(contact)
					} else {
						contact
					}
				}
			)
		})
	}

	public static List<Customer> updateContractForCustomerList(
			List<Integer> ids, 
			Closure cls) {
		updateCustomerByIdList(Customer.allCustomers, ids, { customer ->
			new Customer(
				customer.id,
				customer.name,
				customer.state,
				customer.domain,
				customer.enabled,
				cls(customer.contract),
				customer.contacts
			)
		})
	}

	public static def countEnabledCustomersWithNoEnabledContacts = { 
					List<Customer> customers, Integer sum ->
		if(customers.isEmpty()) {
			return sum
		} else {
			int addition = (customers.head().enabled && 
				(customers.head().contacts.find({ contact -> 
					contact.enabled 
				}) == null)) ? 1 : 0
			return countEnabledCustomersWithNoEnabledContacts.trampoline(
				customers.tail(), 
				addition + sum
			)
		}
	}.trampoline()
}
