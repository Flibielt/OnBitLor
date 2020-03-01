package hu.deach.etrainer.controller;

import hu.deach.etrainer.dto.TestDto;
import hu.deach.etrainer.model.ApiResponse;
import hu.deach.etrainer.model.TestRequest;
import hu.deach.etrainer.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping
    public ResponseEntity<?> addTest(@RequestBody TestRequest request) {
        TestDto testDto = testService.save(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{testId}")
                .buildAndExpand(testDto.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Test successfully created"));
    }

    @GetMapping("/all")
    public ArrayList<TestDto> getAllTest() {
        return testService.findAll();
    }

    @GetMapping("/{testId}")
    public TestDto findById(@PathVariable(value = "testId") Long testId) {
        return testService.findById(testId);
    }

}
