package helloworld.scala

import java.io.{BufferedReader, IOException, InputStreamReader}
import java.net.URL
import java.util
import java.util.stream.Collectors

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

/**
  * Handler for requests to Lambda function.
  */
class App extends RequestHandler[AnyRef, AnyRef] {
  override def handleRequest(input: AnyRef, context: Context): AnyRef = {
    val headers = new util.HashMap[String, String]
    headers.put("Content-Type", "application/json")
    headers.put("X-Custom-Header", "application/json")
    try {
      val pageContents = this.getPageContents("https://checkip.amazonaws.com")
      val output = String.format("{ \"message\": \"hello world\", \"location\": \"%s\" }", pageContents)
      GatewayResponse(output, headers, 200)
    } catch {
      case _: IOException =>
        GatewayResponse("{}", headers, 500)
    }
  }

  private def getPageContents(address: String) = {
    val url = new URL(address)
    try {
      val br = new BufferedReader(new InputStreamReader(url.openStream))
      try
        br.lines.collect(Collectors.joining(System.lineSeparator))
      finally if (br != null) br.close()
    }
  }
}
