public class Parser {

    Scanner s;

    Parser(Scanner s){
        this.s = s;
        this.s.getSym();
    }

    static void error(String s){
        System.out.println(s);
    }

    void klasse() {    // class = "class" ident classbody.

        if (s.sym == s.CLASS){ //"class"
            s.getSym();
            ident();    //ident
            classBody(); //classbody
        } else {
            error("'class' erwartet, " + s.id + " gefunden");
        }
    }

    void ident() {
        if (s.sym == s.ident){
            s.getSym();
        } else {
            error("Bezeichner erwartet, " + s.id + " gefunden");
        }

    }

    void classBody() {  // "{" declarations "}"
        if (s.sym == s.lcparen) { // {
            s.getSym();
            declarations();  //declarations
            if (s.sym == s.rcparen){ // }
                s.getSym();
            } else {
                error("classBody: } erwartet, " + s.id + "gefunden");
            }
        } else {
            error("classBody: { erwartet, " + s.id + " gefunden");
        }
    }

    void declarations(){  // {“final” type ident “=” expression “;” { type ident “;”} {method_declaration}
        while (s.sym == s.FINAL){ // final
            s.getSym();
            type();              //type
            ident();             //ident
            if (s.sym == s.equals){ // =
                s.getSym();
                expression();     //expression
            }else {
                error("declarations: = erwartet, " + s.id + " gefunden");
            }
            if (s.sym == s.semicolon){  // ;
                s.getSym();
            } else {
                error("declarations: ; erwartet, " + s.id + " gefunden");
            }
        }

        while (s.sym == s.INT){  //{ type
            s.getSym();
            ident();   //ident
            if (s.sym == s.semicolon){  //;
                s.getSym();
            } else {
                error("declarations: ; erwartet, " + s.id + " gefunden");
            }                 //}
        }

        while (s.sym == s.PUBLIC) { //{
            methodDeclaration();  //method_declaration
        }    // }
    }

    void expression() {  //simple_expression [(“==” | “<” | ”<= ” | “>” | ”>= ”) simple_expression].
        simpleExpression();  //simple_expression
        if (s.sym == s.comp ||   // [ ==, <, <=, >, >=
                s.sym == s.leq ||
                s.sym == s.lt ||
                s.sym == s.gt ||
                s.sym == s.geq){
            s.getSym();
            simpleExpression();   //simple_expression
        }  // ]
    }

    void simpleExpression(){  //term {(“+” | ”-” ) term}.
        term();       //term
        while (s.sym == s.plus ||   // { +, -
                s.sym == s.minus){
            s.getSym();
            term();       //term
        }  //}
    }

    void term(){  //factor {(“*” | ”/ “ ) factor}
        factor();  //factor
        while (s.sym == s.times ||  // { * , /
                s.sym == s.div){
            s.getSym();
            factor();   //factor
        }  //}
    }

    void factor() {  // ident | number | “(“ expression”)” | intern_procedure_call.
        if (s.sym == s.number){  //number |
            s.getSym();
        }
        if (s.sym == s.lparen){ // (
            expression();      //expression
            if (s.sym == s.rparen){ // )
                s.getSym();
            } else {
                error("factor: ) erwartet, " + s.id + " gefunden");
            }
        }
        if (s.sym == s.ident){   // ident
            s.getSym();
            if (s.sym == s.lparen){
                actualParameters();  //intern_procedure_call = ident actual_parameters.
            }
        }
    }

    void actualParameters(){  //  “(“ [expression {“,” expression}]“)”
        if (s.sym == s.lparen){ // (
            s.getSym();
        } else {
            error ("actualParameters: ( erwartet, " + s.id + " gefunden");
        }
        if (s.sym == s.ident){  // [
            expression();       // expression
            while (s.sym == s.comma){  //{ ,
                s.getSym();
                expression();  // expression
            } //}
        } // ]
        if (s.sym == s.rparen){
            s.getSym();
        } else {
            error("actualParameters: ) erwartet, " + s.id + " gefunden");
        }
    }

    void type(){  // int
        if (s.sym == s.INT){
            s.getSym();
        } else {
            error("'int' erwartet, " + s.id + " gefunden");
        }
    }

    void methodDeclaration(){ // methodhead methodbody
        methodHead();
        methodBody();
    }

    void methodHead(){ // “public” method_type ident formal_parameters.
        if (s.sym == s.PUBLIC){ //public
            s.getSym();
            methodType();
            ident();
            formalParameters();
        } else {
            error("methodHead: 'public' erwartet, " + s.id + " gefunden");
        }
    }

    void methodType(){ //  “void” | type.
        if (s.sym == s.VOID ||
                s.sym == s.INT) {
            s.getSym();
        } else {
            error("methodType: 'void' oder 'int' erwartet, " + s.id + " gefunden");
        }
    }

    void formalParameters(){ //  “(“ [fp_section {“,” fp_section}] “)”.
        if (s.sym == s.lparen){ // (
            s.getSym();
            if (s.sym == s.INT){  // [ fpSection starts with int
                fpSection();     //fp_section
                while (s.sym == s.comma){  // { ,
                    s.getSym();
                    fpSection();   //fpsection
                }  // }
            }  // ]
            if (s.sym == s.rparen){  // )
                s.getSym();
            } else {
                error("formalParameters: ) erwartet, " + s.id + " gefunden");
            }
        } else {
            error("formalParameters: ( erwartet, " + s.id + " gefunden");
        }
    }

    void fpSection(){ //type ident
        type();
        ident();
    }

    void methodBody(){  // “{“ {local_declaration} statement_sequence “}”.
        if (s.sym == s.lcparen){  // {
            s.getSym();
            while (s.sym == s.INT){ // { localDeclaration starts with int
                localDeclaration();
            } // }
            statementSequence(); //statementSequence
            if (s.sym == s.rcparen){
                s.getSym();
            } else {
                error("methodBody: } erwartet, " + s.id + " gefunden");
            }
        } else {
            error("methodBody: { erwartet, " + s.id + " gefunden");
        }
    }

    void localDeclaration(){ //type ident “;”.
        type();
        ident();
        if (s.sym == s.semicolon){
            s.getSym();
        } else {
            error ("localDeclaration: ; erwartet, " + s.id + " gefunden");
        }
    }

    void statementSequence(){ //statement {statement}
        statement();
        while (s.sym == s.ident ||
                s.sym == s.IF ||
                s.sym == s.WHILE ||
                s.sym == s.RETURN){
            statement();
        }
    }

    void statement(){ // assignment | procedure_call | if_statement | while_statement | return_statement.
        if (s.sym == s.ident){ //asssignment and procedure call
            s.getSym();
            if (s.sym == s.equals) { //assignment
                assignment();
            } else if (s.sym == s.lparen){ //procedureCall
                procedureCall();
            } else {
                error("statement: = oder ( erwartet," + s.id + " gefunden");
            }
        } else if (s.sym == s.IF){ //ifStatement
            ifStatement();
        } else if (s.sym == s.WHILE ) { //whileStatement
            whileStatement();
        } else if (s.sym == s.RETURN){ //returnStatement
            returnStatement();
        } else {
            error("statement: ident, if, while oder return erwartet, " + s.id + " gefunden");
        }
    }

    void assignment(){  // “=” expression “;”.
        if (s.sym == s.equals){ // =
            s.getSym();
            expression(); //expression
            if (s.sym == s.semicolon){ //;
                s.getSym();
            } else {
                error("assignment: ; erwartet, " + s.id + " gefunden");
            }
        } else {
            error("assignment: = erwartet, " + s.id + " gefunden");
        }
    }

    void procedureCall(){ //intern_procedure_call “;”
        actualParameters();
        if (s.sym == s.semicolon){
            s.getSym();
        } else {
            error("procedureCall: ; erwartet, " + s.id + " gefunden");
        }
    }

    void ifStatement(){ //  “if” “(“ expression “)” “{“ statement_sequence “}” “else” “{" statement_sequence “}”
        if (s.sym == s.IF){ // if
            s.getSym();
            if (s.sym == s.lparen) { // (
                s.getSym();
                expression(); // expression
                if (s.sym == s.rparen){ // )
                    s.getSym();
                    if (s.sym == s.lcparen){ // {
                        s.getSym();
                        statementSequence(); // statementSequence
                        if (s.sym == s.rcparen){ // }
                            s.getSym();
                            if (s.sym == s.ELSE) { // else
                                s.getSym();
                                if (s.sym == s.lcparen){ // {
                                    s.getSym();
                                    statementSequence();  //statementSequence
                                    if (s.sym == s.rcparen){ // }
                                        s.getSym();
                                    }else {
                                        error("if: } erwartet, " + s.id + " gefunden");
                                    }
                                } else {
                                    error("if: { erwartet, " + s.id + " gefunden");
                                }
                            } else {
                                error("if: 'else' erwartet, " + s.id + " gefunden");
                            }
                        } else {
                            error("if: } erwartet, " + s.id + " gefunden");
                        }
                    } else {
                        error("if: } erwartet, " + s.id + "gefunden");
                    }
                } else {
                    error ( "if: ) erwartet, " + s.id + " gefunden");
                }
            } else {
                error("if: ( erwartet" + s.id + " gefunden");
            }
        } else {
            error("if: 'if' erwartet, " + s.id + " gefunden");
        }
    }

    void whileStatement(){  // “while” “(“ expression “)” “{“ statement_sequence “}”
        if (s.sym == s.WHILE) { // while
            s.getSym();
            if (s.sym == s.lparen){  // (
                s.getSym();
                expression();   // expression
                if (s.sym == s.rparen){ // )
                    s.getSym();
                    if (s.sym == s.lcparen){ // {
                        s.getSym();
                        statementSequence();  // statementSequence
                        if (s.sym == s.rcparen){  // }
                            s.getSym();
                        } else {
                            error("while: } erwartet " + s.id + " gefunden");
                        }
                    }else {
                        error("while: { erwartet " + s.id + " gefunden");
                    }
                } else {
                    error("while: ) erwartet, " + s.id + " gefunden");
                }
            } else {
                error("while: ( erwartet, " + s.id + " gefunden");
            }
        } else {
            error("while: 'while' erwartet, " + s.id + " gefunden");
        }
    }

    void returnStatement(){ //“return” [ simple_expression ] “;”.
        if (s.sym == s.RETURN) {
            s.getSym();
            if (s.sym == s.ident) {
                simpleExpression();
            } else if (s.sym == s.semicolon) {
                s.getSym();
            } else {
                error("return: Bezeichner oder ; erwartet, " + s.id + " gefunden");
            }
        } else {
            error("return: 'return' erwartet, " + s.id + "gefunden");
        }
    }


}
