package com.josephpconley.swagger2postman.app.v2

import java.io.{Writer, _}
import java.net.URL

import com.josephpconley.swagger2postman.CollectionArgs
import com.josephpconley.swagger2postman.models.swagger._
import com.stackmob.newman._
import com.stackmob.newman.dsl._
import play.api.libs.json._

import scala.concurrent._
import scala.concurrent.duration._
import scala.io.Source
import scala.language.postfixOps
import scala.util.Try

object Swagger2PostmanApp
  extends v2.Swagger2Postman
  with App {
  implicit val httpClient = new ApacheHttpClient

  if(args.length < 2){
    throw new IllegalArgumentException("Invalid number of arguments, must be <filename> <collectionName> [<key=value> ... ]")
  }

  val headerMap = args.drop(2) map { kv =>
    val h = kv.split("=")
    h.head -> h.last
  } toMap

  val cArgs = CollectionArgs(host = "host", name = args(1), collectionName = args(2), headers = headerMap)

  val fileJson = Source.fromFile(args(0)).getLines.mkString
  Json.fromJson[v2.SwaggerDoc](Json.parse(fileJson)).fold(
    invalid => {
      println("Error converting Swagger v2 doc to Postman json")
    },
    swaggerDoc => {
      val postmanJson = toPostman(swaggerDoc, cArgs)

      val output: File = new File(args(1)+"postman.json")

      if (output.getParent != null && !new File(output.getParent).exists) {
        val parent: File = new File(output.getParent)
        parent.mkdirs
      }
      val out: Writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "UTF-8"))
      out.write(Json.prettyPrint(postmanJson))
      out.close
    }
  )
}
