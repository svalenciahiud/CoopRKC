package CoopRKC.Transacciones;

import CoopRKC.Modelo.Cuenta;

//Definición de la interfaz Transaccion que sera implementada por las clases Deposito y Retiro
public interface Transaccion {
    //Metodo para ejecutar la transaccion en la cuenta especificada
    void ejecutar(Cuenta cuenta);
    //Metodo para obtener el monto de la transaccion
    double getMonto();
}
