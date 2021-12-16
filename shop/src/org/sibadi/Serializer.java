package org.sibadi;

import java.io.*;

public final class Serializer {

    private Serializer() {
    }

    public static <T extends Serializable> void serialization(T obj, String path) throws IOException {
        if (obj == null) throw new IllegalArgumentException();

        var file = new File(path);
        var fileOutputStream = new FileOutputStream(file);
        var objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(obj);
        objectOutputStream.close();
    }

    public static <T extends Serializable> T deserialization(String path) throws IOException, ClassNotFoundException {

        var file = new File(path);
        var fileInputStream = new FileInputStream(file);
        var objectInputStream = new ObjectInputStream(fileInputStream);

        @SuppressWarnings("unchecked")
        T value = (T) objectInputStream.readObject();

        objectInputStream.close();
        return value;
    }
}
