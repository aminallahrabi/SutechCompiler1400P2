/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler2;

import static java.lang.System.out;

public class Position {

    int index;
    int line ;
    int col ;
//    String fileName = null;
//    String text = null;

    Position(int index, int line, int col) {
        this.index = index;
        this.line = line;
        this.col = col;
//        this.fileName = fileName;
//        this.text = text;
    }

    public void advance(String current_char) {
        this.index += 1;
        this.col += 1;
       
    }
    public Position copy(){
        return new Position(index, line, col);
    }

}
