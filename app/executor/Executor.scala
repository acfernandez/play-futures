package executor

import scala.concurrent.Future
import connectors.Service
import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.JsObject
import play.api.libs.json.Json
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import scala.util.Failure
import scala.util.Success
import scala.collection.mutable.MutableList
import connectors.ErroneusConnector
import connectors.FastConnector
import connectors.SlowConnector


object Executor {
  
  val executors = Map(
      "erroneus" -> Seq(ErroneusConnector),
      "fast" -> Seq(FastConnector),
      "slow" -> Seq(SlowConnector),
      "all" -> Seq(ErroneusConnector, FastConnector, SlowConnector) )
      
  private def buildResponse(futureResults: Seq[Future[JsObject]]): Future[String] = 
    for { list <- Future.sequence(futureResults) } yield Json.stringify(Json.obj("responses" -> list))
      
  def execute(kind: String): Future[String] = {
    
    Logger.info(s"Execute future calls of type: [$kind]")    
    
    val executorResults = executors.getOrElse(kind, Seq.empty) map { 
       executor =>  executor.asInstanceOf[Service[JsObject]].execute()
    }
    
    buildResponse(executorResults)        
  }
  
}