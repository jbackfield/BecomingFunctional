
class TestClass {

  def all = [1,2,3,4,5,6]
  def odd = all.findAll { num -> println("Foo"); num%2 == 1; }

}

def tc = new TestClass()

println("Bar")

println(tc.odd)
