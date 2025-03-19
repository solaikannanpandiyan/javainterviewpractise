package org.streams.DAO;

import java.io.*;
import java.util.function.Supplier;
import java.util.stream.Stream;


import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataStreamer<T> {
    private final Supplier<T> generator;

    public DataStreamer(Supplier<T> generator) {
        this.generator = generator;
    }

    public Stream<T> generateData(int count) {
        return Stream.generate(generator).limit(count);
    }

    public Stream<T> generateDataFromCache(int count, String cacheFileName) {
        cacheFileName = "src/main/java/org/streams/cache/" + cacheFileName;
        File cacheFile = new File(cacheFileName);

        // Load from cache if available
        List<T> cachedData = loadCache(cacheFileName);
        if (cachedData != null && cachedData.size() >= count) {
            return cachedData.stream().limit(count);
        }

        // Generate new data and save to cache
        List<T> newData = Stream.generate(generator)
                .limit(count)
                .collect(Collectors.toList());

        saveCache(newData, cacheFileName);

        return newData.stream();
    }

    private List<T> loadCache(String cacheFileName) {
        File cacheFile = new File(cacheFileName);
        if (!cacheFile.exists()) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheFile))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to read cache, generating new data.");
            return null;
        }
    }

    private void saveCache(List<T> data, String cacheFileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cacheFileName))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.err.println("Failed to write cache.");
        }
    }

}
