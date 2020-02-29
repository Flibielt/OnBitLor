package hu.deach.etrainer.service.impl;

import com.google.common.collect.Lists;
import hu.deach.etrainer.dto.TestResultDto;
import hu.deach.etrainer.entity.TestResult;
import hu.deach.etrainer.entity.TestResultId;
import hu.deach.etrainer.repository.TestResultRepository;
import hu.deach.etrainer.service.TestResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestResultServiceImpl implements TestResultService {

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean save(TestResultDto testResultDto) {
        long count = testResultRepository.count();
        testResultRepository.save(convertToEntity(testResultDto));
        return count < testResultRepository.count();
    }

    @Override
    public Boolean delete(TestResultDto testResultDto) {
        long count = testResultRepository.count();
        testResultRepository.delete(convertToEntity(testResultDto));
        return count < testResultRepository.count();
    }

    @Override
    public Boolean update(TestResultDto testResultDto) {
        TestResult testResult = testResultRepository.save(convertToEntity(testResultDto));
        return convertToDto(testResult).equals(testResultDto);
    }

    @Override
    public TestResultDto findById(Long playerId, Long testId) {
        TestResultId testResultId = new TestResultId();
        testResultId.setPlayer(playerId);
        testResultId.setTest(testId);
        Optional<TestResult> testResult = testResultRepository.findById(testResultId);
        return testResult.map(this::convertToDto).orElse(null);
    }

    @Override
    public ArrayList<TestResultDto> findAll() {
        return Lists.newArrayList(testResultRepository.findAll()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toCollection(Lists::newArrayList));
    }

    private TestResultDto convertToDto(TestResult testResult) {
        return modelMapper.map(testResult, TestResultDto.class);
    }

    private TestResult convertToEntity(TestResultDto testResultDto) {
        return modelMapper.map(testResultDto, TestResult.class);
    }
}
