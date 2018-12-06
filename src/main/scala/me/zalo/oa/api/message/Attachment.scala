package me.zalo.oa.api.message

import io.circe._
import io.circe.generic.extras.semiauto._

case class Attachment(`type`: String, payload: Payload)

object Attachment extends SnakeCaseConfig {
  implicit val attachmentEncoder: Encoder[Attachment] = deriveEncoder
}
