/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler2;

import com.sun.org.apache.xerces.internal.util.SymbolTable;
import java.util.ArrayList;
import compiler2.Error;
import static java.lang.System.out;

public class Parser {

    ArrayList<Token> tokens;
    int tok_idx;
    Token current_tok;

    Parser(ArrayList tokens) {
        this.tokens = tokens;
        this.tok_idx = -1;
        current_tok = this.advance();
    }

    public Token advance() {
        this.tok_idx += 1;
        if (this.tok_idx < this.tokens.size()) {
            this.current_tok = this.tokens.get(tok_idx);
        }
        return this.current_tok;
    }

    public void showSymbolTable() {
        out.println("*******************************************");
        out.println("************     SYMBOLTABLE      *********");
        out.println("*******************************************");
        out.println("-------------------------------------------|");
        out.println("NAME   |   VALUE");
        ErrorHandler.symbolTable.entrySet().forEach(entry -> {
            out.println("-------------------------------------------|");
            System.out.println(entry.getKey() + "       |      " + entry.getValue());
            out.println("-------------------------------------------|");
        });

    }

    public void parse() {
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).type.equals(Tokens.T_EQ)) {

                if (tokens.get(i - 1).type.equals("integer")) {
                    String expression = "";
                    int index = i;
                    index++;
                    while (!tokens.get(index).type.equals(Tokens.T_ENDCOMMAND)) {
                        if (tokens.get(index).type.equals("integer")) {
                            expression += ErrorHandler.symbolTable.get(tokens.get(index).value);
                        } else if (tokens.get(index).value.equals("")) {
                            expression += tokens.get(index).type;
                        } else {
                            expression += tokens.get(index).value;
                        }
                        index++;
                    }
                    EvaluateString exp = new EvaluateString();
                    String r = String.valueOf(exp.evaluate(expression));
                    ErrorHandler.symbolTable.put(tokens.get(i - 1).value, r);
                    out.println(ErrorHandler.symbolTable.get(tokens.get(i - 1).value));

                } else if (tokens.get(i - 1).type.equals("String")) {
                    String value = "";
                    int index = i;
                    while (!tokens.get(index).type.equals(Tokens.T_ENDCOMMAND)) {
                        index++;
                        if (tokens.get(index).type.equals("String")) {
                            value += ErrorHandler.symbolTable.get(tokens.get(index).value);
                        } else if (tokens.get(index).value.equals("+")) {
                            index++;
                            value += value.concat(tokens.get(index).value);

                        } else {

                            value += tokens.get(index).value;
                        }
                    }
                    ErrorHandler.symbolTable.put(tokens.get(i - 1).value, value);

                } else if (tokens.get(i - 1).type.equals("float")) {
                    String expression = "";
                    int index = i;
                    index++;
                    if (tokens.get(i - 2).type.equals("float")) {

                        while (!tokens.get(index).type.equals(Tokens.T_ENDCOMMAND)) {

                            if (tokens.get(index).value.equals("")) {
                                expression += tokens.get(index).type;
                            } else {
                                expression += tokens.get(index).value;
                            }
                            index++;
                        }
                    } else {
                        while (!tokens.get(index).type.equals(Tokens.T_ENDCOMMAND)) {

                            if (tokens.get(index).value.equals("")) {
                                expression += tokens.get(index).type;
                            }
//                            else if(ErrorHandler.symbolTable.containsKey(tokens.get(index).value) && ErrorHandler.symbolTable.get(i)){
                                
//                            } 
                            else {
                                expression += tokens.get(index).value;
                            }
                            index++;
                        }
                    }
                    out.println(expression);
                    EvaluateString exp = new EvaluateString();
                    String r = String.valueOf(exp.evaluate(expression));
                    ErrorHandler.symbolTable.put(tokens.get(i - 1).value, r);
                    out.println(ErrorHandler.symbolTable.get(tokens.get(i - 1).value));

                } else if (tokens.get(i - 1).type.equals("boolean")) {
                    String value = tokens.get(i + 1).value;
                    out.println(value);
                    ErrorHandler.symbolTable.put(tokens.get(i - 1).value, value);
                }
            }
        }
        showSymbolTable();
    }

}
