package task_6.shop.exception;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class ProductTransactionException extends Exception {
    private static final long serialVersionUID = -8104006710790391545L;

    public ProductTransactionException(String message) {
        super(message);
    }
}
