package machine.commands;

import java.util.Scanner;

public class CommandFactory {
        public static Command get(String action, Scanner scanner) {
            Command command = null;

            switch (action) {
                case "buy" : command = new BuyCommand(scanner); break;
                case "take" : command = new TakeCommand(scanner); break;
                case "fill" : command = new FillCommand(scanner); break;
            }
            return command;
        }
    }