package br.com.felipe034sato.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math") //Sera usados para todos os metodos dessa classe

public class MathCOntroller {


    @RequestMapping("/sum/{firstNumber}/{secondNumber}")
    public Double sum(
            @PathVariable("firstNumber") String firstNumber, // Recuperar o valor via path
            @PathVariable("secondNumber") String secondNumber) {

        if(!isNumeric(firstNumber) || !isNumeric(secondNumber)) throw new UnsupportedOperationException("Set a numeric value");
        return convertToDouble(firstNumber) + convertToDouble(secondNumber);
    }

    private Double convertToDouble(String strNumber) {
        if(strNumber == null || strNumber.isEmpty()) throw new UnsupportedOperationException("Set a numeric value"); //Cada metodo deve ser seguro sozinho
        String number =strNumber.replaceAll(",", ".");
        return Double.parseDouble(number);
    }

    public boolean isNumeric(String strNumber) {
        if(strNumber == null || strNumber.isEmpty()) return false;
        String number = strNumber.replace(",","."); //valor bruto X valor tratado
        return (number.matches("[-+]?[0-9]*\\.?[0-9]+")); //regex ja faz a validação de true or false
    }
}
