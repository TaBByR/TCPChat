package io.codeforall.forsome;

import io.codeforall.forsome.commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class Worker implements Runnable {

    private String name;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Server server;
    private HashMap<String, Command> strategiesMap;

    public Worker(String name, Socket socket, Server server, HashMap<String, Command> strategiesMap) throws IOException {
        this.name = name;
        this.clientSocket = socket;
        this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
        this.server = server;
        this.strategiesMap = strategiesMap;
    }

    public String getName() {
        return name;
    }

    public void broadcast(String message) {
        server.broadcast(this, message);
    }

    public void send(String message){
        out.println(message);
    }

    @Override
    public void run() {
        try {
            String line;

            while(!clientSocket.isClosed()) {
                if((line = in.readLine()) != null) {
                    Command strat = (strategiesMap.get(line) == null) ? strategiesMap.get("") : strategiesMap.get(line.toLowerCase());
                    strat.init(this, line);
                }
            }
        } catch (IOException e) {
            System.err.println("Deu poo");
        }
    }
}
