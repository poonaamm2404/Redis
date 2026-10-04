public class GetCommand implements Command {

    private RedisStore store;

    public GetCommand(RedisStore store) {
        this.store = store;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: GET <key>");
            return;
        }
        String key = args[1];
        if (store.isExpired(key)) {
            store.removeExpired(key);
            System.out.println("null");
            return;
        }
        System.out.println(store.get(key));
    }
}
