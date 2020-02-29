package hu.deach.etrainer.service;

import hu.deach.etrainer.dto.TestDto;

import java.util.ArrayList;

public interface TestService {

    Boolean save(TestDto testDto);

    Boolean delete(TestDto testDto);

    Boolean update(TestDto testDto);

    TestDto findById(Long id);

    TestDto findByName(String name);

    ArrayList<TestDto> findAll();

}
