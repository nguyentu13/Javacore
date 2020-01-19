package chatbot;

import java.io.IOException;
import java.util.List;

/**
 * Created by ThanhD
 */
public class App {
    public static void main(String[] args) {
        try {
        	String roomId = "173813529"; // Replace your roomId
            ChatworkClient chatworkClient = new ChatworkClient();
            chatworkClient.startChatBot(roomId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
