
package com.tintim.webhook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tintim.webhook.dto.MessageEventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Slf4j
@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final SqsClient sqsClient;
    private final String queueUrl;
    private final ObjectMapper mapper = new ObjectMapper();

    public WebhookController(SqsClient sqsClient, @Value("${AWS_SQS_QUEUE_URL}") String queueUrl) {
        this.sqsClient = sqsClient;
        this.queueUrl = queueUrl;
    }

    @PostMapping("/messages")
    public ResponseEntity<String> receive(@RequestBody MessageEventDto messsage) {
        try {
            String body = mapper.writeValueAsString(messsage);
            log.info("Body: {}", body);
            SendMessageRequest req = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(body)
                    .build();
            sqsClient.sendMessage(req);
            return ResponseEntity.ok("received");
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.status(500).body("error");
        }
    }
}
