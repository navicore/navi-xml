package onextent.xml

import scala.collection.mutable.ArrayBuffer

case class Topping (name: String)

case class Pizza(crustSize: Int, crustType: String) {
  val toppings: ArrayBuffer[Topping] = ArrayBuffer[Topping]()
  def addTopping(t: Topping) { toppings += t }
}
