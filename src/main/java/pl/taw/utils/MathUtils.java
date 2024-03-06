/**
 * Created by tomasz_taw
 * Date: 06.03.2024
 * Time: 20:52
 * Project Name: lottek
 * Description:
 */
package pl.taw.utils;

import java.math.BigInteger;

public class MathUtils {

    // Metoda do obliczania silni liczby n
    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    // Metoda do obliczania kombinacji Newtona n nad k
    public static BigInteger calculateCombination(int n, int k) {
        BigInteger numerator = factorial(n);
        BigInteger denominator = factorial(k).multiply(factorial(n - k));
        return numerator.divide(denominator);
    }
}
