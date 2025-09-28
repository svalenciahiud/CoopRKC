package CoopRKC.Modelo;
//Superclase abstracta Cuenta
public abstract class Cuenta {
    //Atributos de la clase Cuenta / Abstracción
    protected String numeroCuenta;
    protected double saldo;
    //Constructor de la clase Cuenta
    public Cuenta(String numeroCuenta, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo; // Inicializar saldo en 0 de la cuenta
    }
    //Getters para obtener los atributos de la cuenta desde fuera de la clase / Encapsulamiento
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }
    // Depositar con validaciones
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            System.out.println("Depósito exitoso. Nuevo saldo: " + saldo);
        } else {
            System.out.println("El monto a depositar debe ser positivo.");
        }
    }
    // Retirar con validaciones
    public boolean retirar(double monto) {
        if (monto > saldo) {
            System.out.println("Saldo insuficiente para realizar el retiro.");
            return false;
        }
        if (monto <= 0) {
            System.out.println("El monto a retirar debe ser positivo.");
            return false;
        }
        saldo -= monto;
        return true;
    }

    // Metodo para aplicar interés, que puede ser sobrescrito por subclases
    public void aplicarInteres() {
        // Por defecto, no hace nada
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}

