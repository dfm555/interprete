package interprete;

public class Token {

    public static final int FIN_ARCHIVO = -1;
    //
    public static final int ERROR = 0;
    //
    public static final int SUMA = 1;
    public static final int RESTA = 2;
    public static final int MULTIPLICACION = 3;
    public static final int DIVISION = 4;
    public static final int POTENCIA = 5;
    public static final int MODULO = 6;
    public static final int DIVISION_ENTERA = 7;

    public static final int ABRIR_PARENTESIS = 8;
    public static final int CERRAR_PARENTESIS = 9;
    public static final int PI = 10;

    //
    public static final int MENOR_QUE = 11;
    public static final int MAYOR_QUE = 12;
    public static final int IGUAL_QUE = 13;
    public static final int MENOR_IGUAL_QUE = 14;
    public static final int MAYOR_IGUAL_QUE = 15;
    public static final int DIFERENTE = 16;
    //
    public static final int Y_LOGICO = 17;
    public static final int O_LOGICO = 18;
    public static final int NO_LOGICO = 19;

    public static final int COMA = 50;
    public static final int PUNTOYCOMA = 51;
    //
    public static final int VALOR_ENTERO = 100;
    public static final int VALOR_REAL = 101;
    public static final int VALOR_LOGICO = 102;
    public static final int VALOR_CARACTER = 103;
    public static final int VALOR_CADENA = 104;
    public static final int ID = 105;
    public static final int ASIGNACION = 106;

    //
    public static final int RAIZ_CUADRADA = 400;
    public static final int VALOR_ABSOLUTO = 401;
    public static final int LOGARITMO_NATURAL = 402;
    public static final int EXPONENCIAL = 403;
    public static final int SENO = 404;
    public static final int COSENO = 405;
    public static final int TANGENTE = 406;
    public static final int ALEATORIO = 407;
}
