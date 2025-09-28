package CoopRKC.Transacciones;

import CoopRKC.Modelo.Cuenta;
//Implementacion de la clase Retiro que implementa la interfaz Transaccion
public class Retiro implements Transaccion {
    private double monto;

    public Retiro(double monto) {
        this.monto = monto;
    }
    //Metodo para ejecutar el retiro en la cuenta especificada, este metodo se sobreescribe de la interfaz Transaccion
    @Override
    public void ejecutar(Cuenta cuenta) {
        System.out.println("\nIntentando retirar " + monto + " de la cuenta " + cuenta.getNumeroCuenta() + " con saldo " + cuenta.getSaldo());
        if (monto > cuenta.getSaldo()) {
            System.out.println("Error: El monto a retirar excede el saldo disponible.");
            return;
        }
        try {
            cuenta.retirar(monto);
            System.out.println("Retiro exitoso. Nuevo saldo: " + cuenta.getSaldo());
        } catch (IllegalArgumentException e) {
            System.out.println("Error al procesar retiro: " + e.getMessage());
        }
    }
    //Metodo para obtener el monto del retiro, este metodo se sobreescribe de la interfaz Transaccion
    @Override
    public double getMonto() {
        return monto;
    }
}
