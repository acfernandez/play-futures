package connectors

import java.io.Serializable
import scala.concurrent.Future
import org.apache.commons.lang3.StringUtils
import anorm.SQL
import anorm.sqlToSimple
import anorm.toParameterValue
import play.api.Logger
import play.api.Play.current
import play.api.db.DB
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.JsObject
import play.api.libs.json.Json
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import play.api.libs.ws.Response
import play.api.libs.ws.WS
import play.api.libs.json.JsValue


trait Service[R] { def execute(): Future[R] }

object FastConnector extends Service[JsObject] {
  
  val name = "fast" 
    
  def execute(): Future[JsObject] = Future {
    Json.obj(name -> "My fast response")
  }
  
}

object ErroneusConnector extends Service[JsObject] {
  
  val name = "erroneus"
    
  def execute(): Future[JsObject] = Future {
    Json.obj(name -> "My response is an error")
  }

}

object SlowConnector extends Service[JsObject] {
  
  val name = "slow" 
     
  def execute(): Future[JsObject] = Future {
    Thread sleep 5000
    Json.obj(name -> "My slow response")
  }   
  
}