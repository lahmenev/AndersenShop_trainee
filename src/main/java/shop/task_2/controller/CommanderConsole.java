package shop.task_2.controller;

import shop.task_2.commands.*;
import shop.task_2.controller.proxy.CustomInvocationHandler;
import shop.task_2.model.*;
import shop.task_2.model.strategies.PaymentByRub;
import shop.task_2.model.strategies.PaymentByUsd;
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
    private Customer customer = new Customer("Sergey", 5000);
    private PaymentByRub rub = new PaymentByRub();
    private PaymentByUsd usd = new PaymentByUsd();
    private Serialization serialization = new Serialization();

    private Command displayProductsCommand = new DisplayProductsCommand(stock);
    private Command displayBucketCommand = new DisplayBucketCommand(customer);
    private Command addToBucketCommand = new AddToBucketCommand(stock, customer);
    private Command delFromBucketCommand = new DelFromBucketCommand(customer);
    private Command clearBucketCommand = new ClearBucketCommand(customer);

    private InvokerCommands invokerCommands = new InvokerCommands(displayProductsCommand, displayBucketCommand, addToBucketCommand,
            delFromBucketCommand, clearBucketCommand);

    public CommanderConsole() {
        ClassLoader classLoader = stock.getClass().getClassLoader();
        Class<?>[] interfaces = stock.getClass().getInterfaces();
        CustomInvocationHandler invocationHandler = new CustomInvocationHandler(stock);

        StockInterface proxyInstance = (StockInterface) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);

        proxyInstance.createProduct(new FoodProduct("Milk", rub.getRub(),60, 100));
        proxyInstance.createProduct(new FoodProduct("Water", rub.getRub(), 30, 200));
        proxyInstance.createProduct(new NonFoodProduct("laptop", usd.getUsd(), 1000, 20));

        try {
            customer.setBucket(serialization.deSerialize());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

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
        Product productInBucket;
        String action = "";
        int id;
        int amount;

        while (!action.equalsIgnoreCase("finish")) {
            System.out.println("Please, choose action");
            action = reader.readLine();

            switch (action) {
                case "1":
                    invokerCommands.displayProducts();
                    break;
                case "2":
                    System.out.println("Enter id of product for adding to bucket");
                    id = Integer.parseInt(reader.readLine());

                    if (stock.getStock().get(id) == null) {
                        System.out.println("There is no such product is the stock. Re-enter id");
                        id = Integer.parseInt(reader.readLine());
                        customer.setIdOfProductForBucket(id);
                    }

                    System.out.println("Enter amount of product");
                    amount = Integer.parseInt(reader.readLine());

                    if (amount > stock.getStock().get(id).getAmount()) {
                        System.out.println("There is no such amount of product in the stock. Re-enter amount");
                        amount = Integer.parseInt(reader.readLine());
                        customer.setAmountofProductForBucket(amount);
                    }

                    customer.setIdOfProductForBucket(id);
                    customer.setAmountofProductForBucket(amount);
                    invokerCommands.addToBucket();
                    break;
                case "3":
                    invokerCommands.displayBucket();

                    System.out.println("Do you want to buy product? Y/N");
                    action = reader.readLine();

                    if (action.equalsIgnoreCase("Y")) {
                        System.out.println("Enter id of selected product");
                        id = Integer.parseInt(reader.readLine());
                        customer.setIdOfProductForBucket(id);

                        if (customer.getBucket().getProductById(id) == null) {
                            System.out.println("There is no such product is the bucket. Re-enter id");
                            id = Integer.parseInt(reader.readLine());
                            customer.setIdOfProductForBucket(id);
                        }

                        productInBucket = customer.getBucket().getProductById(id);
                        int finalprice = customer.getBucket().paymentFinalPrice(productInBucket);
                        System.out.println("Final price for you: " + finalprice);
                        invokerCommands.delFromBucket();
                    }

                    break;
                case "4":
                    System.out.println("Enter id of product, needs to remove from bucket");
                    id = Integer.parseInt(reader.readLine());
                    customer.setIdOfProductForBucket(id);
                    invokerCommands.delFromBucket();
                    break;
                case "5":
                    invokerCommands.clearBucket();
                    break;
                    default:

                        if (!action.equalsIgnoreCase("finish")) {
                            System.out.println("Wrong command. Try again!");
                        }

                        serialization.serialize(customer.getBucket());
                        break;
            }
        }
    }
}
