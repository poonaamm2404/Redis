public class DecrCommand implements Command {
    private RedisStore store;
    public DecrCommand(RedisStore store) {
        this.store = store;
    }
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: DECR <key>");
            return;
        }
        String key = args[1];
        String currentValue = store.get(key);
        if (currentValue == null) {
            currentValue = "0";
        }
        try {
            int number = Integer.parseInt(currentValue);
            number--;
            store.set(key, String.valueOf(number));
            System.out.println(number);
        } catch (NumberFormatException e) {
            System.out.println("Error: value is not an integer");
        }
    }
}