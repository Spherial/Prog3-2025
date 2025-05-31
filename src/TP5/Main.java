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


        System.out.println("PROCESADOR Y TAREAS");

        ArrayList<Procesador> procesadores = new ArrayList<>();
        procesadores.add(new Procesador());
        procesadores.add(new Procesador());
        procesadores.add(new Procesador());
        ArrayList<Integer> tareas = new ArrayList<>();
        tareas.add(5);
        tareas.add(2);
        tareas.add(9);
        tareas.add(4);

        ArrayList<Procesador> mejorDistribucion = distribuirTareas(procesadores,tareas);
        System.out.println(mejorDistribucion);


		System.out.println("CONJUNTOS QUE SUMAN IGUAL:");

		ArrayList<Integer> entrada1 = new ArrayList<>(Arrays.asList(1, 5, 11, 5));
		System.out.println(mismaSuma(entrada1));


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

	//EJERCICIO 5
	
	public static ArrayList<Procesador> distribuirTareas(ArrayList<Procesador> procesadores, ArrayList<Integer> tareas){
		ArrayList<Procesador> mejorSolucion = new ArrayList<>();
		ArrayList<Procesador> solucionActual = new ArrayList<>(procesadores);
		backtracking(solucionActual,tareas,0,mejorSolucion);
		return mejorSolucion;
	}
	
	private static void backtracking(ArrayList<Procesador> procesadores, ArrayList<Integer> tareas, Integer indice, ArrayList<Procesador> mejorSolucion) {
        //Si ya asigne todas las tareas, tengo un estado final
		if (indice>= tareas.size()) {
            if (mejorSolucion.isEmpty() || obtenerMayorConsumo(procesadores) < obtenerMayorConsumo(mejorSolucion)){
                mejorSolucion.clear();

                //No puedo hacer un addAll porque los procesadores tienen una estructura compleja
                //Entonces tengo que generar una copia para no modificar la referencia original
                for (Procesador p : procesadores) {
                    mejorSolucion.add(p.getCopia());
                }
            }

		}else {
            //Agrega la tarea actual (a la que apunta el indice) al sig procesador, y propaga la busqueda
			for(Procesador p:procesadores) {
				p.agregarTarea(tareas.get(indice));
				backtracking(procesadores, tareas, indice+1, mejorSolucion);
				p.eliminarTarea(tareas.get(indice));
			}
		}
	}

    //Calcula todos los consumos de los procesadores y devuelve el mayor
	private static Integer obtenerMayorConsumo(ArrayList<Procesador> procesadores) {
		Integer mayorConsumo = Integer.MIN_VALUE;
		for(Procesador p : procesadores) {
			if (p.getConsumoTotal() > mayorConsumo) {
				mayorConsumo = p.getConsumoTotal();
			}
		}
		return mayorConsumo;
	}




	//EJERCICIO 6 (Caballo)

	public static ArrayList<Celda> caballoAtila(Celda[][] matriz, Celda celda1){
		ArrayList<Celda> camino = new ArrayList<>();
		boolean[][] visitado = new boolean[matriz.length][matriz[0].length];
		camino(matriz,celda1, celda1,camino);
		return camino;
	}


	private static void camino(Celda[][] matriz, Celda actual, Celda destino, ArrayList<Celda> camino){
		camino.add(actual);
		//TODO
	}



	//EJERCICIO 4
//	Partición de conjunto. Dado un conjunto de n enteros se desea encontrar, si existe, una partición en
//	dos subconjuntos disjuntos, tal que la suma de sus elementos sea la misma.


	//En este caso solo necesitamos una solucion, (saber si hay o no), entonces podemos usar un boolean

	//No necesitamos comparar soluciones, ni chequear soluciones parciales. Solo interesan estados finales

	public static ArrayList<ArrayList<Integer>> mismaSuma(ArrayList<Integer> entrada){

		ArrayList<ArrayList<Integer>> solucion = new ArrayList<>();
		ArrayList<Integer> conjunto1 = new ArrayList<>();
		ArrayList<Integer> conjunto2 = new ArrayList<>();
		if (mismaSuma(entrada,0,conjunto1,conjunto2,solucion)){
			return solucion;
		}
		return new ArrayList<>();
	}


	//Crea una recursion hasta que se asignen todos los elementos de la entrada, y compara las sumas
	//De los subconjuntos en cada posible estado final
	public static boolean mismaSuma(ArrayList<Integer> entrada,Integer indice, ArrayList<Integer> conjunto1, ArrayList<Integer> conjunto2, ArrayList<ArrayList<Integer>> solucion){

		//Nos aseguramos que estamos parados en un estado final (ya se asignaron todos los numeros)
		if (indice == entrada.size()){
			//Si suman lo mismo, agregamos los 2 conjuntos que cumplen a nuestra solucion
			if (sumaValores(conjunto1) == sumaValores(conjunto2)){
				solucion.add(new ArrayList<>(conjunto1));
				solucion.add(new ArrayList<>(conjunto2));
				return true;
			}
		}

		//Sino, abrimos 2 ramas, una para cuando insertamos el elemento en el conjunto 1, y otra
		//Para cuando lo hacemos en el conjunto 2
		else{
			conjunto1.add(entrada.get(indice));
			if (mismaSuma(entrada,indice+1,conjunto1,conjunto2,solucion)){
				return true;
			}
			conjunto1.remove(conjunto1.size()-1);


			conjunto2.add(entrada.get(indice));
			if (mismaSuma(entrada,indice+1,conjunto1,conjunto2,solucion)){
				return true;
			}
			conjunto2.remove(conjunto2.size()-1);
		}

		//Si es un estado final, pero no coincide ninguna suma, no hay solucion
		return false;
	}

	public static int sumaValores(ArrayList<Integer> numeros){
		int suma = 0;
		for(int numero : numeros){
			suma+=numero;
		}
		return suma;
	}





















}
