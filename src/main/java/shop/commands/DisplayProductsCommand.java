package shop.commands;

import shop.model.Stock;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class DisplayProductsCommand implements Command {
    private Stock stock;

    public DisplayProductsCommand(Stock stock) {
        this.stock = stock;
    }

    /**
     * Executes command
     */
    @Override
    public void execute() {
        stock.displayProducts();
    }
}
