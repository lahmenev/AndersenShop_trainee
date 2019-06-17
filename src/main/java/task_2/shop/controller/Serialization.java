package task_2.shop.controller;

import task_2.shop.model.Bucket;
import java.io.*;

/**
 * This is class to do serialize.
 *
 *
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class Serialization {

    /**
     * Makes serialization
     *
     * @param bucket object of Bucket
     */
    public void serialize(Bucket bucket) {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("temp.out"))) {
            out.writeObject(bucket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Makes deserialization.
     *
     * @return object Bucket
     */
    public Bucket deSerialize() {
        Bucket bucket = null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("temp.out"))) {
            bucket = (Bucket) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return bucket;
    }
}
