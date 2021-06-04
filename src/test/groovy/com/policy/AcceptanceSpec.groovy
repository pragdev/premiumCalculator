package com.policy

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.AutoCleanup
import spock.lang.Specification

@MicronautTest
class AcceptanceSpec extends Specification {

    @AutoCleanup
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    @AutoCleanup
    RxHttpClient client = RxHttpClient.create(embeddedServer.URL)

    def 'should calculate premium of basic coefficient'() {
        given:
        def request = [
                number : '123',
                status : 'a-status',
                objects: [
                    [
                        name      : 'an-object',
                        subObjects: [
                                [
                                        name      : 'a-fire-sub-object',
                                        sumInsured: 100,
                                        type      : 'fire'
                                ], [
                                        name      : 'a-theft-sub-object',
                                        sumInsured: 8,
                                        type      : 'theft'
                                ]
                        ]
                    ]
                ]
        ]
        when:
        def response = client.toBlocking().exchange(HttpRequest.POST("/premium", request), Map)

        then:
        response.body().value == 2.28
    }

    def 'should calculate premium of maximum coefficient'() {
        given:
        def request = [
                number : '123',
                status : 'a-status',
                objects: [
                        [
                                name      : 'an-object',
                                subObjects: [
                                        [
                                                name      : 'a-fire-sub-object',
                                                sumInsured: 500,
                                                type      : 'fire'
                                        ], [
                                                name      : 'a-theft-sub-object',
                                                sumInsured: 102.51,
                                                type      : 'theft'
                                        ]
                                ]
                        ]
                ]
        ]
        when:
        def response = client.toBlocking().exchange(HttpRequest.POST("/premium", request), Map)

        then:
        response.body().value == 17.13
    }

}
