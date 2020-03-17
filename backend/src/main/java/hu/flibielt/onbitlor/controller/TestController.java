package hu.flibielt.onbitlor.controller;

import hu.flibielt.onbitlor.dto.TestDto;
import hu.flibielt.onbitlor.dto.TestResultDto;
import hu.flibielt.onbitlor.model.*;
import hu.flibielt.onbitlor.security.CurrentUser;
import hu.flibielt.onbitlor.security.UserPrincipal;
import hu.flibielt.onbitlor.service.TestResultService;
import hu.flibielt.onbitlor.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private TestResultService testResultService;

    @PostMapping("/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addTest(@RequestBody TestRequest request) {
        TestDto testDto = testService.save(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{testId}")
                .buildAndExpand(testDto.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Test successfully created"));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> addTestResult(@CurrentUser UserPrincipal currentUser, @RequestBody TestResultRequest request) {
        if (!testService.existsByName(request.getName())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Cannot find the given test"));
        }

        TestResultDto testResultDto = new TestResultDto();
        testResultDto.setDate(new Date());
        testResultDto.setTest(testService.findByName(request.getName()).getId());
        testResultDto.setPlayer(currentUser.getId());
        testResultDto.setResult(request.getScore());
        TestResultDto saved = testResultService.save(testResultDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{testId}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Test result successfully saved"));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ArrayList<TestDto> getAllTest() {
        return testService.findAll();
    }

    @GetMapping("/{testId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public TestDto findById(@PathVariable(value = "testId") Long testId) {
        return testService.findById(testId);
    }

    @GetMapping("/result/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public TestResultDto findResultById(@PathVariable(value = "id") Long id) {
        return testResultService.findById(id);
    }

    @GetMapping("/results/{name}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ArrayList<TestStatisticResponse> findResultsByTest(@PathVariable(value = "name") String test) {
        return testResultService.findAll(test);
    }

    @GetMapping("/checkNameAvailability/{name}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public TestNameAvailability checkNameAvailability(@PathVariable(value = "name") String name) {
        Boolean isAvailable = !testService.existsByName(name);
        return new TestNameAvailability(isAvailable);
    }

}
