/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;

public class Compiler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("E:\\project\\JAVA-project\\compiler2\\test\\tests\\7.txt"));
            int c;

            String text = "";
            while ((c = br.read()) != -1) {
                char character = (char) c;          //converting integer to char
                text += character;
            }
            Lexer Lex = new Lexer("input1.txt", text);
            ArrayList<Token> t = Lex.make_tokens();
            out.println("*******************************************");
            out.println("**************     TOKENS      ************");
            out.println("*******************************************");
            out.println("Types          | Values                  |");
            out.println("-------------------------------------------|");
            for (int i = 0; i < t.size(); i++) {
                out.println(t.get(i).toString());
                out.println("------------------------------------------|");
            }
            out.println("*******************************************");
            out.println("**************     ERRORS      ************");
            out.println("*******************************************");
            if (!Lexer.err.isEmpty()) {
                out.println("**************     LEXER   ERRORS      ************");
                for (int i = 0; i < Lexer.err.size(); i++) {
                    if (!("\r\n").contains(Lexer.err.get(i).details)) {
                        out.println(Lexer.err.get(i).details);
                        out.println("------------------------------------------|");
                    }
                }
            }
            else {
                out.println("! you have Error in your code !");
            }
            ErrorHandler m = new ErrorHandler(t); //for manage the token that visited and call error handler
            m.manage();
            if (!ErrorHandler.hasError) {
                out.println("**************     PARSER   ERRORS      ************");

                out.println(" DOESN'T HAVE ERROR ;)      ");
                out.println("------------------------------------------|");
                Parser parse = new Parser(t);
                parse.parse();
            } else {
                out.println("! you have Error in your code !");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
