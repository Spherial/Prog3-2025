package TP6;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        System.out.println("MINIMIZAR CANTIDAD DE BILLETES");
        ArrayList<Integer> billetes = new ArrayList<>(Arrays.asList(5, 1, 10, 20));

        // Valor objetivo a alcanzar
        int objetivo = 47;

        System.out.println(minimoBilletes(billetes,objetivo));

        System.out.println("MAXIMIZAR ACTIVIDADES");
        ArrayList<Actividad> actividades = new ArrayList<>();
        actividades.add(new Actividad(0,1));
        actividades.add(new Actividad(0,3));
        actividades.add(new Actividad(2,4));
        actividades.add(new Actividad(1,5));
        actividades.add(new Actividad(3,6));
        System.out.println(maximizarActividades(actividades));

    }

    //EJERCICIO 1

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


    //EJERCICIO 3

    public static ArrayList<Actividad> maximizarActividades(ArrayList<Actividad> actividades){
        ArrayList<Actividad> solucion = new ArrayList<>();


        while(!actividades.isEmpty()){
            //Elegimos la actividad que termine antes
            Actividad candidato = seleccionarActividad(actividades);
            actividades.remove(candidato);  //La quitamos de la lista de actividades totales

            //Si no se solapa con la ultima actividad que vamos a hacer, la agregamos a la solucion
            if (solucion.isEmpty() || candidato.getTiempoInicio() >= solucion.getLast().getTiempoFin()){
                solucion.add(candidato);
            }

        }

        return solucion;



    }


    //El criterio para elegir el mejor candidato, sera elegir la actividad cuyo tiempo de finalizacion
    //sea antes
    public static Actividad seleccionarActividad(ArrayList<Actividad> actividades){
        Actividad mejorCandidato = null;
        int tiempoMenor = Integer.MAX_VALUE;
        for (Actividad actividad : actividades){
            if (actividad.getTiempoFin() < tiempoMenor){
                mejorCandidato = actividad;
                tiempoMenor = actividad.getTiempoFin();
            }
        }
        return mejorCandidato;
    }





}
