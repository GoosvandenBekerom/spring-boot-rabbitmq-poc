package com.goosvandenbekerom.springbootrabbitmqpoc

import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch

@Component
class Receiver {
    val latch: CountDownLatch = CountDownLatch(1)

    fun receiveMessage(msg: String) {
        Logger.log(javaClass,"Received message: $msg")
        latch.countDown()
    }
}