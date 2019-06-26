package helloworld.scala

import java.util
import scala.beans.BeanProperty

case class GatewayResponse(
                            @BeanProperty var body: String,
                            @BeanProperty var headers: util.Map[String, String],
                            @BeanProperty var statusCode: Int
                          )
