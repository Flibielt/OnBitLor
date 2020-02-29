package hu.deach.etrainer.service.impl;

import hu.deach.etrainer.dto.TestResultDto;
import hu.deach.etrainer.entity.TestResult;
import hu.deach.etrainer.service.TestResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class TestResultServiceImpl implements TestResultService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean save(TestResultDto testResultDto) {
        return null;
    }

    @Override
    public Boolean delete(TestResultDto testResultDto) {
        return null;
    }

    @Override
    public Boolean update(TestResultDto testResultDto) {
        return null;
    }

    @Override
    public TestResultDto findById(Long playerId, Long testId) {
        return null;
    }

    @Override
    public ArrayList<TestResultDto> findAll() {
        return null;
    }

    private TestResultDto convertToDto(TestResult testResult) {
        return modelMapper.map(testResult, TestResultDto.class);
    }

    private TestResult convertToEntity(TestResultDto testResultDto) {
        return modelMapper.map(testResultDto, TestResult.class);
    }
}
