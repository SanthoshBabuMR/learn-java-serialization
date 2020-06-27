package com.babusa.learn.serialization;


import java.io.*;

public class SerializationExample implements Serializable  {

    // special variable used by java for serialization/deserialization process
    private static final long serialVersionUID = 1;

    // primitive type: static var
    static String staticStr = "static variables are skipped from serialization";

    // primitive type: transitent instance var
    transient String transientInstStr = "transisent values are skipped from serialization process";

    // primitive type: instance var
    String instStr = "only instance variable(s) gets serialized";

    // custom type: instance var
    Person person;

    // instance method: gets skipped from serialization
    public void sayHello() {
        System.out.println("Hello!! It's a pleasure to meet you :)");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializationExample serializationExampleWrite = new SerializationExample();
        serializationExampleWrite.person = new Person();
        serializationExampleWrite.person.fname = "foo";
        serializationExampleWrite.person.lname = "king";

        // write object state (i.e instance variable data only) to file
        OutputStream os = new FileOutputStream("serialized-object.txt");
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(serializationExampleWrite);

        // read object state from file
        InputStream is = new FileInputStream("serialized-object.txt");
        ObjectInputStream ois = new ObjectInputStream(is);
        SerializationExample serializationExampleRead = (SerializationExample) ois.readObject();
        System.out.println(String.format("person: %s %s", serializationExampleRead.person.fname, serializationExampleRead.person.lname));
    }
}
