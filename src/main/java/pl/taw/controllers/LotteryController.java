/**
 * Created by tomasz_taw
 * Date: 19.03.2024
 * Time: 09:59
 * Project Name: lottek
 * Description:
 */
package pl.taw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.taw.models.NumbersForm;
import pl.taw.services.LotteryService;

import java.util.Arrays;
import java.util.List;

@Controller
public class LotteryController {

    private final LotteryService lotteryService;

    @Autowired
    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("/lottery")
    public String lottery(Model model) {
        List<Integer> winningNumbers = lotteryService.winningNumbers();
        model.addAttribute("winningNumbers", winningNumbers);

        List<Integer> sortedWinningNumbers = lotteryService.sortedWinningNumbers(winningNumbers);
        model.addAttribute("sortedWinningNumbers", sortedWinningNumbers);

        model.addAttribute("numbersForm", new NumbersForm());

        return "lotteryView";
    }
//
//    @PostMapping("/lottery")
//    public String processLotteryForm(@ModelAttribute("numbersForm") NumbersForm numbersForm, Model model) {
//        List<Integer> numbers = numbersForm.getNumbers();
//
//        return "redirect:/result";
//    }
    @PostMapping("/lottery")
    public String processLotteryForm(@RequestParam("number1") int number1,
                                     @RequestParam("number2") int number2,
                                     @RequestParam("number3") int number3,
                                     @RequestParam("number4") int number4,
                                     @RequestParam("number5") int number5,
                                     @RequestParam("number6") int number6,
                                     Model model) {
        List<Integer> numbers = Arrays.asList(number1, number2, number3, number4, number5, number6);
        // Tutaj możesz przetwarzać wprowadzone liczby
        return "redirect:/results";
    }
}
