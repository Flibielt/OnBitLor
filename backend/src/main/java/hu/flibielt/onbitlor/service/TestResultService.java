package hu.flibielt.onbitlor.service;

import hu.flibielt.onbitlor.dto.TestResultDto;

import java.util.ArrayList;

public interface TestResultService {

    Boolean save(TestResultDto testResultDto);

    Boolean delete(TestResultDto testResultDto);

    Boolean update(TestResultDto testResultDto);

    TestResultDto findById(Long playerId, Long testId);

    ArrayList<TestResultDto> findAll(Long testId);

}
