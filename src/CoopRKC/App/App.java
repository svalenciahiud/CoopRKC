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
        System.out.println("--- Iniciando Prototipo CoopRKC ---");

        // 1. Crear la cooperativa
        Cooperativa coopRKC = new Cooperativa("CoopRKC");

        // 2. Registrar asociados y abrirles cuentas
        Socio socio1 = new Socio("Santiago Valencia Hernandez", "1028098456");
        socio1.agregarCuenta(new CuentaAhorros("CA-456-789-455", 1000000, 2.5));

        Socio socio2 = new Socio("Francisco Javier Florez Ramirez", "1028098457");
        socio2.agregarCuenta(new CuentaAhorros("CA-455-567-498", 450000, 3.0));

        Socio socio3 = new Socio("Ramiro Antonio Giraldo Escobar", "394050607");
        socio3.agregarCuenta(new CuentaAhorros("CA-441-675-678", 750000, 2.8));

        coopRKC.registrarSocio(socio1);
        coopRKC.registrarSocio(socio2);
        coopRKC.registrarSocio(socio3);

        // Intentar agregar una cuenta con número duplicado para el mismo socio
        System.out.println("\n--- Intentando agregar cuenta duplicada ---");
        socio2.agregarCuenta(new CuentaAhorros("CA-455-567-498", 300000, 3.0));


        // 3. Realizar operaciones de depósito y retiro
        System.out.println("\n--- Realizando Transacciones ---");
        Cuenta cuentaSocio1 = socio1.getCuenta("CA-456-789-455").orElse(null);
        if (cuentaSocio1 != null) {
            Transaccion deposito1 = new Deposito(200000);
            deposito1.ejecutar(cuentaSocio1);

            Transaccion retiro1 = new Retiro(50000);
            retiro1.ejecutar(cuentaSocio1);
        }

        // 4. Aplicar interés a una cuenta de ahorros
        System.out.println("\n--- Aplicando Intereses ---");
        if (cuentaSocio1 instanceof CuentaAhorros) {
            ((CuentaAhorros) cuentaSocio1).aplicarInteres();
        }

        // 5. Usar programación funcional
        System.out.println("\n--- Uso de Programación Funcional ---");

        // a. Listar los nombres de todos los socios
        System.out.println("\nNombres de todos los socios:");
        coopRKC.getSocios().stream()
                .map(Socio::getNombre) //Metodo de referencia para obtener nombres
                .forEach(nombre -> System.out.println("- " + nombre));

        // b. Filtrar y mostrar las cuentas con saldo mayor a $500,000
        System.out.println("\nCuentas con saldo mayor a $500,000:");
        List<Cuenta> cuentasConSaldoAlto = coopRKC.filtrarCuentasPorSaldo(500000);
        cuentasConSaldoAlto.forEach(System.out::println);

        // c. Calcular el total del dinero en la cooperativa
        double saldoTotal = coopRKC.calcularSaldoTotal();
        System.out.println("\nSaldo total en la cooperativa: $" + String.format("%,.2f", saldoTotal));

        // 6. Implementar gestión de errores en retiros
        System.out.println("\n--- Probando Gestión de Errores (Retiro Excesivo) ---");
        Cuenta cuentaSocio2 = socio2.getCuenta("CA-455-567-498").orElse(null);
        if (cuentaSocio2 != null) {
            Transaccion retiroExcesivo = new Retiro(1000000); // Saldo es 450000
            retiroExcesivo.ejecutar(cuentaSocio2);
        }

        System.out.println("\n--- Prototipo Finalizado ---");
    }
}
