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

    private final ArrayList<Object> listaInstrucciones;
    private final Stack<Object> pilaNumeros;
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
            int operacion = (int) listaInstrucciones.get(i);

            switch (operacion) {
                case Instruccion.FIN:
                    return;
                case Instruccion.PRINT:
                    if (pilaNumeros.size() > 0) {
                        Object ans = pilaNumeros.pop();

                        cadenaResultado += "ans = " + ans + "\n";

                    }
                    break;
                case Instruccion.POP:
                    if (pilaNumeros.size() > 0) {
                        pilaNumeros.pop();
                    }
                    break;
                case Instruccion.SUMA:
                    if (pilaNumeros.size() > 1) {
                        Object numero2 = pilaNumeros.pop();
                        Object numero1 = pilaNumeros.pop();

                        if (numero1 instanceof Integer
                                && numero2 instanceof Integer) {
                            pilaNumeros.push( (int) numero1
                                    + (int) numero2);
                        } else {
                            pilaNumeros.push(  new Float(numero1.toString())
                                    + new Float(numero2.toString()).floatValue());
                        }
                        //System.out.println("SUMA");
                    } else {
                        throw new ArithmeticException("Error: Falta operando.");
                    }
                    break;
                case Instruccion.RESTA:
                    if (pilaNumeros.size() > 1) {
                        Object numero2 = pilaNumeros.pop();
                        Object numero1 = pilaNumeros.pop();

                        if (numero1 instanceof Integer
                                && numero2 instanceof Integer) {
                            pilaNumeros.push( (int) numero1
                                    - (int) numero2);
                        } else {
                            pilaNumeros.push(  new Float(numero1.toString())
                                    - new Float(numero2.toString()).floatValue());
                        }
                        //System.out.println("RESTA");
                    } else {
                        throw new ArithmeticException("Error: Falta operando.");
                    }
                    break;
                case Instruccion.MULTIPLICACION:
                    if (pilaNumeros.size() > 1) {
                        Object numero2 = pilaNumeros.pop();
                        Object numero1 = pilaNumeros.pop();

                        if (numero1 instanceof Integer
                                && numero2 instanceof Integer) {
                            pilaNumeros.push( (int) numero1
                                    * (int) numero2);
                        } else {
                            pilaNumeros.push(  new Float(numero1.toString())
                                    * new Float(numero2.toString()).floatValue());
                        }
                        //System.out.println("MULTIPLICACION");
                    } else {
                        throw new ArithmeticException("Error: Falta operando.");
                    }
                    break;
                case Instruccion.DIVISION:
                    if (pilaNumeros.size() > 1) {
                        Object numero2 = pilaNumeros.pop();
                        Object numero1 = pilaNumeros.pop();

                        if (numero1 instanceof Integer
                                && numero2 instanceof Integer) {
                            if(numero2 != 0){
                                pilaNumeros.push( (int) numero1 / (int) numero2);
                            }else{
                                throw new ArithmeticException("Error: Division por cero");
                            }

                        } else {
                            if(numero2 != 0){
                                pilaNumeros.push( new Float(numero1.toString())
                                        / new Float(numero2.toString()) );
                            }else{
                                throw new ArithmeticException("Error: Division por cero");
                            }
                        }
                        //System.out.println("Division");
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
                    pilaNumeros.push(listaInstrucciones.get(i));
                    break;
                case Instruccion.PUSH_IDENTIFICADOR:
                    ++i;
                    Variable variable = new Variable(listaInstrucciones.get(i).toString());
                    int varIndex = tablaDeSimbolos.indexOf(variable);
                    pilaNumeros.push(tablaDeSimbolos.get(varIndex).valor);
                    break;
                case Instruccion.ASIGNACION:
                    ++i;
                    int index = (int) listaInstrucciones.get(i);
                    if (pilaNumeros.size() > 0) {
                        Object numero1 = pilaNumeros.pop();

                        pilaNumeros.push(numero1);
                        if (numero1 instanceof Integer) {
                            tablaDeSimbolos.get(index).tipo = "entero";
                            tablaDeSimbolos.get(index).valor = "" + (int) numero1;
                        } else {
                            tablaDeSimbolos.get(index).tipo = "real";
                            tablaDeSimbolos.get(index).valor = "" +  new Double(numero1.toString());
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
