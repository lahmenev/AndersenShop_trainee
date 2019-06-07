package shop.task_2.controller.proxy;

import shop.task_2.model.Stock;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Random;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class CustomInvocationHandler implements InvocationHandler {
    private Stock stock;

    public CustomInvocationHandler(Stock stock) {
        this.stock = stock;
    }

    /**
     * Processes a method invocation on a proxy instance and returns
     * the result.
     *
     * @param proxy the proxy instance that the method was invoked on
     * @param method instance corresponding to the interface method invoked on the proxy instance.
     * @param args an array of objects containing the values of the arguments
     * @return the value to return from the method invocation on the proxy instance.
     * @throws Throwable the exception to throw from the method invocation on the proxy instance.
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().equals("createProduct")) {
            Class gettingClass = args[0].getClass();
            Field[] fields = gettingClass.getDeclaredFields();

            for (Field field : fields) {

                if (field.isAnnotationPresent(ProductValid.class)) {
                    field.setAccessible(true);
                    field.set(args[0], genRandomDate());
                }
            }
        }

        return method.invoke(stock, args);
    }

    /**
     * Gets random date.
     *
     * @return random date in random range
     */
    private LocalDate genRandomDate() {
        Random random = new Random();
        int minDay = (int) LocalDate.of(2019, 4, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2019, 6, 6).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);

        return LocalDate.ofEpochDay(randomDay);
    }
}
