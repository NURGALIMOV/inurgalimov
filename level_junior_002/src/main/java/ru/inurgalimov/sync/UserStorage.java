package ru.inurgalimov.sync;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class UserStorage {

    private volatile User[] userArray;
    private volatile int position;

    public UserStorage() {
        userArray = new User[1000];
        position = 0;
    }

    public synchronized boolean add(User user) {
        if (position == userArray.length) {
            User[] temp = new User[2 * position];
            System.arraycopy(userArray, 0, temp, 0, userArray.length);
            userArray = temp;
        }
        userArray[position++] = user;
        return true;
    }

    public synchronized boolean update(User user) {
        boolean result = false;
        int index = indexForFindUser(user.getId());
        if (index != -1) {
            userArray[index] = user;
            result = true;
        }
        return result;
    }

    public synchronized boolean delete(User user) {
        boolean result = false;
        int index = indexForFindUser(user.getId());
        if (index != -1) {
            result = true;
            userArray[index] = null;
        }
        return result;
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        try {
            int indexFromId = indexForFindUser(fromId);
            int indexToId = indexForFindUser(toId);
            userArray[indexFromId].setAmount(userArray[indexFromId].getAmount() - amount);
            userArray[indexToId].setAmount(userArray[indexToId].getAmount() + amount);
        } catch (ArrayIndexOutOfBoundsException arrEx) {
            arrEx.printStackTrace();
            System.out.println("Операция не возможна! Одного из пользователей нет в структуре.");
        }
    }

    public synchronized int indexForFindUser(int id) {
        int result = -1;
        for (int i = 0; i < userArray.length; i++) {
            if (userArray[i].getId() == id) {
                result = i;
                break;
            }
        }
        return result;
    }

    public User[] getUserArray() {
        return userArray;
    }
}
