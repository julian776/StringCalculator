package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static java.lang.Float.isFinite;
import static java.lang.Float.parseFloat;

public class StringCalculator {

    public Float resultByList(String lista){
        Objects.requireNonNull(lista, "List can not be null");

        if(lista.isEmpty()) {
            return 0F;
        }
        if(lista.length() == 1 && lista.matches("[0-9]")){
            return parseFloat(lista);
        }
        //Complex Calculations
        var delimiter = getDelimiters(lista);
        String [] splitLista;
        if (delimiter == null){
            splitLista = lista.split("[,\n]");
        }
        else{
            splitLista = lista.split(delimiter);
        }
        return Arrays.stream(splitLista).filter(element ->  {
                    try{
                        parseFloat(element);
                    }catch (Exception e){
                        return false;
                    }
                    return true;
                    })
                    .map(element -> parseFloat(element))
                    .reduce((val1, val2) ->  val1 + val2).orElse(0F);
    }

    public String getDelimiters(String lista){
        String [] splitLista;
        if (lista.matches("\n")){
            splitLista = lista.split("\n");
        }
        else {
            splitLista = lista.split(",");
        }
        for (String value: splitLista){
            try{
                parseFloat(value);
                if(parseFloat(value) < 1001F){
                    return null;
                }
            }catch (Exception e){
                return value;
            }
        }
        return null;
    }
}
