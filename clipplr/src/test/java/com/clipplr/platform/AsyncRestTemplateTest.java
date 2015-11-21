package com.clipplr.platform;

import com.clipplr.platform.common.core.UnitTestHelper;
import com.google.common.base.Throwables;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

/**
 * Created by simon on 8/14/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AsyncRestTemplateTest extends UnitTestHelper {

    private static final Logger logger = LoggerFactory.getLogger(AsyncRestTemplateTest.class);

    @Ignore
    @Test
    public void asyncRestfulTest() {

        int timeout = 1000;

        HttpComponentsAsyncClientHttpRequestFactory factory = new HttpComponentsAsyncClientHttpRequestFactory(asyncHttpClient(timeout));
        AsyncRestTemplate restTemplate = new AsyncRestTemplate(factory);

        StopWatch sw = new StopWatch();
        sw.start();

        ListenableFuture<ResponseEntity<String>> future = null;

        try {
            Class<String> responseType = String.class;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<String>("params", headers);

            // 10.255.255.1 is an unroutable address (to yield a connection timeout)
            future = restTemplate.exchange("http://10.255.255.1:8080", HttpMethod.GET, requestEntity, responseType);

            ResponseEntity<String> entity = future.get();
            logger.debug("body:::::" + entity.getBody());

        } catch (ExecutionException ex) {
            Throwable rootEx = ex.getCause();            assertThat(rootEx,
                    anyOf(instanceOf(SocketTimeoutException.class),
                            instanceOf(ConnectTimeoutException.class),
                            instanceOf(ConnectException.class))
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sw.stop();
        }

        int elapsedTime = (int) sw.getTotalTimeMillis();
        logger.debug("elapsedTime:::" + elapsedTime);
        assertTrue(String.format("elapsedTime should be about %d ms (was %d ms)", timeout, elapsedTime),
                timeout <= elapsedTime && elapsedTime <= timeout + 200);
    }

    private CloseableHttpAsyncClient asyncHttpClient(int timeout) {
        try {
            PoolingNHttpClientConnectionManager connectionManager = new PoolingNHttpClientConnectionManager(
                    new DefaultConnectingIOReactor(IOReactorConfig.DEFAULT));

            connectionManager.setMaxTotal(100);
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(timeout)
                    .build();

            CloseableHttpAsyncClient httpclient = HttpAsyncClientBuilder
                    .create().setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(config).build();

            return httpclient;
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

}
