package TP2;

public class Main {



    //Compara un elemento con su siguiente, y se llama a si mismo con el siguiente elemento, hasta
    //la anteultima posicion

    public static boolean estaOrdenado(int[] arr, int posicion){
        //Si llegue a la ultima posicion, es porque el anteultimo elemento es mas chico, y por lo tanto
        //los anteriores tambien, asi que esta ordenado
        if (posicion == arr.length-1){
            return true;
        }

        //Si se rompe el orden, devuelve false
        if (arr[posicion] > arr[posicion+1]){
            return false;
        }

        //Propaga la busqueda al siguiente elemento
        return estaOrdenado(arr, posicion + 1);
    }
    //Complejidad: O(n) porque visito 1 vez cada elemento

    //Hacerlo recursivo apila muchos llamados a la ejecución, aumentando el costo de memoria

    //Si fuera una lista, en lugar de acceder a los índices directamente (el acceso aleatorio es caro), habría
    //que avanzar al siguiente nodo y repetir la búsqueda




    //Sabiendo que el array está ordenado, podemos hacer una búsqueda binaria para aumentar mucho la eficiencia
    public static boolean busquedaBinaria(int[] arr, int inicio, int fin, int elemento){

        //No puedo partir mas el array a la mitad, no hay mas elementos para buscar, entonces no lo encontré
        if (inicio > fin){
            return false;
        }

        //Calculo la mitad del array (o del sub-array)
        int medio = (inicio + fin) / 2;

        //Si encontré el elemento en dicha mitad, lo devuelvo
        if (arr[medio] == elemento){
            return true;
        }

        //Si no lo encontré, propago la búsqueda a la mitad izquierda y a la mitad derecha

        //Propagar izquierda (desde el inicio hasta el medio)
        if (arr[medio] > elemento){
            return busquedaBinaria(arr, inicio, medio-1, elemento);
        }
        //Propagar derecha (desde el medio hasta el fin)
        return busquedaBinaria(arr, medio+1, fin,elemento);

    }
    //Complejidad: O(LogN)



    public static String convertirABinario(int numero) {

        //Si es el ultimo digito binario, lo devuelvo
        if (numero < 2) {
            return String.valueOf(numero);
        }
        //Sino, devuelvo los dígitos obtenidos hasta ahora, junto con el de este step
        else {
            return convertirABinario(numero / 2) + (numero % 2);
        }
    }


    //Bubble sort basico, sin optimizaciones
    public static void bubbleSort(int[] arr){
        for (int i = 0; i < arr.length-1;i++){
            for(int j=0; j < arr.length-1-i;j++){
                if (arr[j] > arr[j+1]){
                    int tmp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    public static void imprimirArray(int[] arr){
        for (int i = 0; i < arr.length;i++){
            System.out.println("ARR[" + i + "] = " + arr[i]);
        }
    }




    public static boolean valorIgualPosicion(int[] arr, int pos){
        //Si coincide el valor con la posicion, retorno true
        if (arr[pos] == pos){
            return true;
        }
        //Si llegue al final del arreglo y todavia no lo encontre, retorno false
        if (pos == arr.length-1){
            return false;
        }

        //Propago la busqueda a la siguiente posicion
        return valorIgualPosicion(arr,pos+1);

    }


    public static void fibonacci(int terminos,int contador,int num1,int num2){
        if (contador < terminos){
            System.out.println(num1);

            fibonacci(terminos,contador+1,num2,num1+num2);
        }
    }






    public static void main(String[] args) {

        //Ejercicio 1
        int[] ordenado = {1, 2, 3, 4, 5};
        int[] desordenado = {1, 3, 2, 4, 5};
        int[] random = {3,8,7,3,4,8,3,7,2,7,8,5};
        int[] valoresIguales = {-3,-1,0,2,4,6,10};
        System.out.println(estaOrdenado(ordenado, 0));
        System.out.println(estaOrdenado(desordenado, 0));

        //Ejercicio 2
        System.out.println("Existe el elemento 4? : " + busquedaBinaria(ordenado,0,ordenado.length-1,4));
        System.out.println("Existe el elemento 8? : " + busquedaBinaria(ordenado,0,ordenado.length-1,8));

        //Ejercicio 3
        System.out.println(convertirABinario(26));


        //Ejercicio 4
        fibonacci(6,0,0,1);


        //Ejercicio 5
        System.out.println(valorIgualPosicion(valoresIguales,0));


        //Ejercicio 6
        System.out.println("BUBBLE SORT");
        System.out.println("ANTES DE ORDENAR");
        imprimirArray(random);
        bubbleSort(random);
        System.out.println("DESPUES DE ORDENAR");
        imprimirArray(random);

    }
}
