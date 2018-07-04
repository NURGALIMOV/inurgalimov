package ru.inurgalimov.additional;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {
    @Test
    public void whenManyValidationOne() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] array = new int[]{10, 5};
        int[] result = coffeeMachine.changes(50, 35);
        assertThat(result, is(array));
    }

    @Test
    public void whenManyValidationTwo() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] array = new int[]{5};
        int[] result = coffeeMachine.changes(25, 20);
        assertThat(result, is(array));
    }

    @Test
    public void whenManyValidationThree() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] array = new int[]{-5};
        int[] result = coffeeMachine.changes(25, 30);
        assertThat(result, is(array));
    }

    @Test
    public void whenManyValidationFour() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] array = new int[]{0};
        int[] result = coffeeMachine.changes(30, 30);
        assertThat(result, is(array));
    }
}
