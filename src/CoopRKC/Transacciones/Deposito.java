package CoopRKC.Transacciones;

import CoopRKC.Modelo.Cuenta;
//Implementación de la clase Deposito que implementa la interfaz Transaccion
public class Deposito implements Transaccion {
    private double monto;

    public Deposito(double monto) {
        this.monto = monto;
    }
    //Metodo para ejecutar el depósito en la cuenta especificad, este metodo se sobreescribe de la interfaz Transaccion
    @Override
    public void ejecutar(Cuenta cuenta) {
        System.out.print("Ejecutando depósito de " + monto + " en cuenta " + cuenta.getNumeroCuenta() + ". ");
        cuenta.depositar(monto);
    }
    //Metodo para obtener el monto del depósito, este metodo se sobreescribe de la interfaz Transaccion
    @Override
    public double getMonto() {
        return monto;
    }
}
