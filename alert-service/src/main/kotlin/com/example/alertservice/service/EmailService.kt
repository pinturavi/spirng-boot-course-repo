package com.example.alertservice.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(val mailSender: JavaMailSender) {

    fun sendEmail(mailMap : Map<String, String>) {
        val logger = LoggerFactory.getLogger(this.javaClass)
        val simpleMailMessage = SimpleMailMessage()
        simpleMailMessage.setFrom("pintu541796@gmail.com")
        simpleMailMessage.setTo("ravi.s18@hotmail.com")
        simpleMailMessage.setSubject("Test Mail")
        val text = mailMap["text"]
        logger.info("email service is called.. with the text: ${text}")
        text?.also {  simpleMailMessage.setText(it)}
        //mailSender.send(simpleMailMessage)
    }
}