package CoopRKC.Modelo;
// Subclase CuentaAhorros que hereda de Cuenta
public class CuentaAhorros extends Cuenta {
    private double interesAnual;
    // Constructor de la clase CuentaAhorros
    public CuentaAhorros(String numeroCuenta, double saldo, double  interesAnual) {
        super(numeroCuenta, saldo); // Llamada al constructor de la superclase
        this.interesAnual = interesAnual;
    }
    // Getter para obtener la tasa de interés anual
    public double getInteresAnual() {
        return interesAnual;
    }
    //Metodo para aplicar el interés anual al saldo
    public void aplicarInteres() {
        double interesGenerado = saldo * (interesAnual / 100);
        saldo += interesGenerado;
        System.out.println("Interés de " + interesGenerado + " aplicado. Nuevo saldo: " + saldo);
    }
}

