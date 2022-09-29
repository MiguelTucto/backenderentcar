package com.upc.pe.backenderentcar.test.cucumber;

import com.upc.pe.backenderentcar.BackenderentcarApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = BackenderentcarApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = BackenderentcarApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {
}
