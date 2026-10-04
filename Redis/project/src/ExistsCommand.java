public class ExistsCommand implements Command{
    private RedisStore store;
    public ExistsCommand(RedisStore store) {
        this.store = store;
    }

    @Override
    public void execute(String[] args) {
        if (args.length<2) {
            System.out.println("Usage: EXISTS <key>");
            return;
        }
        String key = args[1];
        boolean exists = store.exists(key);
        System.out.println(exists?"1":"0");
    }
}
