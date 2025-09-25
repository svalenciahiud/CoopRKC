package CoopRKC.Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cooperativa {
    // Atributos de la clase Cooperativa / Abstracción
    private String nombre;
    private List<Socio> socios = new ArrayList<>(); //Inicialización directa de la lista de Socios
    // Constructor de la clase Cooperativa
    public Cooperativa(String nombre) {
        this.nombre = nombre;
    }
    //Metodo para registrar un nuevo socio
    public void registrarSocio(Socio socio) {
        socios.add(socio);
        System.out.println("Socio " + socio.getNombre() + " registrado exitosamente.");
    }
    // Getter para obtener la lista de socios desde fuera de la clase / Encapsulamiento
    public List<Socio> getSocios() {
        return socios;
    }
    //Metodo para listar todos los socios de la cooperativa
    public void listarSocios() {
        System.out.println("\n--- Lista de Socios en " + this.nombre + " ---");
        socios.stream()
                .forEach(System.out::println);
        System.out.println("----------------------------------------");
    }
    //Metodo para calcular el saldo total de todas las cuentas de todos los socios
    public double calcularSaldoTotal() {
        return socios.stream() // Stream de Socios
                .flatMap(socio -> socio.getCuentas().stream()) // Stream de todas las Cuentas
                .mapToDouble(Cuenta::getSaldo) // Stream de doubles (saldos)
                .reduce(0.0, Double::sum); // Suma total
    }
    //Metodo para filtrar y obtener una lista de cuentas con saldo mayor a un monto especifico
    public List<Cuenta> filtrarCuentasPorSaldo(double monto) {
        return socios.stream()
                .flatMap(socio -> socio.getCuentas().stream())
                .filter(cuenta -> cuenta.getSaldo() > monto)
                .collect(Collectors.toList());
    }
}
