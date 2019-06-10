package task_3.shop.controller;

import task_3.shop.commands.*;
import task_3.shop.controller.proxy.CustomInvocationHandler;
import task_3.shop.model.*;
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

    private Command displayProductsCommand = new DisplayProductsCommand(stock);
    private Command displayBucketCommand = new DisplayBucketCommand(customer);
    private Command addToBucketCommand = new AddToBucketCommand(stock, customer);
    private Command delFromBucketCommand = new DelFromBucketCommand(customer);
    private Command clearBucketCommand = new ClearBucketCommand(customer);
    private Command showUserInfo = new ShowUserInfo(customer);

    private InvokerCommands invokerCommands = new InvokerCommands(displayProductsCommand, displayBucketCommand, addToBucketCommand,
            delFromBucketCommand, clearBucketCommand, showUserInfo);

    public CommanderConsole() {
        ClassLoader classLoader = stock.getClass().getClassLoader();
        Class<?>[] interfaces = stock.getClass().getInterfaces();
        CustomInvocationHandler invocationHandler = new CustomInvocationHandler(stock);

        StockInterface proxyInstance = (StockInterface) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);

        proxyInstance.createProduct(new FoodProduct("Milk", "RUB",60, 100));
        proxyInstance.createProduct(new FoodProduct("Water", "RUB", 30, 200));
        proxyInstance.createProduct(new NonFoodProduct("laptop", "USD", 1000, 20));

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
        System.out.println("   6 - Show user information");
        System.out.println("   finish - Exit from application");
    }

    /**
     * Performs commands
     *
     * @throws IOException if there is problem with I/O
     */
    private void command() throws IOException {
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

                    System.out.println("Enter amount of product");
                    amount = Integer.parseInt(reader.readLine());

                    customer.setIdOfProductForBucket(id);
                    customer.setAmountOfProductForBucket(amount);
                    invokerCommands.addToBucket();
                    break;
                case "3":
                    invokerCommands.displayBucket();
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
                case "6":
                    invokerCommands.showUserInfo();
                    break;
                default:

                    if (!action.equalsIgnoreCase("finish")) {
                        System.out.println("Wrong command. Try again!");
                    }

                    break;

            }
        }
    }
}
