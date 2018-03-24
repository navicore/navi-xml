package onextent.xml

import io.circe.generic.auto._
import io.circe.syntax._
import onextent.xml.DataSupport._
import org.scalatest._

class XmlSpec extends FlatSpec {

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

  "A Pizza" should "be JSON" in {

    val p = Pizza(14, "Thin", Array(Cheese("blue"), Cheese("cheddar")))
    p.addTopping(Topping("cheese"))
    p.addTopping(Topping("sausage"))
    p.addTopping(Topping("mushroom"))

    val json = p.asJson.spaces2
    println(json)

    // todo parse json
  }
}
