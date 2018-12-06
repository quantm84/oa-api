package me.zalo.oa.api.message

import io.circe._
import io.circe.generic.extras.semiauto._

case class ListElement(
  title: String,
  subtitle: Option[String],
  imageUrl: String,
  defaultAction: Option[Action]
)

object ListElement extends SnakeCaseConfig {
  implicit val listElementEncoder: Encoder[ListElement] = deriveEncoder
}
