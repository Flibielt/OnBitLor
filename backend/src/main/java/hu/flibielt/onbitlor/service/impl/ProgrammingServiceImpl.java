package hu.flibielt.onbitlor.service.impl;

import com.google.common.collect.Lists;
import hu.flibielt.onbitlor.dto.ProgrammingDto;
import hu.flibielt.onbitlor.entity.Programming;
import hu.flibielt.onbitlor.model.ProgrammingRequest;
import hu.flibielt.onbitlor.repository.ProgrammingRepository;
import hu.flibielt.onbitlor.service.ProgrammingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgrammingServiceImpl implements ProgrammingService {

    @Autowired
    private ProgrammingRepository programmingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean save(ProgrammingDto programmingDto) {
        long count = programmingRepository.count();
        Programming programming = programmingRepository.save(Objects.requireNonNull(convertToEntity(programmingDto)));
        return count < programmingRepository.count() && programming.getId() != null;
    }

    @Override
    public ProgrammingDto save(ProgrammingRequest programmingRequest) {
        Programming programming = new Programming();
        programming.setName(programmingRequest.getName());
        programming.setDescription(programmingRequest.getDescription());
        programming.setBit(programmingRequest.getBit());
        return convertToDto(programmingRepository.save(programming));
    }

    @Override
    public Boolean delete(ProgrammingDto programmingDto) {
        long count = programmingRepository.count();
        programmingRepository.deleteById(programmingDto.getId());
        return count > programmingRepository.count();
    }

    @Override
    public Boolean update(ProgrammingDto programmingDto) {
        Programming updated = programmingRepository.save(Objects.requireNonNull(convertToEntity(programmingDto)));
        return convertToDto(updated).equals(programmingDto);
    }

    @Override
    public ProgrammingDto findById(Long id) {
        Optional<Programming> programming = programmingRepository.findById(id);
        return programming.map(this::convertToDto).orElse(null);
    }

    @Override
    public ArrayList<ProgrammingDto> findAll() {
        return Lists.newArrayList(programmingRepository.findAll()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toCollection(Lists::newArrayList));
    }

    @Override
    public Boolean existsByName(String name) {
        return programmingRepository.existsByName(name);
    }

    @Override
    public Long getId(String name) {
        return programmingRepository.findByName(name).getId();
    }

    private ProgrammingDto convertToDto(Programming programming) {
        return modelMapper.map(programming, ProgrammingDto.class);
    }

    private Programming convertToEntity(ProgrammingDto programmingDto) {
        Programming programming = modelMapper.map(programmingDto, Programming.class);

        if (programmingDto.getId() == null) {
            return null;
        }

        return programming;
    }

}
