import java.util.ArrayList;
import java.util.List;

public class FunctionalConcepts {

  private FunctionalConcepts() {}

  public static <A1,B> List<B> map(List<A1> inList, Function1<A1,B> func) {
    ArrayList<B> outList = new ArrayList<B>();
    for(A1 obj : inList) {
      outList.add(func.call(obj));
    }
    return outList;
  }

  public static <A> void foreach(ArrayList<A> inList, Foreach1<A> func) {
    for(A obj : inList) {
      func.call(obj);
    }
  }

  public static <A> ArrayList<A> filter(ArrayList<A> inList, 
                                        Function1<A, Boolean> test) {
    ArrayList<A> outList = new ArrayList<A>();
    for(A obj : inList) {
      if(test.call(obj)) {
        outList.add(obj);
      }
    }
    return outList;
  }

}