# 📞 TCP Chat

**TCP Chat** is a terminal-based chat application written in Java. It operates with a server-client architecture where the server listens for incoming connections and assigns each connection to a worker thread. All connected clients can broadcast messages to each other in real-time.

### Key Features
- **Multithreaded Server**: Efficiently manages multiple client connections using worker threads.
- **Broadcast Messaging**: Allows all connected clients to send and receive messages to and from every other connected client.
- **TCP**: Connections made using TCP protocol (JAVA Socket and ServerSocket).

## 🛠️ Technologies Used

- **Java**: Core programming language for building the project.
- **Netcat**: Utility used to connect clients to the server.

## 🏃‍♂️ How to Run

Follow these steps to run the TPC Chat application:

1. **Clone the repository:**

   ```bash
   git clone https://github.com/TaBByR/TCPChat.git
   cd TPC-Chat
   ```
2. **Run the main class in your IDE**

3. **Connect clients to the server:**

   In a separate terminal, use `netcat` to connect to the server. Replace `<server_ip>` with your server's IP address and `<port>` with the port number used by the server.

   ```bash
   nc <server_ip> <port>
   ```

4. **Start chatting:**

   Once connected, type your messages into the terminal, and they will be broadcast to all connected clients.
