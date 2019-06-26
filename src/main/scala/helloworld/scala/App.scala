package helloworld.scala

import java.io.{BufferedReader, IOException, InputStreamReader}
import java.net.URL
import java.util
import java.util.stream.Collectors

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

/**
  * Handler for requests to Lambda function.
  */
class App extends RequestHandler[APIGatewayProxyRequestEvent, GatewayResponse] {
  override def handleRequest(input: APIGatewayProxyRequestEvent, context: Context): GatewayResponse = {
    val headers = new util.HashMap[String, String]
    headers.put("Content-Type", "application/json")
    headers.put("X-Custom-Header", "application/json")
    try {
      val pageContents = this.getPageContents("https://checkip.amazonaws.com")
      val output = s"""{ "message": "hello world", "location": "$pageContents", "input": "$input" }"""
      GatewayResponse(output, headers, 200)
    } catch {
      case _: IOException =>
        GatewayResponse("{}", headers, 500)
    }
  }

  private def getPageContents(address: String) = {
    val url = new URL(address)
    val br = new BufferedReader(new InputStreamReader(url.openStream))

    try {
      br.lines.collect(Collectors.joining(System.lineSeparator))
    } finally {
      if (br != null) br.close()
    }
  }
}
