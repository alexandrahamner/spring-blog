package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
class MathController {
    @RequestMapping(path = "/add/{number1}/and/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String add(@PathVariable int number1, @PathVariable int number2) {
        return number1 + " plus " + number2 + " equals " + (number1+number2) + "!";
    }

    @RequestMapping(path = "/subtract/{number1}/from/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String subtract(@PathVariable int number1, @PathVariable int number2) {
        return number1 + " minus " + number2 + " equals " + (number1-number2) + "!";
    }

    @RequestMapping(path = "/multiply/{number1}/and/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String multiply(@PathVariable int number1, @PathVariable int number2) {
        return number1 + " multiplied by " + number2 + " equals " + (number1*number2) + "!";
    }

    @RequestMapping(path = "/divide/{number1}/and/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String divide(@PathVariable int number1, @PathVariable int number2) {
        return number1 + " divided by " + number2 + " equals " + (number1/number2) + "!";
    }
}


