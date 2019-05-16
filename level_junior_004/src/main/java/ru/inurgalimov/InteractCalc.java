package ru.inurgalimov;

import org.apache.commons.lang.math.NumberUtils;
import ru.inurgalimov.calculator.Calculatable;
import ru.inurgalimov.calculator.Calculator;
import ru.inurgalimov.calculator.EngineeringСalculator;
import ru.inurgalimov.calculator.Parametr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Пользовательский интерфейс для класса Calculator.
 *
 * @author Ilshat Nurgalimov
 * @since 17.05.2019
 */
public class InteractCalc {
    /**
     * Поле класса InteractCalc, будет использован для вычеслений.
     */
    private final Calculatable calculator;
    /**
     * Поле класса InteractCalc, содержит список возможных действий.
     */
    private final Map<String, Consumer<Parametr>> action;

    /**
     * Конструктор для класса InteractCalc.
     *
     * @param calculator - инециализируем поле класса, для дальнейших вычислений.
     */
    public InteractCalc(final Calculatable calculator) {
        this.calculator = calculator;
        action = new HashMap();
        action.put("+", this.calculator::add);
        action.put("-", this.calculator::subtract);
        action.put("/", this.calculator::div);
        action.put("*", this.calculator::multiple);
        action.put("pow", this.calculator::pow);
        action.put("sqrt", this.calculator::sqrt);
        action.put("sin", this.calculator::sin);
        action.put("tan", this.calculator::tan);
        action.put("cos", this.calculator::cos);
    }

    /**
     * Запуск программы.
     *
     * @param args - список параметров при запуске программы, в данной реализации не требуется.
     */
    public static void main(String[] args) {
        InteractCalc interactCalc = new InteractCalc(new EngineeringСalculator());
        interactCalc.start();
    }

    /**
     * Ввод числа.
     *
     * @param reader - поток для ввода информации от пользователя, BufferedReader.
     * @return - возвращаем число введенное пользователем.
     * @throws IOException - возможны ошибки ввода, пробрасываем в вызывающий метод для последующей обработки.
     */
    public double inputNumber(BufferedReader reader) throws IOException {
        double in = 0;
        boolean check = true;
        while (check) {
            String temp = reader.readLine();
            if (NumberUtils.isNumber(temp)) {
                in = Double.parseDouble(temp);
                check = false;
            } else if ("get last".equals(temp)) {
                in = this.calculator.getResult();
                check = false;
            } else {
                System.out.println("Ввели не число, попробуйте снова!");
                System.out.println("Если нужно использовать предыдущий результат введите \"get last\"");
            }
        }

        return in;
    }

    /**
     * Ввод действия.
     *
     * @param reader - поток для ввода информации от пользователя, BufferedReader.
     * @return - возвращаем действие
     * @throws IOException - возможны ошибки ввода, пробрасываем в вызывающий метод для последующей обработки.
     */
    public String inputAction(BufferedReader reader) throws IOException {
        String action = reader.readLine();
        while (this.action.get(action) == null) {
            System.out.println("Такого действия нет, попробуйте снова!");
            printMenu();
            action = reader.readLine();
        }
        return action;
    }

    /**
     * Вывод возможных действий.
     */
    public void printMenu() {
        System.out.println("Список возможных действий в этой программе:");
        for (String s : this.action.keySet()) {
            System.out.print(String.format("<%s> ", s));
        }
        System.out.println();
        System.out.println("Если расчеты нужны только для одного числа, такие как sin(угла), "
                + "учитывается только первое число. "
                + "Второе не будет учитываться, для продолжения работы введите любое число");
    }

    /**
     * Запуск основного цикла программы.
     */
    public void start() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean check = true;
            while (check) {
                this.printMenu();
                System.out.println("Введите первое число:");
                double first = this.inputNumber(reader);
                System.out.println("Введите второе число:");
                double second = this.inputNumber(reader);
                System.out.println("Введите действие:");
                String str = this.inputAction(reader);
                this.action.get(str).accept(new Parametr(first, second));
                System.out.printf("Результат %s чисел %f и %f равен %f \n",
                        str, first, second, this.calculator.getResult());
                System.out.println("Если желаете продолжить введите \"y\"");
                check = "y".equals(reader.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
