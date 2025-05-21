package TP5;
import java.util.ArrayList;
import java.util.Iterator;

import TP4.*;
public class Main {
	public static void main(String[] args) {
		
		
	}
	
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
