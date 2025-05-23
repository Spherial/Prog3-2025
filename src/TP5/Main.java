package TP5;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import TP4.*;
public class Main {
	public static void main(String[] args) {
		ArrayList<Integer> entrada = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		int target = 5;

		System.out.println(sumaIgualA(entrada,target));

	}

	//EJERCICIO 3
	//Suma de subconjuntos. Dados n números positivos distintos, se desea encontrar todas las
	//combinaciones de esos números tal que la suma sea igual a M.

	//Entonces, dada una lista de numeros y un target, se devolvera una lista de listas, donde cada una de
	//las sublistas sera una combinacion de numeros cuya sume sea igual al target

	public static ArrayList<ArrayList<Integer>> sumaIgualA(ArrayList<Integer> entrada, Integer target){
		ArrayList<ArrayList<Integer>> resultado = new ArrayList<>();
		ArrayList<Integer> solucionParcial = new ArrayList<>();
		sumaIgualA(entrada,0,target,0,solucionParcial,resultado);
		return resultado;
	}


	//Para este ej se cambia un poco el enfoque del backtracking, por lo general primero se agregan cosas
	// a la solucion parcial y LUEGO se decide que hacer.
	//Pero en este ejercicio, primero SE DECIDE si agregar, separando las deciciones de "AGREGAR/NO AGREGAR"
	//En sus propias busquedas

	//Entonces, primero compruebo si ya es solucion, y despues lanzo dos busquedas diferentes, contemplando
	//ambas posibilidades
	public static void sumaIgualA(ArrayList<Integer> entrada,Integer indice, Integer target,Integer sumaActual, ArrayList<Integer> solucionParcial, ArrayList<ArrayList<Integer>> resultado){

		//Si la suma es igual al target, entonces el conjunto actual es una solucion valida
		if (sumaActual.equals(target)){
			resultado.add(new ArrayList<>(solucionParcial));	//Agrego esta solucion a la lista de soluciones
			return; //Corto la recursion porque si sigo sumando romperia la solucion
		}

		//Freno si me paso del array
		//En este caso, puedo podar si la suma ya es mas grande que el target
		if (sumaActual > target || indice >= entrada.size()) {
			return;
		}

		//RAMA 1: AGREGO EL ELEMENTO SIGUIENTE

		//Tenemos una variable "indice" para saber cual elemento del conjunto estamos analizando
		//Como todavia no alcanzamos una solucion valida, agregamos el numero actual a la suma
		//y aumentamos el indice para seguir buscando por el siguiente

		solucionParcial.add(entrada.get(indice)); //Agrega el actual

		//Se propaga la busqueda en el siguiente indice, y le sumamos el actual a la suma Actual
		sumaIgualA(entrada, indice + 1, target, sumaActual + entrada.get(indice), solucionParcial, resultado);


		solucionParcial.remove(solucionParcial.size()-1);


		//RAMA 2: NO AGREGO EL ELEMENTO SIGUIENTE

		//En esta rama, nunca sumo el actual a la suma total, directamente paso al siguiente numero
		sumaIgualA(entrada, indice + 1, target, sumaActual, solucionParcial, resultado);


		//Como en esta rama nunca se incluyo el siguiente indice, no necesito hacer otro remove


	}





















	//EJERCICIO 2
	public static ArrayList<Integer> caminoMasLargo(GrafoDirigido<Integer> grafo, Integer entrada, Integer salida){
		
		ArrayList<Integer> caminoMasLargo = new ArrayList<>();
		ArrayList<Integer> caminoActual = new ArrayList<>();
		caminoMasLargo(grafo,entrada,salida,caminoActual,caminoMasLargo);
		return caminoMasLargo;
		
		
	}
	
	private static void caminoMasLargo(GrafoDirigido<Integer> grafo, Integer actual, Integer salida, ArrayList<Integer> caminoActual, ArrayList<Integer> caminoMasLargo) {
		caminoActual.add(actual);
		if (actual.equals(salida)) {
			if(caminoActual.size() > caminoMasLargo.size()) {
				caminoMasLargo.clear();
				caminoMasLargo.addAll(caminoActual);
			}
		}
		else {
			Iterator<Integer> it = grafo.obtenerAdyacentes(actual);
            while (it.hasNext()) {
                Integer vecino = it.next();
                if (!caminoActual.contains(vecino)) {
                    caminoMasLargo(grafo, vecino, salida, caminoActual, caminoMasLargo);
                }
            }
		}
		caminoActual.remove(caminoActual.get(caminoActual.size()-1));
	}

	public static ArrayList<Celda> caminoEntreCasillas(Celda[][] matriz, Celda celda1, Celda celda2){
		ArrayList<Celda> mejorCamino = new ArrayList<>();
		ArrayList<Celda> caminoActual = new ArrayList<>();
		caminoEntreCasillas(matriz, celda1, celda2, caminoActual, mejorCamino);
		return mejorCamino;
	}

	public static void caminoEntreCasillas(Celda[][] matriz, Celda actual, Celda celda2, ArrayList<Celda> caminoActual, ArrayList<Celda> mejorCamino){
		caminoActual.add(actual);
		//Pregunto si es una solucion (si llegue a celda2)
		if (actual.equals(celda2)){
			//Comparo mi solucion actual con la mejor solucion
			int longitudActual = calcularValorCamino(caminoActual);

			int mejorLongitud = mejorCamino.isEmpty() ? Integer.MAX_VALUE : calcularValorCamino(mejorCamino);


			//Si la longitud del camino actual es menor que la longitud del mejor camino, actualizo
			if (longitudActual < mejorLongitud){
				mejorCamino.clear();
				mejorCamino.addAll(caminoActual);
			}

		}
		else{
			//Si aun no llegue, propago la busqueda a mis 4 direcciones adyacentes (si se puede)

			//Si puedo moverme a la izquierda, y no me paso de la matriz
			if (actual.getIzq() && actual.getColumna() > 0){
				Celda vecina = matriz[actual.getFila()][actual.getColumna()-1];
				//Si no explore ya esta casilla
				if (!caminoActual.contains(vecina)){
					caminoEntreCasillas(matriz,vecina, celda2, caminoActual,mejorCamino);
				}
			}

			//Hago lo mismo con las demas direcciones

			//DERECHA
			if (actual.getDer() && actual.getColumna() < matriz[0].length-1){
				Celda vecina = matriz[actual.getFila()][actual.getColumna()+1];
				if (!caminoActual.contains(vecina)){
					caminoEntreCasillas(matriz,vecina, celda2, caminoActual,mejorCamino);
				}
			}

			//ARRIBA
			if (actual.getArriba() && actual.getFila() > 0){
				Celda vecina = matriz[actual.getFila()-1][actual.getColumna()];
				if (!caminoActual.contains(vecina)){
					caminoEntreCasillas(matriz,vecina, celda2, caminoActual,mejorCamino);
				}
			}

			//ABAJO
			if (actual.getAbajo() && actual.getFila() < matriz.length-1){
				Celda vecina = matriz[actual.getFila()+1][actual.getColumna()];
				if (!caminoActual.contains(vecina)){
					caminoEntreCasillas(matriz,vecina, celda2, caminoActual,mejorCamino);
				}
			}

			caminoActual.remove(caminoActual.size()-1);
		}
	}

	public static int calcularValorCamino(ArrayList<Celda> camino){
		int suma = 0;
		for (Celda celda : camino){
			suma+=celda.getValor();
		}
		return suma;
	}

}
