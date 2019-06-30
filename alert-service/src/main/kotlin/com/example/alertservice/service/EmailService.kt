package com.example.alertservice.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(val mailSender: JavaMailSender) {

    fun sendEmail(mailMap : Map<String, String>) {
        var simpleMailMessage: SimpleMailMessage = SimpleMailMessage()
        simpleMailMessage.setFrom("pintu541796@gmail.com")
        simpleMailMessage.setTo("pintu541796@gmail.com")
        simpleMailMessage.setSubject("Test Mail")
        val text = mailMap["text"]
        text?.also {  simpleMailMessage.setText(it)}
        mailSender.send(simpleMailMessage)
    }
}