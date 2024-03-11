/**
 * Created by tomasz_taw
 * Date: 04.03.2024
 * Time: 23:43
 * Project Name: lottek
 * Description:
 */
package pl.taw.services;

import org.apache.commons.math3.util.CombinatoricsUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import pl.taw.models.LastResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    public long calculateNumberOfCombinationsWithOneError(int n, int k, int m) {
        // Obliczamy kombinacje dla 6 z 49, które są standardowe dla systemu lotto
        long standardCombinations = calculateNumberOfCombinations(n, k);

        // Obliczamy kombinacje dla 7 z 49, które są możliwe z jednym błędem
        long combinationsWithOneError = calculateNumberOfCombinations(n - 1, k);

        // Zwracamy różnicę między kombinacjami standardowymi a kombinacjami z jednym błędem
        return Math.abs(combinationsWithOneError - standardCombinations);
    }

    // wyświetlenie tylko ostatnich liczb
    public String getLastLottoResult() {
        StringBuilder result = new StringBuilder();
        try {
            Document doc = Jsoup.connect("https://www.lotto.pl/lotto/wyniki-i-wygrane?plc=menu-gorne").get();
            Elements elements = doc.select(".scoreline-item.circle");

            for (int i = 0; i < Math.min(6, elements.size()); i++) {
                Element item = elements.get(i);
                result.append(item.text()).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result.toString();
    }

    // zmapowanie ostatniego losowania do rekordu LastResult
    public LastResult parseHtmlToLastResult() {
        try {
            Document doc = Jsoup.connect("https://www.lotto.pl/lotto/wyniki-i-wygrane?plc=menu-gorne").get();

            Elements scorelineItems = doc.select(".scoreline-item.circle");

            // Pobierz pierwszy element
            Element firstItem = scorelineItems.first();
            if (firstItem != null) {

                Element lotteryIdElement = firstItem.selectFirst(".result-item__name");
                String lotteryId = (lotteryIdElement != null) ? lotteryIdElement.text().trim() : "";

                Element dayOfWeekElement = firstItem.select(".resu1lt-item__name").first();
                String dayOfWeek = (dayOfWeekElement != null) ? dayOfWeekElement.text().trim() : "";

                Element dateElement = firstItem.select(".result-item__number").first();
                String date = (dateElement != null) ? dateElement.text().trim() : "";

                List<String> numbers = new ArrayList<>();
                for (Element number : firstItem.select(".scoreline-item.circle")) {
                    numbers.add(number.text().trim());
                }

                return new LastResult(lotteryId, dayOfWeek, date, String.join(" ", numbers));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public String getLastLotteryId() {
        try {
            // Pobierz zawartość strony internetowej
            Document doc = Jsoup.connect("https://www.lotto.pl/lotto/wyniki-i-wygrane?plc=menu-gorne").get();

            // Znajdź elementy zawierające numery losowania
            Elements recentResultItems = doc.select(".recent-result-item");

            // Sprawdź, czy znaleziono elementy
            if (!recentResultItems.isEmpty()) {
                // Pobierz pierwszy element
                Element firstItem = recentResultItems.first();

                // Pobierz ID loterii (Lottery ID)
                assert firstItem != null;
                String lotteryId = Objects.requireNonNull(firstItem.selectFirst(".result-item__number")).text().trim();

                return lotteryId;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Nie udało się pobrać numeru ostatniego losowania";
    }

    public List<String> getLastLotteryDetails() {
        List<String> result = new ArrayList<>();

        try {
            Document doc = Jsoup.connect("https://www.lotto.pl/lotto/wyniki-i-wygrane?plc=menu-gorne").get();
            Elements recentResultItems = doc.select(".recent-result-item");

            if (!recentResultItems.isEmpty()) {
                Element firstItem = recentResultItems.first();

                // Pobierz ID loterii (Lottery ID)
                assert firstItem != null;
                String lotteryId = Objects.requireNonNull(firstItem.selectFirst(".result-item__number")).text().trim();
                result.add(lotteryId);

                String[] fullDate = Objects.requireNonNull(firstItem.selectFirst(".sg__desc-title")).text().split(", ");
                String dayOfTheWeek = fullDate[0];
                result.add(dayOfTheWeek);
                String date = fullDate[1];
                result.add(date);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

}
