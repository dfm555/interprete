/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseinterprete;

import interprete.VM;

import java.util.Scanner;

/**
 *
 * @author Oscar
 */
public class BaseInterprete {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner ENTRADA = new Scanner(System.in);

        System.out.println("Ingrese la expresión aritmética: ");
        String expresion = ENTRADA.nextLine();
//
//        Lexer lexer = new Lexer(expresion);
//        Parser parser = new Parser(lexer);
//        parser.declaraciones();
        //
        VM calculadora = new VM(expresion);

        calculadora.run();
        System.out.println("\n" + calculadora.getAnswer());
    }
    
}
