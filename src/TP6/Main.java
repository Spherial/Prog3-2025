package TP6;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> billetes = new ArrayList<>(Arrays.asList(5, 1, 10, 20));

        // Valor objetivo a alcanzar
        int objetivo = 47;

        System.out.println(minimoBilletes(billetes,objetivo));



    }
    public static ArrayList<Integer> minimoBilletes(ArrayList<Integer> candidatos, Integer objetivo){
        ArrayList<Integer> solucion = new ArrayList<>();
        while (objetivo != 0){
            Integer candidato = elegirBillete(candidatos,objetivo);

            //Si el metodo anterior devuelve 0 es porque ya no se puede seguir construyendo la solucion
            if (candidato == 0){
                System.out.println("No se ha encontrado solucion");
                return new ArrayList<>();
            }
            solucion.add(candidato);
            objetivo-=candidato;
        }
        return solucion;
    }

    //Se supone que la lista de billetes no esta ordenada
    public static Integer elegirBillete(ArrayList<Integer> candidatos, Integer objetivo){
        int mejorCandidato = 0;
        int anterior = 0;
        for (Integer candidato : candidatos){
            if (candidato <= objetivo && candidato > anterior){
                mejorCandidato = candidato;
                anterior = candidato;
            }
        }
        return mejorCandidato;
    }
}
