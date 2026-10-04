public class SetCommand implements Command {

    private RedisStore store;
    public SetCommand(RedisStore store) {
        this.store = store;
    }

    @Override
    public void execute(String[] args) {
        if (args.length<3) {
            System.out.println("Usage: SET <key> <value>");
            return;
        }
        String key = args[1];
        String value = args[2];
        store.set(key, value);
        System.out.println("OK");
    }
}