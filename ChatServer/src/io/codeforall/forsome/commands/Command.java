package io.codeforall.forsome.commands;

import io.codeforall.forsome.Worker;

public interface Command {

    void init(Worker worker, String message);
}
