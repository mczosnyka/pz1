package pz1.michalczosnyka;

public abstract class Command {
    String commandName;
    Hotel hotel;
    abstract void Execute();
}
