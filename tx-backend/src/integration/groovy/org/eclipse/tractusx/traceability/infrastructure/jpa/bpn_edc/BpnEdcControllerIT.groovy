/********************************************************************************
 * Copyright (c) 2022, 2023 Bayerische Motoren Werke Aktiengesellschaft (BMW AG)
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ********************************************************************************/

package org.eclipse.tractusx.traceability.infrastructure.jpa.bpn_edc


import io.restassured.http.ContentType
import org.eclipse.tractusx.traceability.IntegrationSpecification
import org.eclipse.tractusx.traceability.common.support.BpnRepositoryProvider
import org.hamcrest.Matchers

import static io.restassured.RestAssured.given
import static org.eclipse.tractusx.traceability.common.security.JwtRole.ADMIN
import static org.eclipse.tractusx.traceability.common.security.JwtRole.USER

class BpnEdcControllerIT extends IntegrationSpecification implements BpnRepositoryProvider {

    def "should create two BPN EDC URL mappings"() {
        when:
            given()
                .contentType(ContentType.JSON)
                .body(
                        asJson(
                                [
                                        bpn    : "BPNL00000003CSGV",
                                        url   : "http://localhost:12345/abc"
                                ]
                        )
                )
                .header(jwtAuthorization(ADMIN))
                .when()
                    .post("/api/bpn-config")
                .then()
                    .statusCode(204)
        then:
            given()
                .header(jwtAuthorization(ADMIN))
                .contentType(ContentType.JSON)
                .when()
                    .get("/api/bpn-config")
                .then()
                    .statusCode(200)
                    .body("content", Matchers.hasSize(1))
                    .body("content[0].bpn", Matchers.equalTo("BPNL00000003CSGV"))
                    .body("content[0].url", Matchers.equalTo("http://localhost:12345/abc"))
    }

    def "should delete one BPN EDC URL mapping"() {
        when:
        given()
                .contentType(ContentType.JSON)
                .body(
                        asJson(
                                [
                                        bpn    : "BPNL00000003CSGV",
                                        url   : "http://localhost:12345/abc"
                                ]
                        )
                )
                .header(jwtAuthorization(ADMIN))
                .when()
                .post("/api/bpn-config")
                .then()
                .statusCode(204)
        then:
        given()
                .contentType(ContentType.JSON)
                .header(jwtAuthorization(ADMIN))
                .when()
                .delete("/api/bpn-config/BPNL00000003CSGV")
                .then()
                .statusCode(204)
        expect:
        given()
                .header(jwtAuthorization(ADMIN))
                .contentType(ContentType.JSON)
                .when()
                .get("/api/bpn-config")
                .then()
                .statusCode(200)
                .body("content", Matchers.hasSize(0))
    }

    def "should report a bad request"() {
        expect:
        given()
                .contentType(ContentType.JSON)
                .body(
                        asJson(
                                {}
                        )
                )
                .header(jwtAuthorization(ADMIN))
                .when()
                .post("/api/bpn-config")
                .then()
                .statusCode(500)
    }

    def "should report an unauthorized request"() {
        expect:
        given()
                .contentType(ContentType.JSON)
                .body(
                        asJson(
                                [
                                        bpn    : "BPNL00000003CSGV",
                                        url   : "http://localhost:12345/abc"
                                ]
                        )
                )
                .header(jwtAuthorization(USER))
                .when()
                .post("/api/bpn-config")
                .then()
                .statusCode(403)
    }

}
