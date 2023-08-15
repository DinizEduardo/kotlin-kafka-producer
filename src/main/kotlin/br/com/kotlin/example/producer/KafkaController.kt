package br.com.kotlin.example.producer

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kafka")
class KafkaController(
        private val kafkaProducer: KafkaProducer

) {

    @GetMapping("/send")
    fun sendMessage() {
        kafkaProducer.sendMessage("nome-do-topico", "Teste da mensagem")
    }

}