/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.performance.tests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class GatewaySimulation extends Simulation {

  val httpConf = http.baseURL("https://nightly.gravitee.io/gateway").acceptHeader("application/json")

  val scn = scenario("Echo calls")
    .exec(http("call_echo").get("/gravitee.io/features"))

  setUp(scn.inject(constantUsersPerSec(50) during(10 seconds)).protocols(httpConf))
    .assertions(global.successfulRequests.percent.gte(100))

}
