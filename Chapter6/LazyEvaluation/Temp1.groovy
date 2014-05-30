
class TestClass {

  def all = [1,2,3,4,5,6]
  def odd = all.findAll { num -> num%2 == 1 }

}

println(new TestClass().odd)
