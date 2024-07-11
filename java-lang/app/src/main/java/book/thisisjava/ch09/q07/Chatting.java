package book.thisisjava.ch09.q07;

public class Chatting {
  class Chat {
    void start() {}
    void sendMessage(String message) {}
  }
  
  void startChat(String chatId) {
    String nickname = null;
    nickname = chatId;
    
    Chat chat = new Chat() {
      @Override
      public void start() {
        while(true) {
          String inputData = "안녕하세요.";
          String message = "[" + nickname + "] " + inputData;
          sendMessage(message);
        }
      }
    };
  }
}
