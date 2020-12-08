package hr.fer.rznu.restexample.utils;

import com.google.gson.reflect.TypeToken;
import hr.fer.rznu.restexample.dto.NoteDTO;
import lombok.Data;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;

@Data
public class ResponseParser<T> {

    private T data;

    public static NoteDTO parse(ResultActions post) throws UnsupportedEncodingException {
        String content = post.andReturn().getResponse().getContentAsString();

        ResponseParser<NoteDTO> response = GsonGenerator.getGson().fromJson(content,
                new TypeToken<ResponseParser<NoteDTO>>() {
                }.getType());

        return response.getData();
    }
}
