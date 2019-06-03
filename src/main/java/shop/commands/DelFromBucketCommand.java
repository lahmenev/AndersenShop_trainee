package shop.commands;

import shop.controller.CommanderConsole;
import shop.model.Bucket;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class DelFromBucketCommand implements Command {
    private Bucket bucket;
    private CommanderConsole commanderConsole;

    public DelFromBucketCommand(Bucket bucket, CommanderConsole commanderConsole) {
        this.bucket = bucket;
        this.commanderConsole = commanderConsole;
    }

    /**
     * Executes command
     */
    @Override
    public void execute() {
        bucket.delFromBucket(commanderConsole.getId());
    }
}
