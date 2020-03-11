package hu.flibielt.onbitlor.service;

import hu.flibielt.onbitlor.dto.TestDto;
import hu.flibielt.onbitlor.model.TestRequest;

import java.util.ArrayList;

public interface TestService {

    Boolean save(TestDto testDto);

    TestDto save(TestRequest request);

    Boolean delete(TestDto testDto);

    Boolean update(TestDto testDto);

    TestDto findById(Long id);

    TestDto findByName(String name);

    ArrayList<TestDto> findAll();

    Boolean existsByName(String name);

    Long getId(String name);

}
