package io.codeforall.forsome;

import io.codeforall.forsome.commands.Command;
import io.codeforall.forsome.commands.List;
import io.codeforall.forsome.commands.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket serverSocket;
    private static final int DEFAULT_PORT = 8085;
    private HashMap<String, Command> strategyMap;

    private ConcurrentLinkedQueue<Worker> workerList;

    private ExecutorService executor;


    public Server() {
        workerList = new ConcurrentLinkedQueue<>();
        executor = Executors.newCachedThreadPool();
        strategyMap = buildMap();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    private void start() {
        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);
            listen();
        } catch (IOException e) {
            System.out.println("Falha a inicializar servidor");
        }
    }

    private void listen() {
        while (true) {
            try {
                System.out.println("À espera de clientes");
                Socket clientSocket = serverSocket.accept();

                Worker worker = new Worker("Client " + (workerList.size() + 1), clientSocket, this, strategyMap);
                workerList.add(worker);
                executor.execute(worker);

                System.out.println(worker.getName() + " entrou");

            } catch (IOException e) {
                System.out.println("Oops");
            }
        }
    }

    public void broadcast(Worker sender, String message) {
        for (Worker w : workerList) {
            if (w.equals(sender)) {
                continue;
            }

            w.send(message);
        }
    }

    public String listClients() {
        StringBuilder sb = new StringBuilder();

        for (Worker w : workerList) {
            sb.append(w.getName());
            sb.append('\n');
        }

        return sb.toString();
    }

    public HashMap<String, Command> buildMap() {

        HashMap<String, Command> strategyMap = new HashMap<>();

        strategyMap.put("/list", new List(this));
        strategyMap.put("", new Message(this));

        return strategyMap;

    }
}
