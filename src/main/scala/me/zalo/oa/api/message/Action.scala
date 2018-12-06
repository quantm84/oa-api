package me.zalo.oa.api.message

import io.circe._
import io.circe.generic.extras.semiauto._
import io.circe.syntax._

trait Action

object Action extends SnakeCaseConfig {
  implicit val actionEncoder: Encoder[Action] = Encoder.instance {
    case b@OpenUrlAction(_, _) => b.asJson
    case c@QueryShowAction(_, _) => c.asJson
    case d@QueryHideAction(_, _) => d.asJson
    case e@OpenSmsAction(_, _) => e.asJson
    case f@OpenPhoneAction(_, _) => f.asJson
  }
}

case class OpenUrlAction private(`type`: String, url: String) extends Action

object OpenUrlAction extends SnakeCaseConfig {
  implicit val openUrlActionEncoder: Encoder[OpenUrlAction] = deriveEncoder

  def apply(url: String): OpenUrlAction = new OpenUrlAction("oa.open.url", url)
}

case class QueryShowAction private(`type`: String, payload: String) extends Action

object QueryShowAction extends SnakeCaseConfig {
  implicit val queryShowActionEncoder: Encoder[QueryShowAction] = deriveEncoder

  def apply(payload: String): QueryShowAction = new QueryShowAction("oa.query.show", payload)
}

case class QueryHideAction private(`type`: String, payload: String) extends Action

object QueryHideAction extends SnakeCaseConfig {
  implicit val queryHideActionEncoder: Encoder[QueryHideAction] = deriveEncoder

  def apply(payload: String): QueryHideAction = new QueryHideAction("oa.query.hide", payload)
}

case class OpenSmsActionPayload(content: String, phoneCode: String)

object OpenSmsActionPayload extends SnakeCaseConfig {
  implicit val openSmsActionPayloadEncoder: Encoder[OpenSmsActionPayload] = deriveEncoder
}

case class OpenSmsAction private(`type`: String, payload: OpenSmsActionPayload) extends Action

object OpenSmsAction extends SnakeCaseConfig {
  implicit val openSmsActionEncoder: Encoder[OpenSmsAction] = deriveEncoder

  def apply(payload: OpenSmsActionPayload): OpenSmsAction = new OpenSmsAction("oa.open.sms", payload)
}

case class OpenPhoneActionPayload(phoneCode: String)

object OpenPhoneActionPayload extends SnakeCaseConfig {
  implicit val openPhoneActionPayloadEncoder: Encoder[OpenPhoneActionPayload] = deriveEncoder
}

case class OpenPhoneAction private(`type`: String, payload: OpenPhoneActionPayload) extends Action

object OpenPhoneAction extends SnakeCaseConfig {
  implicit val openPhoneActionEncoder: Encoder[OpenPhoneAction] = deriveEncoder

  def apply(payload: OpenPhoneActionPayload): OpenPhoneAction = new OpenPhoneAction("oa.open.phone", payload)
}
