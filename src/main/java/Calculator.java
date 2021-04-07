public abstract class Calculator {
    public static double add(double a, double b) {
        return a + b;
    }

    public static double sub(double a, double b) {
        return a - b;
    }

    public static double mul(double a, double b) {
        return a * b;
    }

    public static double div(double a, double b) throws Exception {
        if (b == 0) {
            throw new ArithmeticException("A number cannot be divided into 0");
        }
        return a / b;
    }
}
