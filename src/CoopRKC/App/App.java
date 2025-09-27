package CoopRKC.App;

import CoopRKC.Modelo.Cooperativa;
import CoopRKC.Modelo.Cuenta;
import CoopRKC.Modelo.CuentaAhorros;
import CoopRKC.Modelo.Socio;
import CoopRKC.Transacciones.Deposito;
import CoopRKC.Transacciones.Retiro;
import CoopRKC.Transacciones.Transaccion;

import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("--- Iniciando Prototipo CoopRKC ---\n");

        //Creación de la Cooperativa
        Cooperativa coopRKC = new Cooperativa("CoopRKC");

        //Creacion de Asociados y apertura de cuentas
        Socio socio1 = new Socio("Santiago Valencia Hernandez", "1028098456");
        coopRKC.registrarSocio(socio1); // Primero se registra el socio
        socio1.agregarCuenta(new CuentaAhorros("CA-456-789-455", 1000000, 2.5)); // Luego se agrega la cuenta

        Socio socio2 = new Socio("Francisco Javier Florez Ramirez", "1028098457");
        coopRKC.registrarSocio(socio2);
        socio2.agregarCuenta(new CuentaAhorros("CA-455-567-498", 450000, 3.0));
        socio2.agregarCuenta(new CuentaAhorros("CA-455-567-499", 50000, 2.0));

        Socio socio3 = new Socio("Ramiro Antonio Giraldo Escobar", "394050607");
        coopRKC.registrarSocio(socio3);
        socio3.agregarCuenta(new CuentaAhorros("CA-441-675-678", 750000, 2.8));
        socio3.agregarCuenta(new CuentaAhorros("CA-441-675-679", 550000, 2.5));
        socio3.agregarCuenta(new CuentaAhorros("CA-441-675-680", 75000, 2.1));


        //Un socio puede tener múltiples cuentas pero una cuenta pertenece a un solo socio
        //Validación para no permitir números de cuenta repetidos para un mismo socio
        System.out.println("\nValidación de duplicidad de cuenta para un mismo Asociado.");
        socio2.agregarCuenta(new CuentaAhorros("CA-455-567-498", 300000, 3.0));


        //Realizar operaciones de depósito y retiro
        System.out.println("\nRealizando Transacciones");
        Cuenta cuentaSocio1 = socio1.getCuenta("CA-456-789-455").orElse(null);
        if (cuentaSocio1 != null) {
            //Asociado 1 realiza un depósito de $200,000 y un retiro de 50,000
            Transaccion deposito1 = new Deposito(200000);
            deposito1.ejecutar(cuentaSocio1);
            Transaccion retiro1 = new Retiro(50000);
            retiro1.ejecutar(cuentaSocio1);
        }

        //Aplicación de intereses a las cuentas de ahorro
        System.out.println("\nAplicando Intereses");
        cuentaSocio1.aplicarInteres();

        //Listar los nombres de todos los socios
        System.out.println("\nNombres de todos los socios:");
        coopRKC.getSocios().stream()
                .map(Socio::getNombre) //Metodo de referencia para obtener nombres
                .forEach(nombre -> System.out.println("- " + nombre));

        // Filtrar y mostrar las cuentas con saldo mayor a $500,000
        System.out.println("\nCuentas con saldo mayor a $500,000:");
        List<Cuenta> cuentasConSaldoAlto = coopRKC.filtrarCuentasPorSaldo(500000);
        cuentasConSaldoAlto.forEach(System.out::println);

        //Calcular el total del dinero en la cooperativa
        double saldoTotal = coopRKC.calcularSaldoTotal();
        System.out.println("\nSaldo total en la cooperativa: $" + saldoTotal);

        //Gestión de errores en retiros
        System.out.println("\nRetiro Excesivo de Dinero");
        Cuenta cuentaSocio2 = socio2.getCuenta("CA-455-567-498").orElse(null);
        if (cuentaSocio2 != null) {
            Transaccion retiroExcesivo = new Retiro(1000000); // Saldo es 450000
            retiroExcesivo.ejecutar(cuentaSocio2);
        }

        System.out.println("\n--- Prototipo Finalizado ---");
    }
}
