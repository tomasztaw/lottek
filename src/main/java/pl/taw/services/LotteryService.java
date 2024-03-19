/**
 * Created by tomasz_taw
 * Date: 19.03.2024
 * Time: 10:01
 * Project Name: lottek
 * Description:
 */
package pl.taw.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
public class LotteryService {

    public List<Integer> winningNumbers() {
        List<Integer> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            while (true) {
                int winningNumber = random.nextInt(49) + 1;
                if (!result.contains(winningNumber)) {
                    result.add(winningNumber);
                    break;
                }
            }
        }

        return result;
    }

    public List<Integer> sortedWinningNumbers(List<Integer> numbers) {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        sortedNumbers.sort(Integer::compareTo);

        return sortedNumbers;
    }
}
