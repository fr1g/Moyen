package su.kami.moyen.Helper;

public class PageHelper {
    public static final int limit = 6;
    public static int ParsePage(int page) {
        return (page - 1) * limit; // ?
    }
    public static int P(int p){
        return ParsePage(p);
    }
    //
}
