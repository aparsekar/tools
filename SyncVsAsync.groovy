import groovyx.gpars.GParsPool
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
@Grapes([@Grab(group = 'org.springframework', module = 'spring-web', version = '4.1.1.RELEASE'),
        @Grab(group = 'org.apache.httpcomponents', module = 'httpasyncclient', version = '4.1.3')])

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.AsyncRestTemplate
import org.springframework.web.client.RestTemplate

GParsPool.withPool {
    PoolingNHttpClientConnectionManager connectionNManager = new PoolingNHttpClientConnectionManager(new DefaultConnectingIOReactor())
    CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClientBuilder.create()
            .setConnectionManager(connectionNManager)
            .build()
    HttpComponentsAsyncClientHttpRequestFactory asyncRequestFactory = new HttpComponentsAsyncClientHttpRequestFactory(httpAsyncClient)
    AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate(asyncRequestFactory)

    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager()
    CloseableHttpClient httpClient = HttpClientBuilder.create()
            .setConnectionManager(connectionManager)
            .build()
    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient)
    RestTemplate httpRestTemplate = new RestTemplate(requestFactory)

    pause()

    List statusCodesAsync = ((1..1000).collect {
        asyncRestTemplate.getForEntity('http://tencents.info/', String)
    })*.get().statusCode

    pause(statusCodesAsync.toString())

    List statusCodes = ((1..1000).collectParallel {
        httpRestTemplate.getForEntity('http://tencents.info/', String)
    })*.statusCode

    pause(statusCodes.toString())
    System.exit(-1)
}

void pause(String msg = '') { if (msg) print(msg); Thread.sleep(10_000) }