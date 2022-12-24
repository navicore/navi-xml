Not sure if I ever used this for real work - just looking at it in 2022 because
a bot gave me a PR.

Lots of overhead making this work - I can't remember if I used this
approach - seems insane to create so much support in  the DataSupport file.

But it works and the actual code working with the data is clean and
readable.


```scala

import io.circe.parser._
import io.circe.syntax._
import onextent.xml.DataSupport._
import org.scalatest.flatspec.AnyFlatSpec

class XmlSpec extends AnyFlatSpec {

  "A Pizza" should "have toppings" in {

    val p = Pizza(14, "Thin", Array(Cheese("blue"), Cheese("cheddar")))
    p.addTopping(Topping("cheese"))
    p.addTopping(Topping("sausage"))
    p.addTopping(Topping("mushroom"))

    val xml = stream.toXML(p)
    println(xml)

    val resurectedPizza: Pizza = stream.fromXML(xml).asInstanceOf[Pizza]
    println(resurectedPizza)
    println(resurectedPizza.toppings)
    assert(resurectedPizza.toppings.length == 3, "where's my stuff?!")
    assert(resurectedPizza.cheeses.length == 2, "who moved it?!")

  }

```
