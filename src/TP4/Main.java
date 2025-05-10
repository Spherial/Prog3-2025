package TP4;

public class Main {
    public static void main(String[] args) {
        GrafoDirigido<Integer> miGrafo = new GrafoDirigido<>();
        miGrafo.agregarVertice(1);
        miGrafo.agregarVertice(2);
        miGrafo.agregarVertice(3);
        miGrafo.agregarVertice(4);

        miGrafo.agregarArco(1,2,0);
        miGrafo.agregarArco(2,4,0);
        miGrafo.agregarArco(4,1,0);
        miGrafo.agregarArco(1,3,0);


        DFS<Integer> dfs = new DFS<>(miGrafo);
        dfs.DFS();
        System.out.println(dfs.caminoMasLargo(1,4));
    }
}
