package CoopRKC.Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Socio {
    //Atributos de la clase Socio / Abstracción
    private String nombre;
    private String cedula;
    private List<Cuenta> cuentas = new ArrayList<>(); //Inicialización directa de la lista de Cuentas
    //Constructor de la clase Socio
    public Socio(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }
    //Getters para obtener los atributos del socio desde fuera de la clase / Encapsulamiento
    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }
    //Getter para obtener la lista de cuentas
    public List<Cuenta> getCuentas() {
        return cuentas;
    }
    //Metodo para agregar una nueva cuenta al socio
    public void agregarCuenta(Cuenta nuevaCuenta) {
        // Validación para no permitir números de cuenta repetidos para un mismo socio
        boolean numeroExistente = cuentas.stream()
                .anyMatch(cuenta -> cuenta.getNumeroCuenta().equals(nuevaCuenta.getNumeroCuenta()));

        if (numeroExistente) {
            System.out.println("Error: El socio " + nombre + " ya tiene una cuenta con el número " + nuevaCuenta.getNumeroCuenta());
        } else {
            cuentas.add(nuevaCuenta);
        }
    }
    //Metodo para buscar una cuenta por su número
    public Optional<Cuenta> getCuenta(String numeroCuenta) {
        return cuentas.stream()
                .filter(c -> c.getNumeroCuenta().equals(numeroCuenta))
                .findFirst();
    }
    //Metodo para listar todas las cuentas del socio
    @Override
    public String toString() {
        return "Socio{" +
                "nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", cuentas=" + cuentas.size() +
                '}';
    }
}
