public class TTLCommand implements Command {
    private RedisStore store;
    public TTLCommand(RedisStore store) {
        this.store = store;
    }
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: TTL <key>");
            return;
        }
        String key = args[1];
        System.out.println(store.getTTL(key));
    }
}