
package com.tintim.webhook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageEventDto {

    @JsonProperty(value = "message_id")
    private String messageId;
    @JsonProperty(value = "conversation_id")
    private String conversationId;
    @JsonProperty(value = "messaging_product")
    private String messagingProduct;
    private String to;
    private String type;
    private String text;
    private long timestamp;

}
