public class DebugUtilities {

    public static String toBinaryString(byte b) {
        StringBuilder builder = new StringBuilder("0b");

        for(int i = Byte.SIZE - 1; i >= 0; i--) {
            builder.append((b & (1 << i)) >> i);
        }

        return builder.toString();
    }
}
