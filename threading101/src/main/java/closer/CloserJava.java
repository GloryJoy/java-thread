package closer;

import java.util.function.BiFunction;

class RunningCloser{
    public <T> T processCalculation(BiFunction inputFunction) {
        return (T) inputFunction.apply(10, 20);
    }

}
public class CloserJava {
    public static void main(String[] args) {

        BiFunction multiplier = (number, times) -> {
            final Integer total = (Integer) number * (Integer) times;
            return total;
        };

        BiFunction devision = (number, times) -> {
            final Double result = (Double) number / (Double) times;
            return result;
        };
        System.out.printf("the result is %d\n", (Integer) multiplier.apply(10, 20));
        System.out.println("The result is " + new RunningCloser().processCalculation(multiplier));
        System.out.println("The result is " + new RunningCloser().processCalculation(devision));


    }

}
