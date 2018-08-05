package ru.inurgalimov.generic;

/**
 * @author Nurgalimov Ilshat
 * @version 1.0
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    protected SimpleArray<T> simpleArray;
    protected int size;

    public AbstractStore(int size) {
        this.simpleArray = new SimpleArray<T>(size);
        this.size = size;
    }

    public AbstractStore() {
    }

    @Override
    public void add(T model) {
        simpleArray.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean replaceResult = false;
        for (int i = 0; i < size; i++) {
            if (simpleArray.get(i).getId().equals(id)) {
                replaceResult = simpleArray.set(i, model);
                break;
            }
        }
        return replaceResult;
    }

    @Override
    public boolean delete(String id) {
        boolean delete = false;
        for (int i = 0; i < size; i++) {
            if (simpleArray.get(i).getId().equals(id)) {
                delete = simpleArray.delete(i);
                break;
            }
        }
        return delete;
    }

    @Override
    public T findById(String id) {
        T resultFind = null;
        for (int i = 0; i < size; i++) {
            if (simpleArray.get(i).getId().equals(id)) {
                resultFind = simpleArray.get(i);
                break;
            }
        }
        return resultFind;
    }

}
