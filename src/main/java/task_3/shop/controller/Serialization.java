package task_3.shop.controller;

import task_3.shop.model.Bucket;

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
     * @throws IOException
     */
    public void serialize(Bucket bucket) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("temp.out"));
        out.writeObject(bucket);
        out.flush();
        out.close();
    }

    /**
     * Makes deserialization.
     *
     * @return object Bucket
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Bucket deSerialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("temp.out"));
        return (Bucket) in.readObject();
    }
}
