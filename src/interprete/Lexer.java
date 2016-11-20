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
                case '*':
                    return Token.MULTIPLICACION;
                case '/':
                    return Token.DIVISION;
                case '(':
                    return Token.ABRIR_PARENTESIS;
                case ')':
                    return Token.CERRAR_PARENTESIS;
                case ',':
                    return Token.COMA;
                case ';':
                    return Token.PUNTOYCOMA;
                case '=':
                    return Token.ASIGNACION;
                default:
                    if (Character.isDigit(caracter)) {
                        while (posicion + longitud < n
                                && Character.isDigit(expresion.charAt(posicion
                                                + longitud))) {
                            ++longitud;
                        }

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
                    } else if (Character.isAlphabetic(caracter)) {
                        while (posicion + longitud < n
                                && (Character.isDigit(expresion.charAt(posicion
                                        + longitud)) || Character.isAlphabetic(expresion.charAt(posicion
                                        + longitud)))) {
                            ++longitud;
                        }

                        return Token.ID;
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

    public boolean nextTokenIs(int token) {
        int auxiliarPoisicion = posicion;
        int auxiliarLongitud = longitud;
        int auxToken = nuevoToken;
        advance();
        boolean ans = match(token);

        posicion = auxiliarPoisicion;
        longitud = auxiliarLongitud;
        if(!ans){
            nuevoToken = auxToken;
        }
        return ans;
    }

    public int obtenerEntero() {
        // System.out.println("Posicion: " + posicion + ", longitud: " + longitud);
        return Integer.parseInt(expresion.substring(posicion, posicion
                + longitud));
    }

    public double obtenerReal() {
        // System.out.println("Posicion: " + posicion + ", longitud: " + longitud);
        return Double.parseDouble(expresion.substring(posicion, posicion
                + longitud));
    }

    public String obtenerCadena() {
        // System.out.println("Posicion: " + posicion + ", longitud: " + longitud);
        return expresion.substring(posicion, posicion
                + longitud);
    }
}
