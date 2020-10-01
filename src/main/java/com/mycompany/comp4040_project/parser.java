/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.comp4040_project;

/**
 *
 * @author Logan Newman
 */
import org.parboiled.BaseParser;
import static org.parboiled.BaseParser.EOI;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;


@BuildParseTree
public class parser extends BaseParser<Object> {

    public Rule Info() {
        return Sequence(
                Name(),',',' ',
                Address(),',',' ',
                Number(),',',' ',
                EOI
        );
    }

    Rule Name() {
        return Sequence(
                OneOrMore(Sequence(UcLetter(), OneOrMore(LcLetter()))),
                ' ',
                ZeroOrMore(Sequence(UcLetter(), ZeroOrMore('.'), OneOrMore(LcLetter())), ' '),
                OneOrMore(Sequence(UcLetter(), OneOrMore(LcLetter())))
        );
    }

    Rule Address() {
        return Sequence(
                OneOrMore(Digit()), ' ',
                OneOrMore(Sequence(UcLetter(), OneOrMore(LcLetter(), ZeroOrMore('.'))), ZeroOrMore(' ')),
                ',',' ',
                OneOrMore(Sequence(UcLetter(), OneOrMore(LcLetter())), ZeroOrMore(' ')),
                ',',' ',
                Sequence(UcLetter(), UcLetter()),
                ',',' ',
                OneOrMore(Sequence(ZeroOrMore(UcLetter()), OneOrMore(LcLetter())), ZeroOrMore(' '))
        );
    }

    Rule Number() {
        return Sequence(
                '(',
                OneOrMore(Digit()),
                ')',' ',
                OneOrMore(Digit()),
                '-',
                OneOrMore(Digit())
        );
    }

    Rule UcLetter() {
        return CharRange('A', 'Z');
    }

    Rule LcLetter() {
        return CharRange('a', 'z');
    }

    Rule Digit() {
        return  CharRange('0', '9');
    }

    Rule Spacing() {
        return ZeroOrMore(AnyOf(" \t\r\n\f").label("Whitespace"));
    }

    public String popAsStr(){
        return (String) pop();
    }

    public String convert(String name, String address, String number){
        return String.format("%s name, %s address, %s number",
                name != null ? name : 0,
                address != null ? address : 0,
                number != null ? number : 0);
    }


}