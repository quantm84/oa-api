package me.zalo.oa

import io.circe._
import io.circe.syntax._
import me.zalo.oa.api.message.Message
import scalaj.http.Http

package object api {

  private val urlPrefix = "https://openapi.zalo.me/v2.0/oa"
  private val printer = Printer(preserveOrder = true, dropNullValues = true, indent = "")

  /**
   * Send a text message to follower.
   * [[https://developers.zalo.me/docs/api/official-account-api/api/gui-tin-nhan-post-2343]]
   *
   * @param uid         user id for oa
   * @param msg         message
   * @param accessToken access token
   * @return (response code, response body)
   */
  def sendTextMessage(uid: String, msg: String, accessToken: String): (Int, String) =
    sendMessage(uid, Message(msg, None), accessToken)

  /**
   * Send a message to follower.
   * [[https://developers.zalo.me/docs/api/official-account-api/api/gui-tin-nhan-post-2343]]
   *
   * @param uid         user id for oa
   * @param msg         message
   * @param accessToken access token
   * @return
   */
  def sendMessage(uid: String, msg: Message, accessToken: String): (Int, String) = {
    val data = Json.obj(
      "recipient" -> Json.obj(
        "user_id" -> Json.fromString(uid)
      ),
      "message" -> msg.asJson
    )
    val response = Http(s"$urlPrefix/message?access_token=$accessToken")
      .header("Content-Type", "application/json")
      .postData(printer.pretty(data))
      .asString
    (response.code, response.body)
  }

}
