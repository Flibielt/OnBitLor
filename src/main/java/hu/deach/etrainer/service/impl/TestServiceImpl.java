package hu.deach.etrainer.service.impl;

import hu.deach.etrainer.dto.TestDto;
import hu.deach.etrainer.entity.Test;
import hu.deach.etrainer.service.TestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class TestServiceImpl implements TestService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean save(TestDto testDto) {
        return null;
    }

    @Override
    public Boolean delete(TestDto testDto) {
        return null;
    }

    @Override
    public Boolean update(TestDto testDto) {
        return null;
    }

    @Override
    public TestDto findById(Long id) {
        return null;
    }

    @Override
    public TestDto findByName(String name) {
        return null;
    }

    @Override
    public ArrayList<TestDto> findAll() {
        return null;
    }

    private TestDto convertToDto(Test test) {
        return modelMapper.map(test, TestDto.class);
    }

    private Test convertToEntity(TestDto testDto) {
        return modelMapper.map(testDto, Test.class);
    }
}
