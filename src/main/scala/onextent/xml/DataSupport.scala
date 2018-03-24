package onextent.xml

import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.xml.DomDriver

object DataSupport {

  //
  // XML support
  //
  implicit val stream: XStream = XStreamConversions(
    new XStream(new DomDriver()))
  stream.alias("cheese", classOf[Cheese])
  stream.alias("topping", classOf[Topping])
  stream.alias("pizza", classOf[Pizza])
}
