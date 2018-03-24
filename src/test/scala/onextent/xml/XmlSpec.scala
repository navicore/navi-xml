package onextent.xml

import com.thoughtworks.xstream._
import com.thoughtworks.xstream.io.xml.DomDriver
import org.scalatest._

class XmlSpec extends FlatSpec {

  "A Pizza" should "have toppings" in {

    val p = Pizza(14, "Thin", Array(Cheese("blue"), Cheese("cheddar")))
    p.addTopping(Topping("cheese"))
    p.addTopping(Topping("sausage"))
    p.addTopping(Topping("mushroom"))

    //val stream = new XStream(new DomDriver)
    val stream = XStreamConversions(new XStream(new DomDriver()))
    stream.alias("cheese", classOf[Cheese])
    stream.alias("topping", classOf[Topping])
    stream.alias("pizza", classOf[Pizza])

    val xml = stream.toXML(p)
    println(xml)

    val resurectedPizza: Pizza = stream.fromXML(xml).asInstanceOf[Pizza]
    println(resurectedPizza)
    println(resurectedPizza.toppings)
    assert(resurectedPizza.toppings.length == 3, "where's my stuff?!")
    assert(resurectedPizza.cheeses.length == 2, "who moved it?!")

  }

}
