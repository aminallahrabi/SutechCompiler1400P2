/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler2;

import java.util.ArrayList;
import static java.lang.System.out;
import java.util.HashMap;

public class ErrorHandler {

    ArrayList<Token> tokens = new ArrayList<Token>();
    ArrayList<Error> errors = new ArrayList<Error>();
    int angelBracket = 0;
    int Bracket = 0;
    int paranthes = 0;
    public static int totalKeywords = 0;
    public static int totalParantheses = 0;
    public static HashMap<String, String> symbolTable = new HashMap<String, String>();
    public static boolean hasError = false;
////////////////////////////////////////////////////////////////////////

    ErrorHandler(ArrayList t) {
        tokens = t;
    }
//////////////////////////////////////////////////////////////////////

    public void checkAssignInt(int i) {
        int index = i;
        if (tokens.get(index + 1).type != Tokens.T_IDENTIFIER) {
            hasError = true;
            Error e = new Error().InvalidSyntaxError(String.valueOf(tokens.get(index + 1).pos_start), tokens.get(index + 1).positions, "more variable :" + tokens.get(index + 1).value);
            out.println("------------------------------------------|");
            out.println(e.as_string());
            out.println("------------------------------------------|");

        } else if (tokens.get(index + 2).type != Tokens.T_EQ) {
            hasError = true;
            Error e = new Error().InvalidSyntaxError(String.valueOf(tokens.get(index + 2).pos_start), tokens.get(index + 2).positions, "need ( = )");
            out.println("------------------------------------------|");
            out.println(e.as_string());
            out.println("------------------------------------------|");

        } else {
            for (int j = index + 3; j < tokens.size(); j++) {
                if ((tokens.get(j).type.equals(Tokens.T_INT)) || (tokens.get(j).type.equals(Tokens.T_DIV)) || (tokens.get(j).type.equals(Tokens.T_MINUS)) || (tokens.get(j).type.equals(Tokens.T_PLUS)) || (tokens.get(j).type.equals(Tokens.T_MUL)) || (tokens.get(j).type.equals(Tokens.T_POW)) || (tokens.get(j).type.equals(Tokens.T_IDENTIFIER))) {
                    if (tokens.get(j).value.equals(tokens.get(index + 1).value)) {
                        hasError = true;
                        Error e = new Error().InvalidSyntaxError(String.valueOf(tokens.get(j).pos_start), tokens.get(j).positions, "at first, assign value to variable :" + tokens.get(j).value);
                        out.println("------------------------------------------|");
                        out.println(e.as_string());
                        out.println("------------------------------------------|");

                    }
                } else if (tokens.get(j).type.equals(Tokens.T_ENDCOMMAND)) {

                    break;
                } else {
                    hasError = true;
                    Error e = new Error().InvalidSyntaxError(String.valueOf(tokens.get(j).pos_start), tokens.get(j).positions, "Check value or type or -> ; :" + tokens.get(j).value);
                    out.println("------------------------------------------|");
                    out.println(e.as_string());
                    out.println("------------------------------------------|");

                    break;
                }
            }
        }
    }
////////////////////////////////////////////////////////////

    public void checkAssignString(int i) {
        int index = i;

        if (tokens.get(index + 1).type != Tokens.T_IDENTIFIER) {
            hasError = true;
            Error e = new Error().InvalidSyntaxError(String.valueOf(tokens.get(index + 1).pos_start), tokens.get(index + 1).positions, "need variable but get=" + tokens.get(index + 1).value);
            out.println("------------------------------------------|");
            out.println(e.as_string());
            out.println("------------------------------------------|");
        } else if (tokens.get(index + 2).type != Tokens.T_EQ) {
            hasError = true;
            Error e = new Error().InvalidSyntaxError(String.valueOf(tokens.get(index + 2).pos_start), tokens.get(index + 2).positions, "need =");
            out.println("------------------------------------------|");
            out.println(e.as_string());
            out.println("------------------------------------------|");

        } else {
            for (int j = index + 3; j < tokens.size(); j++) {
                if ((tokens.get(j).type == Tokens.T_STRING) || (tokens.get(j).type == Tokens.T_DIV) || (tokens.get(j).type == Tokens.T_MINUS) || (tokens.get(j).type == Tokens.T_PLUS) || (tokens.get(j).type == Tokens.T_MUL) || (tokens.get(j).type == Tokens.T_POW) || (tokens.get(j).type == Tokens.T_IDENTIFIER)) {
                    if (tokens.get(j).value.equals(tokens.get(index + 1).value)) {
                        hasError = true;
                        Error e = new Error().InvalidSyntaxError(String.valueOf(tokens.get(j).pos_start), tokens.get(j).positions, "at first, assign value to variable :" + tokens.get(j).value);
                        out.println("------------------------------------------|");
                        out.println(e.as_string());
                        out.println("------------------------------------------|");

                    }
                } else if (tokens.get(j).type == Tokens.T_ENDCOMMAND) {

                    break;
                } else {
                    hasError = true;
                    Error e = new Error().InvalidSyntaxError(String.valueOf(tokens.get(j).pos_start), tokens.get(j).positions, "Check value or type or -> ; but get=" + tokens.get(j).value);
                    out.println("------------------------------------------|");
                    out.println(e.as_string());
                    out.println("------------------------------------------|");

                }
            }
        }
    }
//////////////////////////////////////////////////////////////

    public void checkAssignFloat(int i) {
        int index = i;
        if (tokens.get(index + 1).type != Tokens.T_IDENTIFIER) {
            hasError = true;
            Error e = new Error().InvalidSyntaxError(String.valueOf(tokens.get(index + 1).pos_start), tokens.get(index + 1).positions, "need variable :" + tokens.get(index + 1).value);
            out.println("------------------------------------------|");
            out.println(e.as_string());
            out.println("------------------------------------------|");

        } else if (tokens.get(index + 2).type != Tokens.T_EQ) {
            hasError = true;
            Error e = new Error().InvalidSyntaxError(String.valueOf(tokens.get(index + 2).pos_start), tokens.get(index + 2).positions, "need = ");
            out.println("------------------------------------------|");
            out.println(e.as_string());
            out.println("------------------------------------------|");

        } else {
            for (int j = index + 3; j < tokens.size(); j++) {
                if ((tokens.get(j).type == Tokens.T_FLOAT) || (tokens.get(j).type == Tokens.T_DIV) || (tokens.get(j).type == Tokens.T_MINUS) || (tokens.get(j).type == Tokens.T_PLUS) || (tokens.get(j).type == Tokens.T_MUL) || (tokens.get(j).type == Tokens.T_POW) || (tokens.get(j).type == Tokens.T_IDENTIFIER)) {

                } else if (tokens.get(j).type == Tokens.T_ENDCOMMAND) {

                    break;
                } else {
                    hasError = true;
                    Error e = new Error().InvalidSyntaxError(String.valueOf(tokens.get(j).pos_start), tokens.get(j).positions, " Check value or type or -> ; but get=" + tokens.get(j).value);
                    out.println("------------------------------------------|");
                    out.println(e.as_string());
                    out.println("------------------------------------------|");

                }
            }
        }
    }
//////////////////////////////////////////////////////////////

    public void checkAssignBoolean(int i) {
        int index = i;
        if (tokens.get(index + 1).type != Tokens.T_IDENTIFIER) {
            hasError = true;
            Error e = new Error().InvalidSyntaxError(String.valueOf(tokens.get(index + 1).pos_start), tokens.get(index + 1).positions, " need variable :" + tokens.get(index + 1).value);
            out.println("------------------------------------------|");
            out.println(e.as_string());
            out.println("------------------------------------------|");

        } else if (tokens.get(index + 2).type != Tokens.T_EQ) {
            hasError = true;
            Error e = new Error().InvalidSyntaxError(String.valueOf(tokens.get(index + 2).pos_start), tokens.get(index + 2).positions, " need = ");
            out.println("------------------------------------------|");
            out.println(e.as_string());
            out.println("------------------------------------------|");

        } else {
            for (int j = index + 3; j < tokens.size(); j++) {
                if ((tokens.get(j).type == Tokens.T_BOOL)) {

                    continue;
                } else if (tokens.get(j).type == Tokens.T_ENDCOMMAND) {

                    break;
                } else {
                    hasError = true;
                    Error e = new Error().InvalidSyntaxError(String.valueOf(tokens.get(j).pos_start), tokens.get(j).positions, "Check value or type or -> ; but get=" + tokens.get(j).value);
                    out.println("------------------------------------------|");
                    out.println(e.as_string());
                    out.println("------------------------------------------|");

                }
            }
        }
    }
////////////////////////////////////////////////////

    public void checkIWFstructure(int i) {
        int index = i;
        if (tokens.get(index + 1).type != Tokens.T_LPAREN) {
            hasError = true;
            Error e = new Error().InvalidSyntaxError(String.valueOf(tokens.get(index + 1).pos_start), tokens.get(index + 1).positions, "need ( but get=" + tokens.get(index + 1).value);
            out.println("------------------------------------------|");
            out.println(e.as_string());
            out.println("------------------------------------------|");

        }
    }
//////////////////////////////////////////////////////////

    public void checkElse(int i) {
        int index = i;
        if (tokens.get(index + 1).type != Tokens.T_LANGELBRACKET) {
            hasError = true;
            Error e = new Error().InvalidSyntaxError(String.valueOf(tokens.get(index + 1).pos_start), tokens.get(index + 1).positions, "need { but get=" + tokens.get(index + 1).value);
            out.println("------------------------------------------|");
            out.println(e.as_string());
            out.println("------------------------------------------|");

        }
        if (tokens.get(index - 1).type != Tokens.T_RANGELBRACKET) {
            hasError = true;
            Error e = new Error().InvalidSyntaxError(String.valueOf(tokens.get(index).pos_start), tokens.get(index).positions, "need } but get=" + tokens.get(index).value);
            out.println("------------------------------------------|");
            out.println(e.as_string());
            out.println("------------------------------------------|");

        }
    }
////////////////////////////////////////////////////////

    public void checks() {

        if (((Lexer.angelbracketsO != totalKeywords) || (Lexer.angelbracketsC != totalKeywords)) && totalKeywords != 0) {

            if ((Lexer.angelbracketsO > totalKeywords)) {
                hasError = true;
                out.println("------------------------------------------|");
                out.println("you type " + (Lexer.angelbracketsO - totalKeywords) + " more angelbrackets { ");
                out.println("------------------------------------------|");

            } else if ((Lexer.angelbracketsO < totalKeywords)) {
                hasError = true;
                out.println("------------------------------------------|");
                out.println("you need " + (totalKeywords - Lexer.angelbracketsO) + " more angelbrackets { ");
                out.println("------------------------------------------|");

            } else {
            }
            if ((Lexer.angelbracketsC > totalKeywords)) {
                hasError = true;
                out.println("------------------------------------------|");
                out.println("you type " + (Lexer.angelbracketsC - totalKeywords) + " more angelbrackets } ");
                out.println("------------------------------------------|");

            } else if ((Lexer.angelbracketsC < totalKeywords)) {
                hasError = true;
                out.println("------------------------------------------|");
                out.println("you need " + (totalKeywords - Lexer.angelbracketsC) + " more angelbrackets } ");
                out.println("------------------------------------------|");

            } else {
            }

        }
        if (((Lexer.paranthesesO != totalParantheses) || (Lexer.paranthesesC != totalParantheses)) && totalKeywords != 0) {

            if ((Lexer.paranthesesO > totalParantheses)) {
                hasError = true;
                out.println("------------------------------------------|");
                out.println("you type " + (Lexer.paranthesesO - totalParantheses) + " more brackets ( ");
                out.println("------------------------------------------|");

            } else if ((Lexer.paranthesesO < totalParantheses)) {
                hasError = true;
                out.println("------------------------------------------|");
                out.println("you need " + (totalParantheses - Lexer.paranthesesO) + " more brackets ( ");
                out.println("------------------------------------------|");

            } else {
            }
            if ((Lexer.paranthesesC > totalParantheses)) {
                hasError = true;
                out.println("------------------------------------------|");
                out.println("you type " + (Lexer.paranthesesC - totalParantheses) + " more brackets ) ");
                out.println("------------------------------------------|");

            } else if ((Lexer.paranthesesC < totalParantheses)) {
                hasError = true;
                out.println("------------------------------------------|");
                out.println("you need " + (totalParantheses - Lexer.paranthesesC) + " more brackets ) ");
                out.println("------------------------------------------|");

            } else {
            }
        }
        if (Lexer.bracketsO != Lexer.bracketsC) {
            hasError = true;
            out.println("------------------------------------------|");
            out.println("you need more angelbrackets [ or ] ");
            out.println("------------------------------------------|");

        }
    }
////////////////////////////////////////////////////////////////////////

    public void checkIdentifier(int i) {
        int index = i;
        if ((tokens.get(index - 1).value.equals("int")) || (tokens.get(index - 1).value.equals("String")) || (tokens.get(index - 1).value.equals("Boolean")) || (tokens.get(index - 1).value.equals("void"))) {
            symbolTable.put(tokens.get(index).value, " ");

        } else {
            if (!(symbolTable.containsKey(tokens.get(index).value))) {
                hasError = true;
                out.println("------------------------------------------|");
                out.println("you don't declare variable :" + tokens.get(index).value + "  in Line:" + (tokens.get(index).positions.line + 1) + " ( before use, declare variable . )");
                out.println("------------------------------------------|");

            }
        }
    }
////////////////////////////////////////////////////////////////////

    public void manage() {
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).type == Tokens.T_KEYWORD) {
                if (tokens.get(i).value.equals("int")) {
                    checkAssignInt(i);
                } else if (tokens.get(i).value.equals("String")) {
                    checkAssignString(i);
                } else if (tokens.get(i).value.equals("float")) {
                    checkAssignString(i);
                } else if ((tokens.get(i).value.equals("if")) || (tokens.get(i).value.equals("elseif")) || (tokens.get(i).value.equals("while")) || (tokens.get(i).value.equals("for"))) {
                    checkIWFstructure(i);
                    totalKeywords += 1;
                    totalParantheses += 1;
                } else if (tokens.get(i).value.equals("else")) {
                    checkElse(i);
                    totalKeywords += 1;
                    
                } else if ((tokens.get(i).value.equals("void"))) {
                    totalKeywords += 1;
                    totalParantheses += 1;

                } else {

                }
            } else if (tokens.get(i).type.equals(Tokens.T_IDENTIFIER)) {

                checkIdentifier(i);
            }
        }
        checks();
    }

}
