/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprete;

/**
 *
 * @author Oscar Arenas
 */
public class Variable {

    public String nombre;
    public String tipo;
    public String valor;

    public Variable(String nombre) {
        this.nombre = nombre;
        this.tipo = "";
        this.valor = "";
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        } else if (objeto instanceof Variable) {
            Variable aux = (Variable) objeto;

            return this.nombre.compareTo(aux.nombre) == 0;
        }

        return false;
    }

    @Override
    public String toString() {
        return "ID => " + "Nombre: " + nombre + ", tipo : " + tipo + ", valor: " + valor;
    }
}
