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
}
