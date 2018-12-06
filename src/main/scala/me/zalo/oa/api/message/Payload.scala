package me.zalo.oa.api.message

import io.circe._
import io.circe.generic.extras.semiauto._
import io.circe.syntax._

trait Payload

object Payload extends SnakeCaseConfig {
  implicit val payloadEncoder: Encoder[Payload] = Encoder.instance {
    case b@MediaPayload(_, _) => b.asJson
    case c@ListPayload(_, _) => c.asJson
  }
}

case class MediaPayload private(templateType: String, elements: Array[MediaElement]) extends Payload

object MediaPayload extends SnakeCaseConfig {
  implicit val mediaPayloadEncoder: Encoder[MediaPayload] = deriveEncoder

  def apply(elements: Array[MediaElement]): MediaPayload = new MediaPayload("media", elements)
}

case class ListPayload private(templateType: String, elements: Array[ListElement]) extends Payload

object ListPayload extends SnakeCaseConfig {
  implicit val listPayloadEncoder: Encoder[ListPayload] = deriveEncoder

  def apply(elements: Array[ListElement]): ListPayload = new ListPayload("list", elements)
}
