package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.ThreadLocalRandom;

@Controller
class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{number}")
    public String userGuess(@PathVariable int number, Model model) {
        int dice = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        model.addAttribute("number", number);
        model.addAttribute("dice", dice);
        return "roll-dice";
    }
}
