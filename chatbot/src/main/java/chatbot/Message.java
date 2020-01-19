package chatbot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by ThanhD
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Message {

    @JsonProperty("account")
    public Account account;

    @JsonProperty("body")
    public String body;
}
