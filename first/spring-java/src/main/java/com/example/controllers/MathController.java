package com.example.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.converters.NumberConverter;
import com.example.excpetions.UnsopportedMathException;
import com.example.math.SimpleMath;

@RestController("MathController")
public class MathController {

    private SimpleMath math = new SimpleMath();

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsopportedMathException("Please set a numeric value");
        }

        return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @GetMapping(value = "/sub/{numberOne}/{numberTwo}")
    public Double sub(@PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsopportedMathException("Please set a numeric value");
        }

        return math.sub(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @GetMapping(value = "/mul/{numberOne}/{numberTwo}")
    public Double mul(@PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsopportedMathException("Please set a numeric value");
        }

        return math.mul(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @GetMapping(value = "/div/{numberOne}/{numberTwo}")
    public Double div(@PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsopportedMathException("Please set a numeric value");
        }

        return math.div(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @GetMapping(value = "/med/{numberOne}/{numberTwo}")
    public Double med(@PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsopportedMathException("Please set a numeric value");
        }

        return math.med(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @GetMapping(value = "/square/{number}")
    public Double square(@PathVariable(value = "number") String number) throws Exception {

        if (!NumberConverter.isNumeric(number)) {
            throw new UnsopportedMathException("Please set a numeric value");
        }

        return math.square(NumberConverter.convertToDouble(number));
    }
}
