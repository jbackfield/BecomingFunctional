
class TestClass {

  def all = [1,2,3,4,5,6]
  @Lazy def odd = all.findAll { num -> num%2 == 1 }

}

def tc = new TestClass()

println(tc.odd)

tc.all = [1,2,3]

println(tc.odd)
