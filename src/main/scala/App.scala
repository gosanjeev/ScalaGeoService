package com.zoomgaga

import com.typesafe.play.mini._
import play.api.mvc._
import play.api.mvc.Results._
import play.api.mvc.BodyParsers.parse
import play.api.libs.ws.WS
import play.libs._
import scala.xml.XML

/**
 * this application is registered via Global
 */
object App extends Application { 
  def route = {
    case GET(Path("/geo")) & QueryString(qs) => Action{ request=>
    Async {
      println(request.body)
      println(play.api.Play.current)
      var result = QueryString(qs,"latlng")
      //val backendUrl = QueryString(qs,"target") map (_.get(0)) getOrElse("http://localhost:8080/api/token")
      //val tokenData = QueryString(qs,"data") map (_.get(0)) getOrElse("<auth>john</auth>")
      var backendUrl : String = "http://maps.googleapis.com/maps/api/geocode/json?latlng="
      if (result.isEmpty)
	backendUrl+="37.7749295,-122.4194155&sensor=false"
      else
        backendUrl+= result.get.get(0) + "&sensor=false"
      println(backendUrl)
      //WS.url(backendUrl).post(XML loadString backendUrl).map { response =>
      WS.url(backendUrl).get().map { response =>
      Ok(<html><h1>Posted to {backendUrl}</h1>
         <body>
           <div><p><b>Request url:</b></p>{backendUrl}</div>
           <div><p><b>Response body:</b></p>{response.body}</div>
         </body></html>).as("text/html") }
      }
    }
  }
}
