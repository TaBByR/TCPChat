package io.codeforall.forsome.commands;

import io.codeforall.forsome.Server;
import io.codeforall.forsome.Worker;

import java.io.PrintWriter;

public class List implements Command{

    private Server server;

    public List(Server server) {
        this.server = server;
    }

    @Override
    public void init(Worker worker, String message) {
       worker.send(server.listClients().trim());
    }
}
