/**
 * Created by tomasz_taw
 * Date: 04.03.2024
 * Time: 23:42
 * Project Name: lottek
 * Description:
 */
package pl.taw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.taw.models.PlayerTicker;
import pl.taw.services.LottoService;
import pl.taw.utils.MathUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@Controller
public class LottoController {

    private final LottoService
            lottoService;

    @Autowired
    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    @GetMapping("/lotto")
    public String showLottoForm() {
        return "lottoForm";
    }

    @PostMapping("/lotto")
    public String calculateProbability(@RequestParam("numbers") List<Integer> numbers, Model model) {
        List<Integer> winningNumbers = lottoService.generateRandomNumbers(6);
        PlayerTicker ticket = new PlayerTicker(numbers);
        double probability = lottoService.calculateWinningProbability(ticket.getChosenNumbers(), winningNumbers);
        model.addAttribute("winningNumbers", winningNumbers);
        model.addAttribute("probability", probability);
        return "lottoResult";
    }

    @GetMapping("/combinations")
    public String getNumberOfCombinations2(Model model) {
        int n = 49; // liczba wszystkich dostępnych liczb
        int k = 6; // liczba liczb, które musisz wybrać

        long numberOfCombinations = lottoService.calculateNumberOfCombinations(n, k);
        model.addAttribute("numberOfCombinations", numberOfCombinations);

        return "combinationsView";
    }


    @GetMapping("/system")
    public String resultForSystem(Model model) {
        int totalNumbers = 49;
        int selectedNumbers = 12;
        int drawnNumbers = 6;

        double result = lottoService.calculateProbability(totalNumbers, selectedNumbers, drawnNumbers);
        model.addAttribute("result", result);

        int percent = (int) result * 100;
        model.addAttribute("percent", percent);

        return "resultSystem";
    }

    @GetMapping("/probability")
    public String calculateProbability(Model model) {
        int totalNumbers = 49;
        int numbersToChoose = 6;
        int numbersToSelectFrom = 12;

        BigInteger combinationsOfChosenNumbers = MathUtils.calculateCombination(numbersToSelectFrom, numbersToChoose);
        BigInteger totalCombinations = MathUtils.calculateCombination(totalNumbers, numbersToChoose);

        double probability = combinationsOfChosenNumbers.doubleValue() / totalCombinations.doubleValue();

        BigDecimal roundedProbability = BigDecimal.valueOf(probability).setScale(5, RoundingMode.HALF_UP);

        BigDecimal probabilityNext = new BigDecimal(combinationsOfChosenNumbers)
                .divide(new BigDecimal(totalCombinations), MathContext.DECIMAL128)
                .multiply(BigDecimal.valueOf(100));


        model.addAttribute("combinationsOfChosenNumbers", combinationsOfChosenNumbers);
        model.addAttribute("totalCombinations", totalCombinations);
        model.addAttribute("probability", probability);
        model.addAttribute("roundedProbability", roundedProbability);

        model.addAttribute("prob", probabilityNext);

        return "probabilityView";
    }
}
