package chatbot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ThanhD
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Account {

    @JsonProperty("account_id")
    public String accountId;

    @JsonProperty("name")
    public String name;
}