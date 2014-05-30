
public class Temp {

  public static void test(Option<String> foo) {
    System.out.println(foo.getOrElse("die"));
  }

  public static void main(String[] args) {
    test(new None<String>()); 
  }

}
