import java.util.Calendar

object Contract {

  def setContractForCustomerList(ids : List[Integer], 
                                 status : Boolean) : List[Customer] = {
  	Customer.updateContractForCustomerList(ids, { contract => 
      new Contract(contract.begin_date, contract.end_date, status)
    })
  }	

}

class Contract(val begin_date : Calendar, 
               val end_date : Calendar, 
               val enabled : Boolean) {

  def this(begin_date : Calendar, enabled : Boolean) = this(begin_date, {
    val c = Calendar.getInstance()
    c.setTimeInMillis(begin_date.getTimeInMillis)
    c.add(Calendar.YEAR, 2)
    c
  }, enabled)

}
