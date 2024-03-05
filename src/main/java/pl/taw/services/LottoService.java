/**
 * Created by tomasz_taw
 * Date: 04.03.2024
 * Time: 23:43
 * Project Name: lottek
 * Description:
 */
package pl.taw.services;

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
}
