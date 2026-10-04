public class ExpireCommand implements Command {
    private RedisStore store;
    public ExpireCommand(RedisStore store) {
        this.store = store;
    }
    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: EXPIRE <key> <seconds>");
            return;
        }
        String key = args[1];
        long seconds = Long.parseLong(args[2]);
        store.expire(key, seconds);
        System.out.println("OK");
    }
}