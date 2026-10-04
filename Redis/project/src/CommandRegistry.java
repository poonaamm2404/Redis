import java.util.HashMap;

public class CommandRegistry {
    private HashMap<String, Command> commands = new HashMap<>();
    public CommandRegistry(RedisStore store) {
        commands.put("SET", new SetCommand(store));
        commands.put("GET", new GetCommand(store));
        commands.put("DEL",new DeleteCommand(store));
        commands.put("EXISTS", new ExistsCommand(store));
        commands.put("INCR", new IncrCommand(store));
        commands.put("DECR", new DecrCommand(store));
        commands.put("EXPIRE", new ExpireCommand(store));
        commands.put("TTL", new TTLCommand(store));
    }
    public Command getCommand(String name) {
        return commands.get(name);
    }
}