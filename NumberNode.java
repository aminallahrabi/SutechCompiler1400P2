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
public class NumberNode {
    Token tok;
    NumberNode(Token tok){
        this.tok = tok;
    }
    @Override
    public String toString(){
        return ""+tok+"";
    }
    
}
