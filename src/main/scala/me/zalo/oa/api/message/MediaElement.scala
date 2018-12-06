package me.zalo.oa.api.message

import io.circe._
import io.circe.generic.extras.semiauto._
import io.circe.syntax._

trait MediaElement

object MediaElement extends SnakeCaseConfig {
  implicit val mediaElementEncoder: Encoder[MediaElement] = Encoder.instance {
    case b@ImageUrlElement(_, _) => b.asJson
    case c@ImageAttachmentElement(_, _) => c.asJson
  }
}

case class ImageUrlElement private(mediaType: String, url: String) extends MediaElement

object ImageUrlElement extends SnakeCaseConfig {
  implicit val imageUrlElementEncoder: Encoder[ImageUrlElement] = deriveEncoder

  def apply(url: String): ImageUrlElement = new ImageUrlElement("image", url)
}

case class ImageAttachmentElement private(mediaType: String, attachmentId: String) extends MediaElement

object ImageAttachmentElement extends SnakeCaseConfig {
  implicit val imageAttachmentElementEncoder: Encoder[ImageAttachmentElement] = deriveEncoder

  def apply(attachmentId: String): ImageAttachmentElement = new ImageAttachmentElement("image", attachmentId)
}
