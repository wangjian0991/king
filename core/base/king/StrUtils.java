package base.king;

public class StrUtils {
    public static String upInitial(String str)
    {
        char[] chars = str.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }
}
