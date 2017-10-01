package ta.market.utils;

public class Assert {

    public static void assertAllPresent(Object... objects) {
        int i = 0;
        for (Object o : objects) {
            checkNotNull(o, "argument " + i++);
        }
    }

    private static void checkNotNull(Object obj, String parameter) {
        if(obj == null) {
            throw new IllegalArgumentException(parameter + " cannot be null");
        }
    }

}
