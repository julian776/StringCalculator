package calculator;

import java.util.Arrays;
import java.util.Objects;

import static java.lang.Float.parseFloat;

public class StringCalculator {

    public Float resultFromListString(String lista){
        Objects.requireNonNull(lista, "List can not be null");

        if(lista.isEmpty()) {
            return 0F;
        }
        if(lista.split("").length == 1 && (parseFloat(lista) > 0 && parseFloat(lista) < 1001)){
            return parseFloat(lista);
        }
        //Complex Calculations
        var delimiter = getDelimiterAndList(lista);
        String [] splitLista;
        if (delimiter == null){
            splitLista = lista.split("[,\n]");
            return delimiterOrFirstLine(splitLista);
        }
        else if(delimiter[0].length() == 1){
            splitLista = delimiter[1].split(delimiter[0]);
            return delimiterOrFirstLine(splitLista);
        }
        else {
            return calculateanyCombination(delimiter[1]);
        }

    }

    private boolean validateElement(String element){
        try{
            if (parseFloat(element) > 1000){
                return false;
            }
        }catch (Exception e){
            return false;
        }
        if (parseFloat(element) < 0){
            throw new IllegalArgumentException("Negative number found");
        }
        return true;
    }

    private String [] getDelimiterAndList(String lista){
        //Also validates if no delimiter to work with
        String [] splitLista = lista.split("[,\n]");
        var delimiter = new String [2];
        try{
            parseFloat(splitLista[0]);
            return null;
        }catch (Exception e){
            if (splitLista.length == 2 && splitLista[0].length() == 1){
                delimiter[0] = splitLista[0];
                delimiter[1] = splitLista[1];
                return delimiter;
            }
        }
        delimiter[0] = "not found";
        delimiter[1] = lista;
        return delimiter;
    }

    private Float delimiterOrFirstLine(String [] splitLista){
        return Arrays.stream(splitLista)
                .filter(this::validateElement)
                .map(element -> parseFloat(element))
                .reduce((val1, val2) ->  val1 + val2).orElse(0F);
    }

    private Float calculateanyCombination(String lista) {
        var tempNum = "0";
        var total = 0F;
        var splitLista = lista.split("");
        for (int i = 0; i < splitLista.length; i++) {
            if (splitLista[i].matches("[0-9]")){
                tempNum += splitLista[i];
            }
            else{
                if (parseFloat(tempNum) < 1000){
                    total += parseFloat(tempNum);
                }
                tempNum = "0";
            }
        }
        if (parseFloat(tempNum) > 1000){
            tempNum = "0";
        }
        return total += parseFloat(tempNum);
    }
}
