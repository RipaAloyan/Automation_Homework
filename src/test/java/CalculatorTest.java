import org.testng.annotations.*;
import org.testng.*;

public class CalculatorTest {
    @Test
    public void addNumbers(){
        double actualAdd =  Calculator.add(5.5, 14.68);
        double expectedAdd = 20.18;

        Assert.assertEquals(actualAdd, expectedAdd,0.01);
    }

    @Test
    public void subNumbers(){
        double actualSub =  Calculator.sub(50, 1.8);
        double expectedSub = 48.2;

        Assert.assertEquals(actualSub, expectedSub,0.1);
    }

    @Test
    public void mulNumbers(){
        double actualMul =  Calculator.mul(5.4, 2.2);
        double expectedMul = 11.88;

        Assert.assertEquals(actualMul, expectedMul,0.01);
    }

    @Test
    public void divNumbers() throws Exception {
        double actualDiv =  Calculator.div(4.4, 2.2);
        double expectedDiv = 2.0;

        Assert.assertEquals(actualDiv, expectedDiv,0.1);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void divIntoZero() throws Exception {
        Calculator.div(20.12, 0);
    }
}