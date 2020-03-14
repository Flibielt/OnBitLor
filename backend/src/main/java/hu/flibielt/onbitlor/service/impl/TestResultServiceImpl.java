package hu.flibielt.onbitlor.service.impl;

import com.google.common.collect.Lists;
import hu.flibielt.onbitlor.dto.TestResultDto;
import hu.flibielt.onbitlor.entity.Player;
import hu.flibielt.onbitlor.entity.Test;
import hu.flibielt.onbitlor.entity.TestResult;
import hu.flibielt.onbitlor.entity.TestResultId;
import hu.flibielt.onbitlor.repository.PlayerRepository;
import hu.flibielt.onbitlor.repository.TestRepository;
import hu.flibielt.onbitlor.repository.TestResultRepository;
import hu.flibielt.onbitlor.service.TestResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestResultServiceImpl implements TestResultService {

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private PlayerRepository playerRepository;

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
    public ArrayList<TestResultDto> findAll(String testName) {
        Long testId = testRepository.findByName(testName).getId();
        return Lists.newArrayList(testResultRepository.findAllByTestIdOrderByResultDesc(testId)).stream()
                .map(this::convertToDto)
                .collect(Collectors.toCollection(Lists::newArrayList));
    }

    private TestResultDto convertToDto(TestResult testResult) {
        TestResultDto dto = new TestResultDto();
        dto.setPlayer(testResult.getPlayer().getId());
        dto.setTest(testResult.getTest().getId());
        dto.setDate(testResult.getDate());
        dto.setResult(testResult.getResult());
        return dto;
    }

    private TestResult convertToEntity(TestResultDto testResultDto) {
        TestResult testResult = new TestResult();
        Player player = playerRepository.getOne(testResultDto.getPlayer());
        Test test = testRepository.getOne(testResultDto.getTest());
        testResult.setPlayer(player);
        testResult.setTest(test);
        testResult.setDate(testResultDto.getDate());
        testResult.setResult(testResultDto.getResult());
        return testResult;
    }
}
