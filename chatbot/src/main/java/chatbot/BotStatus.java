package chatbot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by ThanhD
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class BotStatus {

    @JsonProperty("mention_num")
    public int mentionRoomNum;
}