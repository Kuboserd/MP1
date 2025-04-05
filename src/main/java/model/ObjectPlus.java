package model;

import util.FeedEveryXHours;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ObjectPlus implements Serializable {
    private static Map<Class<? extends ObjectPlus>, List<ObjectPlus>> allExtents = new HashMap<>();

    public ObjectPlus() {
    }

    public static <T extends ObjectPlus> void addExtent(T newObject) {
        Class<? extends ObjectPlus> theClass = newObject.getClass();
        allExtents.computeIfAbsent(theClass, k -> new ArrayList<>()).add(newObject);
    }

    public static void writeExtents(String path) {
        System.out.println("Saving extent to a file...");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(allExtents);
            FeedEveryXHours staticValue = new FeedEveryXHours();
            staticValue.add(Monkey.class, Monkey.getFeedEveryXHours());
            staticValue.add(Tiger.class, Tiger.getFeedEveryXHours());
            out.writeObject(staticValue);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readExtents(String path) {
        System.out.println("Loading extent from a file...");
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            allExtents = (HashMap) in.readObject();
            FeedEveryXHours staticData = (FeedEveryXHours) in.readObject();
            Monkey.setFeedEveryXHours(staticData.get(Monkey.class));
            Tiger.setFeedEveryXHours(staticData.get(Tiger.class));
        } catch (EOFException e) {
            System.out.println("Extent file is empty.");
        } catch (FileNotFoundException e) {
            System.out.println("Extent file not found, creating new one.");
        } catch (ClassNotFoundException e) {
            System.out.println("Extent file contains unknown class.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static <T> Iterable<T> getExtent(Class<T> type) throws ClassNotFoundException {
        List<T> result = new ArrayList<>();

        boolean found = false;

        for (Class<?> storedClass : allExtents.keySet()) {
            if (type.isAssignableFrom(storedClass)) {
                List<ObjectPlus> extent = allExtents.get(storedClass);
                for (ObjectPlus obj : extent) {
                    result.add(type.cast(obj));
                }
                found = true;
            }
        }

        if (!found) {
            throw new ClassNotFoundException("No extents found for class or subclasses: " + type.getSimpleName());
        }

        return result;
    }


    public static void showExtent(Class<?> theClass) throws ClassNotFoundException {
        List<Object> result = new ArrayList<>();

        boolean found = false;

        for (Map.Entry<Class<? extends ObjectPlus>, List<ObjectPlus>> entry : allExtents.entrySet()) {
            if (theClass.isAssignableFrom(entry.getKey())) {
                result.addAll(entry.getValue());
                found = true;
            }
        }

        if (!found) {
            throw new ClassNotFoundException("No extents found for class or subclasses: " + theClass.getSimpleName());
        }

        System.out.println("Extent of class and subclasses: " + theClass.getSimpleName());
        for (Object obj : result) {
            System.out.println(obj);
        }
    }


}