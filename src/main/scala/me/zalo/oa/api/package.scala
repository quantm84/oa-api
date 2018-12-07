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

  /**
   * Get follower profile.
   * [[https://developers.zalo.me/docs/api/official-account-api/api/lay-thong-tin-post-2570]]
   *
   * @param userId      user id for oa, or phone number of follower
   * @param accessToken access token
   * @return (response code, response body)
   */
  def getProfile(userId: String, accessToken: String): (Int, String) =
    get("getprofile", accessToken, Json.obj(
      "user_id" -> Json.fromString(userId)
    ))

  /**
   * Get followers.
   * [[https://developers.zalo.me/docs/api/official-account-api/api/lay-thong-tin-post-2570]]
   *
   * @param offset      offset
   * @param count       count
   * @param accessToken access token
   * @return
   */
  def getFollowers(offset: Int, count: Int, accessToken: String): (Int, String) =
    get("getfollowers", accessToken, Json.obj(
      "offset" -> Json.fromInt(offset),
      "count" -> Json.fromInt(count)
    ))

  /**
   * Get recent chats.
   * [[https://developers.zalo.me/docs/api/official-account-api/api/lay-thong-tin-post-2570]]
   *
   * @param offset      offset
   * @param count       count
   * @param accessToken access token
   * @return
   */
  def getRecentChats(offset: Int, count: Int, accessToken: String): (Int, String) =
    get("listrecentchat", accessToken, Json.obj(
      "offset" -> Json.fromInt(offset),
      "count" -> Json.fromInt(count)
    ))

  /**
   * Get conversation between user and oa.
   * [[https://developers.zalo.me/docs/api/official-account-api/api/lay-thong-tin-post-2570]]
   *
   * @param userId      user id for oa
   * @param offset      offset
   * @param count       count
   * @param accessToken access token
   * @return
   */
  def getConversation(userId: Long, offset: Int, count: Int, accessToken: String): (Int, String) =
    get("conversation", accessToken, Json.obj(
      "user_id" -> Json.fromLong(userId),
      "offset" -> Json.fromInt(offset),
      "count" -> Json.fromInt(count)
    ))

  private def get(endpoint: String, accessToken: String, data: Json): (Int, String) = {
    val response = Http(s"$urlPrefix/$endpoint")
      .header("Content-Type", "application/json")
      .params(
        "access_token" -> accessToken,
        "data" -> data.noSpaces
      )
      .asString
    (response.code, response.body)
  }

}
