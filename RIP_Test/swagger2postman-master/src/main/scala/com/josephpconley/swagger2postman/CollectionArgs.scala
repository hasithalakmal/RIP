package com.josephpconley.swagger2postman

import com.josephpconley.swagger2postman.utils.ConversionUtils
import play.api.libs.json.Json

import ConversionUtils._

case class CollectionArgs(host: String, name: String, collectionName: String, headers: Map[String, String] = Map.empty){
  val docUrl = host + "/api-docs"
  val owner = "REST In Peace"
  val collectionId = genUUID
  val collectionDescription = collectionName
}

trait CollectionFormats {
  implicit val collectionFmt = Json.format[CollectionArgs]
}
