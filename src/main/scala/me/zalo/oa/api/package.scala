package me.zalo.oa

import io.circe._
import io.circe.syntax._
import me.zalo.oa.api.message.Message
import scalaj.http.Http

package object api {

  private val printer = Printer(preserveOrder = true, dropNullValues = true, indent = "")

  def sendTextMessage(uid: String, msg: String, accessToken: String): (Int, String) =
    sendMessage(uid, Message(msg, None), accessToken)

  def sendMessage(uid: String, msg: Message, accessToken: String): (Int, String) = {
    val data = Json.obj(
      "recipient" -> Json.obj(
        "user_id" -> Json.fromString(uid)
      ),
      "message" -> msg.asJson
    )
    val response = Http(s"https://openapi.zalo.me/v2.0/oa/message?access_token=$accessToken")
      .header("Content-Type", "application/json")
      .postData(printer.pretty(data))
      .asString
    (response.code, response.body)
  }

}
