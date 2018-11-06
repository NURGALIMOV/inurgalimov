package ru.inurgalimov.sync;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test for class UserStorage Multithreading
 *
 * @author Ilshat Nurgalimov
 */
public class UserStorageTest {
    UserStorage userStorage = new UserStorage();
    Thread thread1 = new Thread(() -> {
        userStorage.add(new User(1, 100));
        User[] userTempArray;
        while (true) {
            userTempArray = userStorage.getUserArray();
            if (userTempArray[0] != null && userTempArray[1] != null) {
                userStorage.transfer(1, 2, 50);
                break;
            }
        }
    });
    Thread thread2 = new Thread(() -> {
        userStorage.add(new User(2, 200));
        User[] userTempArray;
        while (true) {
            userTempArray = userStorage.getUserArray();
            if (userTempArray[1] != null && userTempArray[2] != null) {
                userStorage.transfer(2, 3, 100);
                break;
            }
        }
    });
    Thread thread3 = new Thread(() -> {
        userStorage.add(new User(3, 300));
        User[] userTempArray;
        while (true) {
            userTempArray = userStorage.getUserArray();
            if (userTempArray[1] != null && userTempArray[2] != null) {
                userStorage.transfer(3, 1, 200);
                break;
            }
        }
    });

    @Test
    public void userStorageMultithreadTest() {
        thread1.start();
        thread2.start();
        thread3.start();
        User[] test = userStorage.getUserArray();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(userStorage.add(new User(1010, 1000)), is(true));
        assertThat(test[0].getAmount(), is(250));
        assertThat(test[1].getAmount(), is(150));
        assertThat(test[2].getAmount(), is(200));
    }

}