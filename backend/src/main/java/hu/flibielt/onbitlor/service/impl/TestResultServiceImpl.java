package hu.flibielt.onbitlor.service.impl;

import com.google.common.collect.Lists;
import hu.flibielt.onbitlor.dto.TestResultDto;
import hu.flibielt.onbitlor.entity.Player;
import hu.flibielt.onbitlor.entity.Test;
import hu.flibielt.onbitlor.entity.TestResult;
import hu.flibielt.onbitlor.model.TestStatisticResponse;
import hu.flibielt.onbitlor.repository.PlayerRepository;
import hu.flibielt.onbitlor.repository.TestRepository;
import hu.flibielt.onbitlor.repository.TestResultRepository;
import hu.flibielt.onbitlor.service.TestResultService;
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
    public TestResultDto save(TestResultDto testResultDto) {
        Player player = playerRepository.getOne(testResultDto.getPlayer());
        TestResult testResult = convertToEntity(testResultDto);
        player.setBit(player.getBit() + testResult.getTest().getBit() * testResult.getResult());
        playerRepository.save(player);
        return convertToDto(testResultRepository.save(testResult));
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
    public TestResultDto findById(Long id) {
        Optional<TestResult> testResult = testResultRepository.findById(id);
        return testResult.map(this::convertToDto).orElse(null);
    }

    @Override
    public ArrayList<TestStatisticResponse> findAll(String testName) {
        Long testId = testRepository.findByName(testName).getId();
        return Lists.newArrayList(testResultRepository.findAllByTestIdOrderByResultDesc(testId)).stream()
                .map(this::convertToTestStatisticResponse)
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

    private TestStatisticResponse convertToTestStatisticResponse(TestResult testResult) {
        TestStatisticResponse response = new TestStatisticResponse();
        response.setId(testResult.getId());
        response.setUsername(testResult.getPlayer().getUsername());
        response.setFirstName(testResult.getPlayer().getFirstName());
        response.setLastName(testResult.getPlayer().getLastName());
        response.setResult(testResult.getResult());
        response.setDate(testResult.getDate());
        return response;
    }
}
