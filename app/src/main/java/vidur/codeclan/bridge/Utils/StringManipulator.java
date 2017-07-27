package vidur.codeclan.bridge.Utils;

/**
 * Created by vidur on 7/28/2017.
 */

public class StringManipulator {
    public static String expandUsername(String username){
        return username.replace(".", " ");
    }

    public static String condenseUsername(String username) {
        return username.replace(" ", ".");
    }

}
