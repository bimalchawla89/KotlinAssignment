package com.suncorpassignment.network

import android.support.test.runner.AndroidJUnit4
import com.suncorpassignment.testing.DependencyProvider
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Tests for [ApiCallInterface]
 */
@RunWith(AndroidJUnit4::class)
class ApiCallInterfaceTest {

    private lateinit var apiCallInterfaceService: ApiCallInterface
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun init() {
        mockWebServer = MockWebServer()
        apiCallInterfaceService = DependencyProvider
                .getRetrofit(mockWebServer.url("/"))
                .create(ApiCallInterface::class.java)

    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getTransactions() {
        queueResponse {
            setResponseCode(200)
            setBody(DependencyProvider.getResponseFromJson("transactions"))
        }

        apiCallInterfaceService
                .getTransactions()
                .test()
                .run {
                    assertNoErrors()
                    assertValueCount(1)
                    Assert.assertEquals(values()[0].size, 5)
                    Assert.assertEquals(values()[0][0].description, "Opal recharge")
                    Assert.assertEquals(values()[0][0].id, 1)
                }
    }


    private fun queueResponse(block: MockResponse.() -> Unit) {
        mockWebServer.enqueue(MockResponse().apply(block))
    }
}