# SutechCompiler1400P2
# project part2 ( lexer with parser )
 implemente the ERRORHANDLER and parser and develope the lexer that produce in project part1.
 develope the managment.java file and rename the file to errorHandler.
 add some variable in lexer.java file.
in this project
# display the:
{
Symbol table, Check all errors in one compile , parse decimal experssion 
}
# chek errors:
{
Assign , assign type , declare before use , {} , [] , () , ...
}
the symbol table update during the parsing.
after the lexer find all tokens , pass the arraylist to Error Handler
after Error handler shecl all the errors then set hasError true or false.
if the hasError was false then parser call it's constructor and call method parse for 
showing the symbol table and update the variables.
