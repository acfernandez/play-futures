package controllers

import scala.concurrent.Future


import executor.Executor
import play.api.Logger
import play.api.data.Form
import play.api.data.Forms.boolean
import play.api.data.Forms.mapping
import play.api.data.Forms.nonEmptyText
import play.api.data.Forms.optional
import play.api.data.Forms.text
import play.api.i18n.Lang
import play.api.libs.Comet
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.iteratee.Enumerator
import play.api.libs.json.Json
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import play.api.mvc.Action
import play.api.mvc.Controller



object Application extends Controller {
   
  def execute(kind: String) = Action.async {
    Executor.execute(kind: String).map( response => Ok(response) )
  } 
  
}