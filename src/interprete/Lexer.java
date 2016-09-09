/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprete;

/**
 *
 * @author Oscar
 */
public class Lexer {

    private String expresion;
    private int posicion;
    private int longitud;
    private int nuevoToken;

    public Lexer(String expresion) {
        establecer(expresion);
    }

    public final void establecer(String expresion) {
        this.expresion = "" + expresion;
        posicion = 0;
        longitud = 0;
        nuevoToken = 0;
    }

    private int getToken() {
        int n = expresion.length();
        posicion += longitud;

        longitud = 1;

        while (posicion < n && expresion.charAt(posicion) == ' ') {
            ++posicion;
        }

        if (posicion < n) {
            char caracter = expresion.charAt(posicion);

            switch (caracter) {
                case '+':
                    return Token.SUMA;
                case '-':
                    return Token.RESTA;
                case '(':
                    return Token.ABRIR_PARENTESIS;
                case ')':
                    return Token.CERRAR_PARENTESIS;
                default:
                    if (Character.isDigit(caracter)) {
                        while (posicion + longitud < n
                                && Character.isDigit(expresion.charAt(posicion
                                                + longitud))) {
                            ++longitud;
                        }

                        //return Token.VALOR_ENTERO;
                        if (posicion + longitud < n
                                && expresion.charAt(posicion + longitud) == '.') {
                            ++longitud;

                            while (posicion + longitud < n
                                    && Character.isDigit(expresion.charAt(posicion
                                                    + longitud))) {
                                ++longitud;
                            }
                            return Token.VALOR_REAL;
                        }
                        return Token.VALOR_ENTERO;
                    }
            }
        }

        return Token.FIN_ARCHIVO;
    }

    public void advance() {
        nuevoToken = getToken();
    }

    public boolean match(int token) {
        if (nuevoToken == 0) {
            nuevoToken = getToken();
        }

        return token == nuevoToken;
    }

    public int obtenerEntero() {
        return Integer.parseInt(expresion.substring(posicion, posicion
                + longitud));
    }

    public double obtenerReal() {
        return Double.parseDouble(expresion.substring(posicion, posicion
                + longitud));
    }
}
