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
import org.parboiled.Parboiled;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.common.StringUtils;
import static org.parboiled.support.ParseTreeUtils.printNodeTree;
import org.parboiled.support.ParsingResult;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
       parser parser = Parboiled.createParser(parser.class);
       Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Enter information.");
            System.out.println("Leave any value blank if you wish to end.");
            System.out.println("Enter your name:");
            String name = in.nextLine();
            System.out.println("Enter your address:");
            String address = in.nextLine();
            System.out.println("Enter your phone number:");
            String phone_number = in.nextLine();
            String input = name +", " +address+", " +phone_number;
            if (StringUtils.isEmpty(name)||StringUtils.isEmpty(address)|| StringUtils.isEmpty(phone_number)) break;

            ParsingResult<?> result = new RecoveringParseRunner(parser.Info()).run(input);

            System.out.println(input + " = " + result.parseTreeRoot.getValue() + '\n');
            System.out.println(printNodeTree(result) + '\n');

            if (!result.matched) {
                System.out.println(StringUtils.join(result.parseErrors, "---\n"));
            }
        }
    }
}