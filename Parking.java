/**
 * La clase representa a un parking de una ciudad europea
 * que dispone de dos tarifas de aparcamiento para los clientes
 * que lo usen: la tarifa regular (que incluye una tarifa plana para
 * entradas "tempranas") y la tarifa comercial para clientes que trabajan
 * cerca del parking, aparcan un nº elevado de horas y se benefician de esta 
 * tarifa más económica
 * (leer enunciado)
 * @Author - Ibai Andreu.
 */
public class Parking
{
    //dos constantes que indican la tarifa.
    private final char REGULAR = 'R';
    private final char COMERCIAL = 'C';
    //tres constantes que indican el precio de la tarifa.
    private final double PRECIO_BASE_REGULAR = 2.0;
    private final double PRECIO_MEDIA_REGULAR_HASTA11 = 3.0;
    private final double PRECIO_MEDIA_REGULAR_DESPUES11 = 5.0;
    //cinco constantes que indican la tarifa según hora.
    private final double HORA_INICIO_ENTRADA_TEMPRANA = (6 * 60);
    private final double HORA_FIN_ENTRADA_TEMPRANA = (8 * 60 + 30);
    private final double HORA_INICIO_SALIDA_TEMPRANA = (15 * 60);
    private final double HORA_FIN_SALIDA_TEMPRANA = (18 * 60);
    private final double PRECIO_TARIFA_PLANA_REGULAR = 15.0;
    //dos constantes para definir la tarifa comercial.
    private final double PRECIO_PRIMERAS3_COMERCIAL = 5.00;
    private final double PRECIO_MEDIA_COMERCIAL = 3.00;
    //Atruibutos y variables de instancia.
    private String nombre;
    private int cliente;
    private double importeTotal;
    private int regular;
    private int comercial;
    private int clientesLunes;
    private int clientesSabado;
    private int clientesDomingo;
    private int clienteMaximoComercial;
    private double importeMaximoComercial;

    /**
     * Inicializa el parking con el nombre indicada por el parámetro.
     * El resto de atributos se inicializan a 0 
     */
    public Parking(String queNombre) {
        nombre = queNombre;
        cliente = 0;
        importeTotal = 0;
        regular = 0;
        comercial = 0;
        clientesLunes = 0;
        clientesSabado = 0;
        clientesDomingo = 0;
        clienteMaximoComercial = 0;
        importeMaximoComercial = 0;
    }

    /**
     * accesor para el nombre del parking
     *  
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * mutador para el nombre del parking
     *  
     */
    public void setNombre(String queNombre) {
        nombre = queNombre;
    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    tipoTarifa - un carácter 'R' o 'C'
     *    entrada - hora de entrada al parking
     *    salida – hora de salida del parking
     *    dia – nº de día de la semana (un valor entre 1 y 7)
     *    
     *    A partir de estos parámetros el método debe calcular el importe
     *    a pagar por el cliente y mostrarlo en pantalla 
     *    y  actualizará adecuadamente el resto de atributos
     *    del parking para poder mostrar posteriormente (en otro método) las estadísticas
     *   
     *    Por simplicidad consideraremos que un cliente entra y sale en un mismo día
     *    
     *    (leer enunciado del ejercicio)
     */
    public void facturarCliente(char tipoTarifa, int entrada, int salida, int dia) {
        cliente ++; //Incrementar el cliente automaticamente.
        //Variables locales
        double importe = 0;
        String tipoDeTarifa = "";
        //Variables locales para Horas de entrada a minutos.
        int entradaHoras = entrada / 100;
        int entradaMinutos = entrada % 100;
        int entradaTodoMinutos = entradaHoras * 60 + entradaMinutos;
        //Variables locales para Horas de salida a minutos.
        int salidaHoras = salida / 100;
        int salidaMinutos = salida % 100;
        int salidaTodoMinutos = salidaHoras * 60 + salidaMinutos;
        //La salida menos la entrada
        int tiempoAparcadoMinutos = salidaTodoMinutos - entradaTodoMinutos;
        switch (tipoTarifa){
            case 'R': 
            if((entradaTodoMinutos > HORA_INICIO_ENTRADA_TEMPRANA && entradaTodoMinutos < HORA_FIN_ENTRADA_TEMPRANA) && (salidaTodoMinutos > HORA_INICIO_SALIDA_TEMPRANA && salidaTodoMinutos < HORA_FIN_SALIDA_TEMPRANA)){
                importe += PRECIO_TARIFA_PLANA_REGULAR;
                tipoDeTarifa += "Plana y Regular";
            } 
            else{
                if(entrada < 1100){
                    if (salida > 1100){
                        importe += ((11 * 60 - entradaTodoMinutos) / 30 * PRECIO_MEDIA_REGULAR_HASTA11) + ((salidaTodoMinutos - 11 * 60) / 30 * PRECIO_MEDIA_REGULAR_DESPUES11);
                    }
                    else{
                        importe += tiempoAparcadoMinutos / 30 * PRECIO_MEDIA_REGULAR_HASTA11;
                    }
                }
                else{
                    importe += tiempoAparcadoMinutos / 30 * PRECIO_MEDIA_REGULAR_DESPUES11;
                }
                tipoDeTarifa += "Regular";
            }
            regular ++;//incrementar el numero de clientes en regular.
            break;
            case 'C': 
            if( tiempoAparcadoMinutos < 180){
                importe = PRECIO_PRIMERAS3_COMERCIAL;
                tipoDeTarifa += "Comercial primeras 3 horas";
            }
            else{
                importe = (tiempoAparcadoMinutos - 180) / 30 * PRECIO_MEDIA_COMERCIAL + PRECIO_PRIMERAS3_COMERCIAL;
                tipoDeTarifa += "Comercial primeras 3 más media comercial";
            }
            comercial ++;//incrementar el numero de clientes en comercial.
            break;
        }
        System.out.println("********************************************");
        System.out.println("Cliente nº: " + cliente);
        System.out.println("Hora de entrada: " + entradaHoras + ":" + entradaMinutos);
        System.out.println("Hora de salida: " + salidaHoras + ":" + salidaMinutos);
        System.out.println("Tarifa a aplicar: " + tipoDeTarifa);
        System.out.println("Importe a pagar: " + importe + "€");
        System.out.println("********************************************");
        //Para saber el total de todos los clientes.
        importeTotal = importeTotal + importe;
        //Para saber el cliente que más a pagado.
        if (importeMaximoComercial < importe){
            importeMaximoComercial = importe;
            clienteMaximoComercial = cliente;
        }
        //Para saber el día que más se ha usado el parking.
        switch(dia){
            case 1:  clientesLunes ++;
            break;
            case 6:  clientesSabado ++;
            break;
            case 7:  clientesDomingo ++;
            break;
        }
    }

    /**
     * Muestra en pantalla las estadísticcas sobre el parking  
     *   
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println("********************************************");
        System.out.println("importe total entre todos los clientes: " + importeTotal);
        System.out.println("Nº de clientes tarifa regular: " + regular);
        System.out.println("Nº de clientes tarifa regular: " + comercial);
        System.out.println("Cliente tarifa: comercial con factura máxima fue el nº " + clienteMaximoComercial + " y pagó " + importeMaximoComercial + "€");
        System.out.println("********************************************");
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que más clientes han utilizado el parking - "SÁBADO"   "DOMINGO" o  "LUNES"
     */
    public String diaMayorNumeroClientes() {
        int NumeroDeClientes = 0;
        String diaMasClientes ="";
        if (NumeroDeClientes < clientesLunes){
            diaMasClientes ="Lunes";
            NumeroDeClientes = clientesLunes;
        }
        else if(NumeroDeClientes < clientesSabado){
            diaMasClientes ="Sabado";
            NumeroDeClientes = clientesSabado;
        }
        else if(NumeroDeClientes < clientesDomingo){
            diaMasClientes ="Domingo";
            NumeroDeClientes = clientesDomingo;
        }
        return diaMasClientes;
    }
}