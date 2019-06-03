package shop.commands;

import shop.controller.CommanderConsole;
import shop.model.Bucket;
import shop.model.Stock;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class AddToBucketCommand implements Command {
    private Bucket bucket;
    private Stock stock;
    private CommanderConsole commanderConsole;

    public AddToBucketCommand(Bucket bucket, Stock stock, CommanderConsole commanderConsole) {
        this.bucket = bucket;
        this.stock = stock;
        this.commanderConsole = commanderConsole;
    }

    /**
     * Executes command
     */
    @Override
    public void execute() {
        bucket.addToBucket(stock, commanderConsole.getId());
    }
}
