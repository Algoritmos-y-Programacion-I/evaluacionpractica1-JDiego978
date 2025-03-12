package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las "+precios.length+" referencias vendidas en el dia, "+consultarReferenciasSobreLimite(limite)+" superaron el limite minimo de ventas de "+limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    System.out.println("\nOpcion invalida, intenta nuevamente.");
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];

    }

    /** 
     * Decripcion: Es un metodo que se encarga de preguntar el precio y la cantidad de unidades vendidas por un
     * producto, va en orden primero pregunta por el producto 1, precio | valor, y asi continua hasta la cantidad de
     * productos vendidos
    */
    public static void solicitarDatos(){
        for (int i=0;i < unidades.length;i++){
            System.out.println("Ingresa el precio del producto: "+(i+1));
            precios[i] = reader.nextDouble();
            System.out.println("Ingresa la cantidad de unidades vendidas del producto: "+(i+1));
            unidades[i] = reader.nextInt();
        }
    }

    /** 
     * Decripcion: Este metodo se encarga de calcular la cantidad total de unidades vendidas, esto mediante un ciclo que
     * recorre cada cantidad y la va sumando en una variable
     * 
     * @return int totalUniVendi: Es un entero que devuelve el total de las unidades vendidas
    */
    public static int calcularTotalUnidadesVendidas(){
        int totalUniVendi = 0;
        for (int i=0;i < unidades.length;i++){
            totalUniVendi += unidades[i];
        }
        return totalUniVendi;
    }

    /** 
     * Decripcion: Se encarga de tomar el total del dinero recaudado, y lo divide por la cantidad de productos que existen
     * esto para poder sacar el precio promedio
     * 
     * @return double promedio: Es un double que devuelve el precio promedio
    */
    public static double calcularPrecioPromedio(){
        double totalPrecio = calcularVentasTotales();
        double promedio = totalPrecio / precios.length;
        return promedio;
    }

    /** 
     * Decripcion: Se encarga de multiplicar el precio unitario de cada producto con las unidades tambien correspondientes
     * y este valor lo va guardando y sumando en una variable
     * 
     * @return double totalPrecio: Se encarga de devolver los ingresos, dadas las ventas
    */
    public static double calcularVentasTotales(){
        double totalPrecio = 0;
        for (int i = 0;i < precios.length;i++){
            totalPrecio += precios[i] * unidades[i];
        }
        return totalPrecio;
    }

    /** 
     * Decripcion: Se encarga de calcular cuantos productos superaron un limite minimo dado por el cliente
     * esto mediante un ciclo donde guarda en una variable temporal el ingreso de cada producto (precio unitario * cantidad vendida)
     * y esta variable es comparada con el limite, si es mayor se suma 1 en otra variable que son las que superan el limite
     * @param limite double Es el limite dado por el usuario para comparar
     * @return int superLimite: Devuelve cuantos productos superaron el limite del precio dado por el usuario
    */
    public static int consultarReferenciasSobreLimite(double limite){
        int superLimite = 0;
        for (int i=0;i < precios.length;i++){
            double precioTemporal = precios[i] * unidades[i];
            if (precioTemporal > limite){
                superLimite += 1;
            }
        }
        return superLimite;
    }

}
