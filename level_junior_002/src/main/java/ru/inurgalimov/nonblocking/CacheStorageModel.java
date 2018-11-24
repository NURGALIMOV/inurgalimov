package ru.inurgalimov.nonblocking;

import java.util.concurrent.ConcurrentHashMap;

public class CacheStorageModel {
    private ConcurrentHashMap<Integer, Base> storage;

    public CacheStorageModel() {
        this.storage = new ConcurrentHashMap<>();
    }

    public Base add(Base model) {
        return this.storage.put(model.getId(), model);
    }

    public Base update(Base model) {
        while (storage.get(model.getId()) == null) {
            System.out.println(" ");
        }
        int tempVersion = storage.get(model.getId()).getVersion();
        return storage.computeIfPresent(model.getId(), (key, value) -> {
            storage.get(key).setVersion(storage.get(key).getVersion() + 1);
            if ((tempVersion + 1) != storage.get(key).getVersion()) {
                throw new OptimisticException("The current version of the model differs by more than one.");
            }
            value.setVersion(tempVersion + 1);
            return storage.replace(key, value);
        });
    }

    public Base delete(Base model) {
        return storage.remove(model.getId());
    }
}
