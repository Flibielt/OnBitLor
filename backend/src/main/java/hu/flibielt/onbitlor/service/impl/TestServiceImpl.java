package hu.flibielt.onbitlor.service.impl;

import com.google.common.collect.Lists;
import hu.flibielt.onbitlor.dto.TestDto;
import hu.flibielt.onbitlor.entity.Test;
import hu.flibielt.onbitlor.model.TestRequest;
import hu.flibielt.onbitlor.repository.TestRepository;
import hu.flibielt.onbitlor.service.TestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean save(TestDto testDto) {
        long count = testRepository.count();
        Test test = testRepository.save(convertToEntity(testDto));
        return count < testRepository.count() && test.getId() != null;
    }

    @Override
    public TestDto save(TestRequest request) {
        Test test = new Test();
        test.setName(request.getName());
        test.setDescription(request.getDescription());
        test.setBit(request.getBit());
        return convertToDto(testRepository.save(test));
    }

    @Override
    public Boolean delete(TestDto testDto) {
        long count = testRepository.count();
        testRepository.delete(convertToEntity(testDto));
        return count > testRepository.count();
    }

    @Override
    public Boolean update(TestDto testDto) {
        Test test = testRepository.save(convertToEntity(testDto));
        return convertToDto(test).equals(testDto);
    }

    @Override
    public TestDto findById(Long id) {
        Optional<Test> test = testRepository.findById(id);
        return test.map(this::convertToDto).orElse(null);
    }

    @Override
    public TestDto findByName(String name) {
        return convertToDto(testRepository.findByName(name));
    }

    @Override
    public ArrayList<TestDto> findAll() {
        return Lists.newArrayList(testRepository.findAll()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toCollection(Lists::newArrayList));
    }

    @Override
    public Boolean existsByName(String name) {
        return testRepository.existsByName(name);
    }

    @Override
    public Long getId(String name) {
        return testRepository.findByName(name).getId();
    }

    private TestDto convertToDto(Test test) {
        return modelMapper.map(test, TestDto.class);
    }

    private Test convertToEntity(TestDto testDto) {
        return modelMapper.map(testDto, Test.class);
    }
}
