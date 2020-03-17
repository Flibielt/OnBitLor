package hu.flibielt.onbitlor.service;

import hu.flibielt.onbitlor.dto.TestResultDto;
import hu.flibielt.onbitlor.model.TestStatisticResponse;

import java.util.ArrayList;

public interface TestResultService {

    TestResultDto save(TestResultDto testResultDto);

    Boolean delete(TestResultDto testResultDto);

    Boolean update(TestResultDto testResultDto);

    TestResultDto findById(Long id);

    ArrayList<TestStatisticResponse> findAll(String testName);

}
