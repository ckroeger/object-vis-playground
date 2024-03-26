package com.github.service.renderer;

import com.github.service.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class HTMLObjectRendererTest {

    @Test
    void renderObject() {

        Person person = new Person("John", 30);
        person.setAddress("123 Main St");
        person.setCity("New York");

        log.info(HTMLObjectRenderer.renderObject(person));
    }
}