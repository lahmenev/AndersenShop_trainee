package shop.controller;

import shop.commands.*;
import shop.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class CommanderConsole {
    private int id;
    private Stock stock = new Stock();
    private Bucket bucket = new Bucket();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private Command displayProductsCommand = new DisplayProductsCommand(stock);
    private Command displayBucketCommand = new DisplayBucketCommand(bucket);
    private Command addToBucketCommand = new AddToBucketCommand(bucket, stock, this);
    private Command delFromBucketCommand = new DelFromBucketCommand(bucket, this);
    private Command clearBucketCommand = new ClearBucketCommand(bucket);

    private InvokerCommands invokerCommands = new InvokerCommands(displayProductsCommand, displayBucketCommand, addToBucketCommand,
            delFromBucketCommand, clearBucketCommand);

    /**
     * Initialization of application
     */
    public void init() {
        Product product1 = new FoodProduct("milk", 50, 100);
        Product product2 = new NonFoodProduct("car", 10_000, 2);
        Product product3 = new NonFoodProduct("car2", 10_000, 2);

        stock.createProduct(product1);
        stock.createProduct(product2);
        stock.createProduct(product3);

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
     * @throws IOException if there is problem with I/O
     */
    private void command() throws IOException {
        String action = "";

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
                    invokerCommands.addToBucket();
                    break;
                case "3":
                    invokerCommands.displayBucket();
                    break;
                case "4":
                    System.out.println("Enter id of product, needs to remove from bucket");
                    id = Integer.parseInt(reader.readLine());
                    invokerCommands.delFromBucket();
                    break;
                case "5":
                    invokerCommands.clearBucket();
                    break;
                    default:

                        if (!action.equalsIgnoreCase("finish")) {
                            System.out.println("Wrong command. Try again!");
                        }

                        break;
            }
        }
    }

    public int getId() {
        return id;
    }
}
