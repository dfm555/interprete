/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprete;

import java.util.ArrayList;

/**
 *
 * @author Oscar Arenas
 */
public class Parser {

    private final Lexer lexer;
    private final ArrayList<Number> listaInstrucciones;
    private final ArrayList<Variable> tablaDeSimbolos;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        listaInstrucciones = new ArrayList<>();
        tablaDeSimbolos = new ArrayList<>();
    }

    public void declaraciones() {
        while (!lexer.match(Token.FIN_ARCHIVO)) {
            asignacion();
            expresiones();
        }

        listaInstrucciones.add(Instruccion.FIN);
        //System.out.println("Fin!");
    }

    public void expresiones() {
        expresion();
        if (lexer.match(Token.COMA)) {
            listaInstrucciones.add(Instruccion.PRINT);
            lexer.advance();
            expresiones();
        } else if (lexer.match(Token.PUNTOYCOMA)) {
            listaInstrucciones.add(Instruccion.POP);
            lexer.advance();
            expresiones();
        } else {
            listaInstrucciones.add(Instruccion.PRINT);
        }
    }

    public void termino() {
        if (lexer.match(Token.VALOR_ENTERO)) {
            int entero = lexer.obtenerEntero();

            listaInstrucciones.add(Instruccion.PUSH_NUMERO_ENTERO);
            listaInstrucciones.add(entero);

            lexer.advance();
        } else if (lexer.match(Token.VALOR_REAL)) {
            double real = lexer.obtenerReal();
            //System.out.println("real: " + real);

            listaInstrucciones.add(Instruccion.PUSH_NUMERO_REAL);
            listaInstrucciones.add(real);

            lexer.advance();
        } else if (lexer.match(Token.ABRIR_PARENTESIS)) {
            System.out.println("(");

            lexer.advance();
            expresion();
            if (!lexer.match(Token.CERRAR_PARENTESIS)) {
                System.out.println("Error: Se esperaba )");
                return;
            }
            System.out.println(")");

            lexer.advance();
        }
    }

    public void expresion() {
        termino();
        expresionPrima();
    }

    public void expresionPrima() {
        if (lexer.match(Token.SUMA)) {
            lexer.advance();
            termino();
            listaInstrucciones.add(Instruccion.SUMA);
            expresionPrima();
        }

        if (lexer.match(Token.RESTA)) {
            lexer.advance();
            termino();
            listaInstrucciones.add(Instruccion.RESTA);
            expresionPrima();
        }
    }

    public void asignacion() {
        if (lexer.match(Token.ID) && lexer.nextTokenIs(Token.ASIGNACION)) {
            String cadena = lexer.obtenerCadena();

            Variable id = new Variable(cadena);

            if (!tablaDeSimbolos.contains(id)) {
                tablaDeSimbolos.add(id);
            }

            lexer.advance();
            lexer.advance();

            expresion();
            if (!lexer.match(Token.PUNTOYCOMA)) {
                System.out.println("Error: Se esperaba ; en la instrucción de asignación.");
                return;
            }
            lexer.advance();

            listaInstrucciones.add(Instruccion.ASIGNACION);
            listaInstrucciones.add(tablaDeSimbolos.indexOf(id));
        }
    }

    public ArrayList<Number> obtenerInstrucciones() {
        return listaInstrucciones;
    }

    public ArrayList<Variable> obtenerTablaDeSimbolos() {
        return tablaDeSimbolos;
    }
}
