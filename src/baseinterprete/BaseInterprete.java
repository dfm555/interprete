/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseinterprete;

import interprete.Lexer;
import interprete.Token;

/**
 *
 * @author Oscar
 */
public class BaseInterprete {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String programa = "2+ 233 +  4.5";
        Lexer lexer = new Lexer(programa);
        
       while(!lexer.match(Token.FIN_ARCHIVO) ){
           if( lexer.match(Token.VALOR_ENTERO) ){
               System.out.println("Entero " + lexer.obtenerEntero());
           }
           if( lexer.match(Token.SUMA) ){
               System.out.println("SUMA ");
           }

           if( lexer.match(Token.VALOR_REAL) ){
               System.out.println("Real "+ lexer.obtenerReal());
           }
         lexer.advance();
       }
    }
    
}
