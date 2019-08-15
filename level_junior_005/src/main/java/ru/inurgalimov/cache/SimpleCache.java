package ru.inurgalimov.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Простая реализация кеша.
 */
public class SimpleCache implements ICache {

    /** Хранилище */
    private Map<String, SoftReference<byte[]>> cache = new HashMap<>();

    @Override
    public byte[] get(String key) {
        byte[] result = cache.get(key).get();
        if (result == null) {
            set(Paths.get(key).toFile());
            result = cache.get(key).get();
        }
        return result;
    }

    @Override
    public <T> void set(T t) {
        try(InputStream in = new FileInputStream((File) t)) {
            cache.put(((File) t).getName(), new SoftReference(in.readAllBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}