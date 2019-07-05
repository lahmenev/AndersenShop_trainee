package task_9.shop.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import task_9.shop.exception.ProductTransactionException;
import task_9.shop.model.entity.Bucket;
import task_9.shop.model.entity.EatableProduct;
import task_9.shop.model.projections.ProductInfo;
import task_9.shop.repo.BucketRepo;
import task_9.shop.repo.EatableProductRepo;
import task_9.shop.service.ProductService;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class EatableProductServiceImpl implements ProductService<EatableProduct> {
    @Autowired
    EatableProductRepo eatableProductRepo;

    @Autowired
    BucketRepo bucketRepo;

    @Override
    public List<EatableProduct> getProductList() {
        return eatableProductRepo.findAll();
    }

    @Override
    public void addProduct(EatableProduct product) {
        eatableProductRepo.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        eatableProductRepo.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = ProductTransactionException.class)
    public void addToBucketProduct(EatableProduct product, String userName) throws ProductTransactionException {
        int sum = getFinalPrice(product);
        int id = product.getId();
        int amount = product.getAmount();
        int productAmount = eatableProductRepo.getEatableProductById(id).getAmount();
        int currentProductAmount = productAmount - amount;

        Bucket bucket = new Bucket(product, sum, userName);

        bucketRepo.save(bucket);
        updateEatableProductAmount(id, currentProductAmount, productAmount);
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

    @Override
    public ProductInfo getProductInfo(int id) {
        return eatableProductRepo.findProductById(id);
    }
}
