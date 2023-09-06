package br.edu.ufcg.computacao.psoft.commerce.service.produto;

import java.util.ArrayList;
import java.util.List;

public class CodigoDeBarrasService {

    public static Boolean verificarPadrao(String codigo){
        return codigo.startsWith("78999999");
    }

    public static Integer getVerificador(String codigo){
        List<Integer> numeros = new ArrayList<>();
        for (Character c : codigo.toCharArray()) {
            numeros.add(Character.getNumericValue(c));
        }
        Integer[] somas = {0,0};
        for (Integer i = 0; i < numeros.size(); i++) {
            somas[i%2]+=numeros.get(i);
        }
        return 10-(somas[0]+(3*somas[1]))%10;
    }
}
