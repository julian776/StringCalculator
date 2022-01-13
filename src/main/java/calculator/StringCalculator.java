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
        }
        else if(delimiter[0].length() == 1){
            splitLista = delimiter[1].split(delimiter[0]);
        }
        else {
            splitLista = lista.split("[,\n]");
        }
        return Arrays.stream(splitLista)
                .filter(this::validateElement)
                    .map(element -> parseFloat(element))
                    .reduce((val1, val2) ->  val1 + val2).orElse(0F);
    }

    public boolean validateElement(String element){
        try{
            parseFloat(element);
        }catch (Exception e){
            return false;
        }
        if (parseFloat(element) < 0){
            throw new IllegalArgumentException("Negative number found");
        }
        return true;
    }

    public String [] getDelimiterAndList(String lista){
        //Also validates if no delimiter to work with
        String [] splitLista = lista.split("[,\n]");
        try{
            parseFloat(splitLista[0]);
            return null;
        }catch (Exception e){
            if (splitLista.length == 2 && splitLista[0].length() == 1){
                var delimiter = new String [2];
                delimiter[0] = splitLista[0];
                delimiter[1] = splitLista[1];
                return delimiter;
            }
            for (String value : splitLista) {

            }
        }
        return null;
    }
}
