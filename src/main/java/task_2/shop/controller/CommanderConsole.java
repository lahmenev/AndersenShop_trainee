package task_2.shop.controller;

import task_2.shop.commands.*;
import task_2.shop.controller.proxy.CustomInvocationHandler;
import task_2.shop.model.*;
import task_2.shop.model.strategies.PaymentByRub;
import task_2.shop.model.strategies.PaymentByUsd;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Proxy;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class CommanderConsole {
    private Stock stock = new Stock();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private User user = new User("Sergey", 5000);
    private PaymentByRub rub = new PaymentByRub();
    private PaymentByUsd usd = new PaymentByUsd();
    private Serialization serialization = new Serialization();

    private InvokerCommand invokerCommandImpl = new InvokerCommandImpl(user, stock);

    public CommanderConsole() {
        ClassLoader classLoader = stock.getClass().getClassLoader();
        Class<?>[] interfaces = stock.getClass().getInterfaces();
        CustomInvocationHandler invocationHandler = new CustomInvocationHandler(stock);

        StockInterface proxyInstance = (StockInterface) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);

        proxyInstance.createProduct(new FoodProduct("Milk", rub.getRub(),60, 100));
        proxyInstance.createProduct(new FoodProduct("Water", rub.getRub(), 30, 200));
        proxyInstance.createProduct(new NonFoodProduct("laptop", usd.getUsd(), 1000, 20));

        user.setBucket(serialization.deSerialize());

        System.out.println("You are welcome to the shop! Below you can see list of commands.");
        showListCommand();

        try {
            command();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows all commands on console
     */
    private void showListCommand() {
        System.out.println("   1 - Show list of current products");
        System.out.println("   2 - Add product into bucket");
        System.out.println("   3 - Show products in bucket");
        System.out.println("   4 - Remove product from bucket");
        System.out.println("   5 - Clear bucket");
        System.out.println("   finish - Exit from application");
    }

    /**
     * Performs commands
     *
     * @throws IOException if there is problem with I/O
     */
    private void command() throws IOException {
        String action = "";

        while (!action.equalsIgnoreCase("finish")) {
            System.out.println("Please, choose action");
            action = reader.readLine();

            switch (action) {
                case "1":
                    invokerCommandImpl.displayProducts();
                    break;
                case "2":
                    addToBucketEventHandler();
                    break;
                case "3":
                    displayBucketEventHandler();
                    break;
                case "4":
                    delProductEventHandler();
                    break;
                case "5":
                    invokerCommandImpl.clearBucket();
                    break;
                    default:
                        exitEventHandler(action);
                        break;
            }
        }
    }

    /**
     * Handler for exiting from application
     *
     * @param action input parameter of event
     */
    private void exitEventHandler(String action) {

        if (!action.equalsIgnoreCase("finish")) {
            System.out.println("Wrong command. Try again!");
        }

        serialization.serialize(user.getBucket());
    }

    /**
     * Handler for adding product into bucket
     *
     * @throws IOException
     */
    private void addToBucketEventHandler() throws IOException {
        int id;
        int amount;

        System.out.println("Enter id of product for adding to bucket");
        id = Integer.parseInt(reader.readLine());

        if (stock.getStock().get(id) == null) {
            System.out.println("There is no such product is the stock. Re-enter id");
            id = Integer.parseInt(reader.readLine());
            user.setIdOfProductForBucket(id);
        }

        System.out.println("Enter amount of product");
        amount = Integer.parseInt(reader.readLine());

        if (amount > stock.getStock().get(id).getAmount()) {
            System.out.println("There is no such amount of product in the stock. Re-enter amount");
            amount = Integer.parseInt(reader.readLine());
            user.setAmountofProductForBucket(amount);
        }

        user.setIdOfProductForBucket(id);
        user.setAmountofProductForBucket(amount);
        invokerCommandImpl.addToBucket();
    }

    /**
     * Handler for display list of bucket
     *
     * @throws IOException
     */
    private void displayBucketEventHandler() throws IOException {
        Product productInBucket;
        String action;
        int id;
        invokerCommandImpl.displayBucket();

        System.out.println("Do you want to buy product? Y/N");
        action = reader.readLine();

        if (action.equalsIgnoreCase("Y")) {
            System.out.println("Enter id of selected product");
            id = Integer.parseInt(reader.readLine());
            user.setIdOfProductForBucket(id);

            if (user.getBucket().getProductById(id) == null) {
                System.out.println("There is no such product is the bucket. Re-enter id");
                id = Integer.parseInt(reader.readLine());
                user.setIdOfProductForBucket(id);
            }

            productInBucket = user.getBucket().getProductById(id);
            int finalprice = user.getBucket().paymentFinalPrice(productInBucket);
            System.out.println("Final price for you: " + finalprice);
            invokerCommandImpl.delFromBucket();
        }
    }

    /**
     * Handler for removing product from bucket
     *
     * @throws IOException
     */
    private void delProductEventHandler() throws IOException {
        int id;

        System.out.println("Enter id of product, needs to remove from bucket");
        id = Integer.parseInt(reader.readLine());
        user.setIdOfProductForBucket(id);
        invokerCommandImpl.delFromBucket();
    }
}
