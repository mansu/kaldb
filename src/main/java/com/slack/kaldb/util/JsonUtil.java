package com.slack.kaldb.util;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.ByteBuffer;

public class JsonUtil {
  private static JsonUtil ourInstance = new JsonUtil();
  private final ObjectMapper mapper;

  public static JsonUtil getInstance() {
    return ourInstance;
  }

  public static <T> ByteBuffer toByteBuffer(T obj) throws JsonProcessingException {
    return ByteBuffer.wrap(writeAsString(obj).getBytes());
  }

  public static <T> String writeAsString(T obj) throws JsonProcessingException {
    return ourInstance.mapper.writeValueAsString(obj);
  }

  // TODO: Ensure this class templating works here.
  public static <T> T read(String s, Class<T> cls) throws IOException {
    return ourInstance.mapper.readValue(s, cls);
  }

  private JsonUtil() {
    mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
  }
}
