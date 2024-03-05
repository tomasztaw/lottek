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
}
