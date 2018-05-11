package ru.inurgalimov.condition;

import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PoinTest {
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);

        // Вычисляем расстояние.
        double result = a.distanceTo(b);
        // Задаем ожидаемый результат.
        double expected = 4.47213595499958;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, is(expected));
    }
}
