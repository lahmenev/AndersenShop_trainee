package task_9.shop.service;

import task_9.shop.exception.ProductTransactionException;
import task_9.shop.model.entity.Product;
import task_9.shop.model.projections.ProductInfo;
import task_9.shop.model.strategies.CurrencyStrategy;
import task_9.shop.model.strategies.PaymentByDefault;
import task_9.shop.model.strategies.PaymentByRub;
import task_9.shop.model.strategies.PaymentByUsd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface ProductService<E extends Product> {

    /**
     * Gets list of products from database
     *
     * @return list of products
     */
    List<E> getProductList();

    /**
     * Adds product to List
     *
     * @param product item of products
     */
    void addProduct(E product);

    /**
     * Removes product
     *
     * @param id input parameter
     */
    void deleteProduct(int id);

    /**
     * Adds product to bucket
     *
     * @param product input parameter of Product
     * @param userName input parameter of User
     * @throws ProductTransactionException
     */
    void addToBucketProduct(E product, String userName) throws ProductTransactionException;

    /**
     * Gets information about product
     *
     * @param id input parameter of id
     * @return ProductInfo instance
     */
    ProductInfo getProductInfo(int id);

    /**
     * Gets final price of product
     *
     * @param currency input parameter of currency
     * @param price input parameter of price
     * @param amount input parameter of amount
     * @return final price of product
     */
    default int paymentFinalPrice(String currency, int price, int amount) {
        CurrencyStrategy strategy = null;

        Map<String, CurrencyStrategy> rubMap = new HashMap<>();
        Map<String, CurrencyStrategy> usdMap = new HashMap<>();
        PaymentByRub rubPayment = new PaymentByRub();
        PaymentByUsd usdPayment = new PaymentByUsd();

        rubMap.put("RUB", rubPayment);
        usdMap.put("USD", usdPayment);

        if (currency.equals("RUB")) {
            strategy = rubMap.getOrDefault("RUB", new PaymentByDefault());
        } else if (currency.equals("USD")) {
            strategy = usdMap.getOrDefault("USD", new PaymentByDefault());
        }

        return strategy.payment(price, amount);
    }

    /**
     * Gets final price for product
     *
     * @param e input parameter of Product
     * @return final price for product
     */
    default int getFinalPrice(E e) {
        String currency = e.getCurrency();
        int price = e.getPrice();
        int amount = e.getAmount();
        return paymentFinalPrice(currency, price, amount);
    }
}
