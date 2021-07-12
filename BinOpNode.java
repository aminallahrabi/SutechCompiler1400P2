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
public class BinOpNode {
    Token left_node;
    Token op_tok;
    Token right_node;
    BinOpNode(Token left_node,Token op_tok,Token right_node) {
        this.left_node = left_node;
        this.op_tok = op_tok;
        this.right_node = right_node;
    }

    @Override
    public String toString() {
        return "(" + left_node + "," + op_tok + "," + right_node + ")";
    }
}
