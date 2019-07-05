package task_9.shop.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import task_9.shop.exception.ProductTransactionException;
import task_9.shop.model.entity.Bucket;
import task_9.shop.model.entity.UnEatableProduct;
import task_9.shop.model.projections.ProductInfo;
import task_9.shop.repo.BucketRepo;
import task_9.shop.repo.UnEatableProductRepo;
import task_9.shop.service.ProductService;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class UnEatableProductServiceImpl implements ProductService<UnEatableProduct> {
    @Autowired
    UnEatableProductRepo unEatableProductRepo;

    @Autowired
    BucketRepo bucketRepo;

    @Override
    public List<UnEatableProduct> getProductList() {
        return unEatableProductRepo.findAll();
    }

    @Override
    public void addProduct(UnEatableProduct product) {
        unEatableProductRepo.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        unEatableProductRepo.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = ProductTransactionException.class)
    public void addToBucketProduct(UnEatableProduct product, String userName) throws ProductTransactionException {
        int sum = getFinalPrice(product);
        int id = product.getId();
        int amount = product.getAmount();
        int productAmount = unEatableProductRepo.getUnEatableProductById(id).getAmount();
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

        unEatableProductRepo.update(currentProductAmount, id);
    }

    @Override
    public ProductInfo getProductInfo(int id) {
        return unEatableProductRepo.findProductById(id);
    }
}
