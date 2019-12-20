package challenge4;

import org.testng.Assert;
import org.testng.annotations.*;

public class challenge4 {
    private fibonacciFunction fibWords;
    @Test
    public void fibCheck() {
        fibWords = new fibonacciFunction();
        System.out.println(fibWords.fibCalculate(2));
        Assert.assertEquals((fibWords.fibCalculate(2)), "1 one");
        System.out.println((fibWords.fibCalculate(7)));
        Assert.assertEquals((fibWords.fibCalculate(7)), "13 thirteen");
        System.out.println((fibWords.fibCalculate(19)));
        Assert.assertEquals((fibWords.fibCalculate(19)), "4181 four thousand one hundred eighty one");
        System.out.println((fibWords.fibCalculate(44)));
        Assert.assertEquals((fibWords.fibCalculate(44)), "701408733 seven hundred one million four hundred eight thousand seven hundred thirty three");
        System.out.println((fibWords.fibCalculate(45)));
        Assert.assertEquals((fibWords.fibCalculate(45)), "1134903170 one billion one hundred thirty four million nine hundred three thousand one hundred seventy");
    }
}


