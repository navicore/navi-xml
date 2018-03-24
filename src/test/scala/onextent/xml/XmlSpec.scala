package onextent.xml

import io.circe.{Decoder, Encoder}
import io.circe.generic.auto._
import io.circe.syntax._
import onextent.xml.DataSupport._
import org.scalatest._
import io.circe.parser._
import io.circe.generic.semiauto._
import io.circe._

class XmlSpec extends FlatSpec {

  implicit val pizzaDecoder: Decoder[Pizza] = deriveDecoder
  implicit val pizzaEncoder: Encoder[Pizza] = deriveEncoder
  implicit val chDecoder: Decoder[Cheese] = deriveDecoder
  implicit val chEncoder: Encoder[Cheese] = deriveEncoder

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

    val p: Pizza = Pizza(14, "Thin", Array(Cheese("blue"), Cheese("cheddar")))
    p.addTopping(Topping("cheese"))
    p.addTopping(Topping("sausage"))
    p.addTopping(Topping("mushroom"))

    val jsonstr = p.asJson.spaces2
    println(jsonstr)

    parse(jsonstr) match {
      case Right(json) =>
        json.as[Pizza].toOption match {
          case Some(newest) => {
            println(newest)
            println(newest.asJson.spaces4)
          }
        }
    }

  }

}
