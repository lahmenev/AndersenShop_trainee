package task_8.shop.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import task_8.shop.exception.ProductTransactionException;
import task_8.shop.model.entity.Bucket;
import task_8.shop.model.entity.Country;
import task_8.shop.model.entity.Product;
import task_8.shop.model.projections.ProductInfo;
import task_8.shop.model.strategies.CurrencyStrategy;
import task_8.shop.model.strategies.PaymentByDefault;
import task_8.shop.model.strategies.PaymentByRub;
import task_8.shop.model.strategies.PaymentByUsd;
import task_8.shop.repo.BucketRepo;
import task_8.shop.repo.ProductRepo;
import task_8.shop.service.StockService;
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
    ProductRepo productRepo;

    @Autowired
    BucketRepo bucketRepo;

    /**
     * Gets list of products from database
     *
     * @return list of products
     */
    @Override
    public List<Product> getStockList() {
        return productRepo.findAll();
    }

    /**
     * Adds product to List
     *
     * @param product item of products
     */
    @Override
    public void addProduct(Product product) {
        productRepo.save(product);
    }

    /**
     * Removes product
     *
     * @param id input parameter
     */
    @Override
    public void deleteProduct(int id) {
        productRepo.delete(id);
    }

    /**
     * Gets information about product
     *
     * @param id input parameter of id
     * @return ProductInfo instance
     */
    public ProductInfo getProductInfo(int id) {
        return productRepo.findProductById(id);
    }

    /**
     * Updates amount value of product
     *
     * @param id input parameter of identity
     * @param currentProductAmount input parameter of current amount
     * @param productAmount input parameter of amount
     * @throws ProductTransactionException
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateProductAmount(int id, int currentProductAmount, int productAmount) throws ProductTransactionException {

        if (currentProductAmount < 0) {
            throw new ProductTransactionException("There is no such amount of product: current amount : " + productAmount);
        }

        productRepo.update(currentProductAmount, id);
    }

    /**
     * Adds product to bucket
     *
     * @param product input parameter of Product
     * @param userName input parameter of User
     * @throws ProductTransactionException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = ProductTransactionException.class)
    public void addToBucket(Product product, String userName) throws ProductTransactionException {
        String name = product.getName();
        String currency = product.getCurrency();
        Country country = product.getCountry();
        int id = product.getId();
        int price = product.getPrice();
        int amount = product.getAmount();
        int sum = paymentFinalPrice(currency, price, amount);
        int productAmount = productRepo.getProductsById(id).getAmount();
        int currentProductAmount = productAmount - amount;

        Bucket bucket = new Bucket(new Product(name, currency, price, amount, country), sum, userName);

        bucketRepo.save(bucket);
        updateProductAmount(id, currentProductAmount, productAmount);
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
