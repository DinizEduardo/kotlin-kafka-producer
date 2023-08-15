package br.com.kotlin.example.producer

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(kafkaConfig: KafkaConfig) {

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, String>

    private val producerJson = kafkaConfig.producerJson()

    fun sendMessage(topic: String, obj: Any) {
        val node = ObjectMapper().valueToTree<JsonNode>(obj)
        val record : ProducerRecord<String, JsonNode> = ProducerRecord(topic, node)

        producerJson.send(record) { metadata: RecordMetadata?, exception: Exception? ->
            println(exception ?: metadata)
        }
    }

}