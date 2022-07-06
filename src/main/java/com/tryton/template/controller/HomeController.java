package com.tryton.template.controller;

import com.tryton.template.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@CommonsLog
@Tag(name = "HomeController", description = "Rest Template Service HomeController")
public class HomeController {

    private final TestService testService;

    @Autowired
    public HomeController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    @Operation(summary = "First endpoint in rest template service")
    public DummyObject getHome() {
        log.info("Starting getHome.");
        //call service
        DummyObject dummyObject = new DummyObject(1, new DummyObject.DummyNestedObject("OHH! It works!"));
        log.info("Finished getHome.");
        return dummyObject;
    }

    @GetMapping("/db")
    @Operation(summary = "Test call to DB, to check if connection works using Java MyBatis Mapper")
    public ResponseEntity<Integer> testCall() {
        log.info("Starting testCall.");
        int i = testService.testCall();
        log.info("Finished testCall.");
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    @GetMapping("/db-xml")
    @Operation(summary = "Test call to DB - XML Mapper, to check if connection works using XML MyBatis Mapper")
    public ResponseEntity<Integer> testCallXML() {
        log.info("Starting testCallXML.");
        int i = testService.testCallXml();
        log.info("Finished testCallXML.");
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    @GetMapping("error")
    @Operation(summary = "Throws RuntimeException - to test Errors handling")
    public void testErrorHandling() {
        log.info("Starting testErrorHandling.");
        throw new RuntimeException("Testing Error handling");
    }

    @AllArgsConstructor
    @Getter
    private static class DummyObject {
        private final int id;
        private final DummyNestedObject nested;

        @AllArgsConstructor
        @Getter
        private static class DummyNestedObject {
            private final String name;
        }
    }
}
