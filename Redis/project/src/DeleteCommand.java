public class DeleteCommand implements Command {

    private RedisStore store;

    public DeleteCommand(RedisStore store) {
        this.store = store;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: DEL <key>");
            return;
        }
        String key = args[1];
        store.delete(key);
        System.out.println("Done");
    }
}