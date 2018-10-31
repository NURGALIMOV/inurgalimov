package ru.inurgalimov.sync;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class UserStorage {

    private volatile User[] userArray;

    public UserStorage() {
        userArray = new User[1000];
    }

    public synchronized boolean add(User user) {
        try {
            userArray[user.getId()] = user;
        } catch (ArrayIndexOutOfBoundsException arrEx) {
            arrEx.printStackTrace();
            User[] temp = new User[user.getId() + 1000];
            System.arraycopy(userArray, 0, temp, 0, userArray.length);
            userArray = temp;
            userArray[user.getId()] = user;
        }
        return true;
    }

    public synchronized boolean update(User user) {
        boolean result = false;
        if (userArray[user.getId()] != null) {
            result = true;
            userArray[user.getId()] = user;
        }
        return result;
    }

    public synchronized boolean delete(User user) {
        boolean result = true;
        try {
            userArray[user.getId()] = null;
        } catch (ArrayIndexOutOfBoundsException arrEx) {
            arrEx.printStackTrace();
            result = false;
        }
        return result;
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        try {
            userArray[fromId].setAmount(userArray[fromId].getAmount() - amount);
            userArray[toId].setAmount(userArray[toId].getAmount() + amount);
        } catch (ArrayIndexOutOfBoundsException arrEx) {
            arrEx.printStackTrace();
            System.out.println("Операция не возможна! Одного из пользователей нет в структуре.");
        }
    }

    public User[] getUserArray() {
        return userArray;
    }
}
