package TP5;

import java.util.ArrayList;

public class Procesador {

	private ArrayList<Integer> tareas;
	
	public Procesador() {
		this.tareas = new ArrayList<>();
		
	}
	
	public void agregarTarea(Integer nuevaTarea) {
		this.tareas.add(nuevaTarea);
	}
	
	public Integer getConsumoTotal() {
		Integer suma = 0;
		for(Integer tarea : this.tareas) {
			suma += tarea;
		}
		return suma;
	}
	
	public void eliminarTarea(Integer indice) {
		this.tareas.remove(indice);
	}
	
}
