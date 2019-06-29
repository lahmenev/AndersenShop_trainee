package task_9.shop.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import task_9.shop.exception.ProductTransactionException;
import task_9.shop.model.entity.*;
import task_9.shop.model.projections.ProductInfo;
import task_9.shop.model.strategies.CurrencyStrategy;
import task_9.shop.model.strategies.PaymentByDefault;
import task_9.shop.model.strategies.PaymentByRub;
import task_9.shop.model.strategies.PaymentByUsd;
import task_9.shop.repo.BucketRepo;
import task_9.shop.repo.EatableProductRepo;
import task_9.shop.repo.UnEatableProductRepo;
import task_9.shop.service.StockService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class StockServiceImpl implements StockService {
    private static CurrencyStrategy strategy;

    @Autowired
    EatableProductRepo eatableProductRepo;

    @Autowired
    UnEatableProductRepo unEatableProductRepo;

    @Autowired
    BucketRepo bucketRepo;

    /**
     * Gets list of eatable products from database
     *
     * @return list of products
     */
    @Override
    public List<EatableProduct> getEatableProductList() {
        return eatableProductRepo.findAll();
    }

    /**
     * Gets list of uneatable products from database
     *
     * @return list of products
     */
    @Override
    public List<UnEatableProduct> getUnEatableProductList() {
        return unEatableProductRepo.findAll();
    }

    /**
     * Adds eatable product to List
     *
     * @param product item of products
     */
    @Override
    public void addEatableProduct(EatableProduct product) {
        eatableProductRepo.save(product);
    }

    /**
     * Adds uneatable product to List
     *
     * @param product item of products
     */
    @Override
    public void addUnEatableProduct(UnEatableProduct product) {
        unEatableProductRepo.save(product);
    }

    /**
     * Removes eatable product
     *
     * @param id input parameter
     */
    @Override
    @Transactional
    public void deleteEatableProduct(int id) {
        eatableProductRepo.delete(id);
    }

    /**
     * Removes uneatable product
     *
     * @param id input parameter
     */
    @Override
    @Transactional
    public void deleteUnEatableProduct(int id) {
        unEatableProductRepo.delete(id);
    }

    /**
     * Gets information about eatable product
     *
     * @param id input parameter of id
     * @return ProductInfo instance
     */
    @Override
    public ProductInfo getEatableProductInfo(int id) {
        return eatableProductRepo.findProductById(id);
    }

    /**
     * Gets information about uneatable product
     *
     * @param id input parameter of id
     * @return ProductInfo instance
     */
    @Override
    public ProductInfo getUnEatableProductInfo(int id) {
        return unEatableProductRepo.findProductById(id);
    }

    /**
     * Updates amount value of eatable product
     *
     * @param id input parameter of identity
     * @param currentProductAmount input parameter of current amount
     * @param productAmount input parameter of amount
     * @throws ProductTransactionException
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateEatableProductAmount(int id, int currentProductAmount, int productAmount) throws ProductTransactionException {

        if (currentProductAmount < 0) {
            throw new ProductTransactionException("There is no such amount of product: current amount : " + productAmount);
        }

        eatableProductRepo.update(currentProductAmount, id);
    }

    /**
     * Updates amount value of uneatable product
     *
     * @param id input parameter of identity
     * @param currentProductAmount input parameter of current amount
     * @param productAmount input parameter of amount
     * @throws ProductTransactionException
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateUnEatableProductAmount(int id, int currentProductAmount, int productAmount) throws ProductTransactionException {

        if (currentProductAmount < 0) {
            throw new ProductTransactionException("There is no such amount of product: current amount : " + productAmount);
        }

        unEatableProductRepo.update(currentProductAmount, id);
    }

    /**
     * Adds eatable product to bucket
     *
     * @param product input parameter of Product
     * @param userName input parameter of User
     * @throws ProductTransactionException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = ProductTransactionException.class)
    public void addToBucketEatableProduct(EatableProduct product, String userName) throws ProductTransactionException {
        String currency = product.getCurrency();
        int id = product.getId();
        int price = product.getPrice();
        int amount = product.getAmount();
        int sum = paymentFinalPrice(currency, price, amount);
        int productAmount = eatableProductRepo.getEatableProductById(id).getAmount();
        int currentProductAmount = productAmount - amount;

        Bucket bucket = new Bucket(product, sum, userName);

        bucketRepo.save(bucket);
        updateEatableProductAmount(id, currentProductAmount, productAmount);
    }

    /**
     * Adds uneatable product to bucket
     *
     * @param product input parameter of Product
     * @param userName input parameter of User
     * @throws ProductTransactionException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = ProductTransactionException.class)
    public void addToBucketUnEatableProduct(UnEatableProduct product, String userName) throws ProductTransactionException {
        String currency = product.getCurrency();
        int id = product.getId();
        int price = product.getPrice();
        int amount = product.getAmount();
        int sum = paymentFinalPrice(currency, price, amount);
        int productAmount = unEatableProductRepo.getUnEatableProductById(id).getAmount();
        int currentProductAmount = productAmount - amount;

        Bucket bucket = new Bucket(product, sum, userName);

        bucketRepo.save(bucket);
        updateUnEatableProductAmount(id, currentProductAmount, productAmount);
    }


    /**
     * Gets final price of product
     *
     * @param currency input parameter of currency
     * @param price input parameter of price
     * @param amount input parameter of amount
     * @return final price of product
     */
    private int paymentFinalPrice(String currency, int price, int amount) {
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
}
