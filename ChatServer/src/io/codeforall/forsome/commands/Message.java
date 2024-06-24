package io.codeforall.forsome.commands;

import io.codeforall.forsome.Server;
import io.codeforall.forsome.Worker;

public class Message implements Command{

    private Server server;

    public Message(Server server) {
        this.server = server;
    }

    @Override
    public void init(Worker worker, String message) {
        server.broadcast(worker, worker.getName() + ": " +message);
    }

}
