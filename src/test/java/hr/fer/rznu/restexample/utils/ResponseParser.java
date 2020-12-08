package hr.fer.rznu.restexample.utils;

import lombok.Data;

@Data
public class ResponseParser<T> {

    private T data;
}
