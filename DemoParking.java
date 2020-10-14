import java.util.Scanner;

/**
 *  Clase DemoParking para testear la clase Parking
 *  
 *  
 */
public class DemoParking {

    private Parking parking;
    private Scanner teclado;

    /**
     * Constructor de la clase DemoParking
     */
    public DemoParking() {

        teclado = new Scanner(System.in);
        parking = new Parking("Sky");
        parking.setNombre("Sky Park");

    }

    /**
     *   
     * simular varias entradas y salidas del parking
     * en diferentes días y con diferentes tarifas y
     * cálculo del importe a pagar por los clientes
     */
    public void iniciar() {

        char[] tiposTarifa = {'R', 'C', 'R', 'R', 'R', 'C', 'C', 'C', 'R', 'R'};
        int[] horasEntrada = {830, 1235, 415, 935, 1315, 930, 1115, 725, 730, 930};
        int[] horasSalida = { 1607, 1752, 722, 1240, 1650, 1300, 1512, 2235, 1640, 1300};
        int[] dias = { 2, 1, 1, 5, 7, 6, 6, 1, 1, 6, 7};
        System.out.println("Parking: " + parking.getNombre());
        System.out.println();
        simularFacturacionClientes(tiposTarifa, horasEntrada, horasSalida,dias);
        mostrarEstadisticas();

        pausa();

    }

    /**
     * 
     */
    private void simularFacturacionClientes(char[] tiposTarifa, int[] horasEntrada, int[] horasSalida, int[] dias) {

        for (int i = 0; i < horasEntrada.length; i++) {
            parking.facturarCliente(tiposTarifa[i], horasEntrada[i], horasSalida[i], dias[i]);
            System.out.println();
        }

    }

    /**
     * Muestra estadísticas del parking
     */
    private void mostrarEstadisticas() {
        parking.printEstadísticas();
        System.out.println("El día que más clientes usaron el parking fue " +
            parking.diaMayorNumeroClientes());

    }

    /**
     *  hacer una pausa
     */
    private void pausa() {

        System.out.println("\n\nPulse <Intro> para continuar");
        teclado.nextLine();
        System.out.println("\u000C");
    }

}
