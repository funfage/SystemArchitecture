package com.gdut.zrf;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gdut.zrf.*;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void whenSerializingUsingJsonView_thenCorrect()
            throws JsonProcessingException {
        Item item = new Item(2, "book", "John");

        String result = new ObjectMapper()
                .writerWithView(Views.Public.class)
                .writeValueAsString(item);

        assertThat(result, containsString("book"));
        assertThat(result, containsString("2"));
        assertThat(result, not(containsString("John")));

        System.out.println(result);
    }

    @Test
    public void whenSerializingUsingJsonRawValue_thenCorrect()
            throws JsonProcessingException {

        RawBean bean = new RawBean("My bean", "{\"attr\":false}");

        String result = new ObjectMapper().writeValueAsString(bean);
        assertThat(result, containsString("My bean"));
        assertThat(result, containsString("{\"attr\":false}"));
        System.out.println(result);
    }

    @Test
    public void whenSerializingUsingJsonValue_thenCorrect()
            throws JsonParseException, IOException {

        String enumAsString = new ObjectMapper()
                .writeValueAsString(TypeEnumWithValue.TYPE1);

//        assertThat(enumAsString, is(""Type A""));
        System.out.println(enumAsString);
    }

    @Test
    public void whenSerializingUsingJsonRootName_thenCorrect()
            throws JsonProcessingException {

        UserWithRoot user = new UserWithRoot(1, "John");

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String result = mapper.writeValueAsString(user);

        assertThat(result, containsString("John"));
        assertThat(result, containsString("user"));

        System.out.println(result);
    }

}
