package book.thisisjava.ch19.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class SocketClient {
  ChatServer chatServer;
  Socket socket;
  DataInputStream dis;
  DataOutputStream dos;
  String clientIp;
  String chatName;
}
