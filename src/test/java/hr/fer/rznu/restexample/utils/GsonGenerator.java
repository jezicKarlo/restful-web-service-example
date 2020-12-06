package hr.fer.rznu.restexample.utils;

import com.google.gson.Gson;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GsonGenerator {

    private static Gson gson;

    static {
        gson = new Gson();
    }

    public static Gson getGson() {
        return gson;
    }
}
