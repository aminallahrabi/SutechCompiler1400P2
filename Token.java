/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler2;

class Token {
    String name;
    String type;
    String value;
    int pos_start;
    int pos_end;
    public Position positions = new Position(0, 0, 0);

    Token(String type, String value, Position p) {
        if (type == null) {
            this.type = "";
        }
        this.type = type;
        this.value = value;
        if (p != null) {
            this.pos_start = p.index;
            this.pos_end = pos_start + 1;
            this.positions = p;
        }
    }

    Token(String type, Position p) {
        if (type == null) {
            this.type = "";
        }
        this.type = type;
        this.value = "";
        if (positions != null) {
            this.pos_start = p.index;
            this.pos_end = pos_start + 1;
            this.positions = p;
        }

    }
    public boolean matches(String typeChar, String value) {
        return (this.type == typeChar) && (this.value == value);
    }

    @Override
    public String toString() {
        if ((this.value != null) && (this.value !="")) {
            return "type :[" + this.type + "] " + " value :[" + this.value + "]";
        }
        return "type :[" + this.type + "]";
    }
}

