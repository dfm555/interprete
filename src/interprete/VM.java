/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprete;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Oscar Arenas
 */
public class VM {

    private final ArrayList<Number> listaInstrucciones;
    private final Stack<Number> pilaNumeros;
    private String cadenaResultado;
    private ArrayList<Variable> tablaDeSimbolos;

    public VM(String programa) {
        Parser parser = new Parser(new Lexer(programa));
        parser.declaraciones();
        cadenaResultado = "";

        listaInstrucciones = parser.obtenerInstrucciones();
        tablaDeSimbolos = parser.obtenerTablaDeSimbolos();
        pilaNumeros = new Stack<>();
    }

    public void run() throws ArithmeticException {
        int n = listaInstrucciones.size();
        int i = 0;

        while (i < n) {
            int operacion = listaInstrucciones.get(i).intValue();

            switch (operacion) {
                case Instruccion.FIN:
                    return;
                case Instruccion.PRINT:
                    if (pilaNumeros.size() > 0) {
                        Number ans = pilaNumeros.pop();

                        if (ans.intValue() == ans.doubleValue()) {
                            cadenaResultado += "ans = " + ans.intValue() + "\n";
                        } else {
                            cadenaResultado += "ans = " + ans + "\n";
                        }
                    }
                    break;
                case Instruccion.POP:
                    if (pilaNumeros.size() > 0) {
                        pilaNumeros.pop();
                    }
                    break;
                case Instruccion.SUMA:
                    if (pilaNumeros.size() > 1) {
                        Number numero2 = pilaNumeros.pop();
                        Number numero1 = pilaNumeros.pop();

                        if (numero1 instanceof Integer
                                && numero2 instanceof Integer) {
                            pilaNumeros.push(numero1.intValue()
                                    + numero2.intValue());
                        } else {
                            pilaNumeros.push(numero1.floatValue()
                                    + numero2.floatValue());
                        }
                        // System.out.println("SUMA");
                    } else {
                        throw new ArithmeticException("Error: Falta operando.");
                    }
                    break;
                case Instruccion.RESTA:
                    if (pilaNumeros.size() > 1) {
                        Number numero2 = pilaNumeros.pop();
                        Number numero1 = pilaNumeros.pop();

                        if (numero1 instanceof Integer
                                && numero2 instanceof Integer) {
                            pilaNumeros.push(numero1.intValue()
                                    - numero2.intValue());
                        } else {
                            pilaNumeros.push(numero1.floatValue()
                                    - numero2.floatValue());
                        }
                        // System.out.println("RESTA");
                    } else {
                        throw new ArithmeticException("Error: Falta operando.");
                    }
                    break;
                case Instruccion.PUSH_NUMERO_ENTERO:
                    ++i;
                    //System.out.println("ENTERO: " + listaInstrucciones.get(i));
                    pilaNumeros.push(listaInstrucciones.get(i));
                    break;
                case Instruccion.PUSH_NUMERO_REAL:
                    ++i;
                    pilaNumeros.push(listaInstrucciones.get(i).floatValue());
                    break;
                case Instruccion.ASIGNACION:
                    ++i;
                    int index = listaInstrucciones.get(i).intValue();
                    if (pilaNumeros.size() > 0) {
                        Number numero1 = pilaNumeros.pop();

                        if (numero1 instanceof Integer) {
                            tablaDeSimbolos.get(index).tipo = "entero";
                            tablaDeSimbolos.get(index).valor = "" + numero1.intValue();
                        } else {
                            tablaDeSimbolos.get(index).tipo = "real";
                            tablaDeSimbolos.get(index).valor = "" + numero1.floatValue();
                        }

                        System.out.println("\n" + tablaDeSimbolos.get(index));
                    } else {
                        throw new ArithmeticException("Error: Falta valor.");
                    }

                    break;
                default:
                    return;
            }

            ++i;
        }
    }

    public String getAnswer() {
        return cadenaResultado;
    }
}
