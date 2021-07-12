/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler2;

/**
 *
 * @author AminAll
 */
public class UnaryOpNode {

    Token op_tok;
    Token node;

    UnaryOpNode(Token op_tok, Token node) {
        this.op_tok = op_tok;
        this.node = node;
    }

    @Override
    public String toString() {
        return "(" + op_tok + "," + node + ")";
    }
}
