/**
 * Created by tomasz_taw
 * Date: 04.03.2024
 * Time: 23:43
 * Project Name: lottek
 * Description:
 */
package pl.taw.services;

import org.apache.commons.math3.util.CombinatoricsUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LottoService {

    public List<Integer> generateRandomNumbers(int count) {
        // Implementacja generowania losowych liczb
        return Collections.emptyList();
    }

    public double calculateWinningProbability(List<Integer> playerNumbers, List<Integer> winningNumbers) {
        // Implementacja obliczania prawdopodobieństwa wygranej
        return 1.0;
    }

    public long calculateNumberOfCombinations(int n, int k) {
        return CombinatoricsUtils.binomialCoefficient(n, k);
    }

    public double calculateProbability(int totalNumbers, int selectedNumbers, int drawnNumbers) {
        long allCombinations = CombinatoricsUtils.binomialCoefficient(totalNumbers, selectedNumbers);
        long successfulCombinations = CombinatoricsUtils.binomialCoefficient(selectedNumbers, drawnNumbers);

        return (double) successfulCombinations / allCombinations;
    }

}
