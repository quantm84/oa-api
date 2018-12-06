package me.zalo.oa.api.message

import io.circe._
import io.circe.generic.extras.semiauto._

case class Message(text: String, attachment: Option[Attachment])

object Message extends SnakeCaseConfig {
  implicit val messageEncoder: Encoder[Message] = deriveEncoder
}
