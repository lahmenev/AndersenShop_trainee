package shop.commands;

import shop.model.Bucket;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class ClearBucketCommand implements Command {
    private Bucket bucket;

    public ClearBucketCommand(Bucket bucket) {
        this.bucket = bucket;
    }

    /**
     * Executes command
     */
    @Override
    public void execute() {
        bucket.clearBucket();
    }
}
