package com.goosvandenbekerom.springbootrabbitmqpoc

import org.springframework.boot.CommandLineRunner
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class Producer(val rabbitTemplate: RabbitTemplate, val receiver: Receiver) : CommandLineRunner {
    override fun run(vararg args: String?) {
        while (true) {
            Logger.log(javaClass, "Type message:")

            val msg = readLine().orEmpty()
            if (msg == "q") break

            Logger.log(javaClass, "Sending message...")
            rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, "poc.ieks", msg)
            receiver.latch.await(10000, TimeUnit.MILLISECONDS)
        }
    }
}