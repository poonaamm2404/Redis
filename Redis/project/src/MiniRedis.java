import java.util.Scanner;

public class MiniRedis {

    public static void main(String[] args) {
        RedisStore store = new RedisStore();
        CommandRegistry registry = new CommandRegistry(store);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("EXIT")) {
                break;
            }
            String[] parts = input.split(" ");
            String commandName = parts[0].toUpperCase();
            Command command = registry.getCommand(commandName);
            if (command != null) {
                command.execute(parts);
            } else {
                System.out.println("Unknown command");
            }
        }
    }
}