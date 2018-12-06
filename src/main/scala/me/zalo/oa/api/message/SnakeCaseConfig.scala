package me.zalo.oa.api.message

import io.circe.generic.extras.Configuration

trait SnakeCaseConfig {
  implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames
}
