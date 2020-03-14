package hu.flibielt.onbitlor.service.impl;

import com.google.common.collect.Lists;
import hu.flibielt.onbitlor.dto.ProgrammingStatisticDto;
import hu.flibielt.onbitlor.entity.Player;
import hu.flibielt.onbitlor.entity.Programming;
import hu.flibielt.onbitlor.entity.ProgrammingStatistic;
import hu.flibielt.onbitlor.repository.PlayerRepository;
import hu.flibielt.onbitlor.repository.ProgrammingRepository;
import hu.flibielt.onbitlor.repository.ProgrammingStatisticRepository;
import hu.flibielt.onbitlor.service.ProgrammingStatisticService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgrammingStatisticServiceImpl implements ProgrammingStatisticService {

    @Autowired
    private ProgrammingStatisticRepository programmingStatisticRepository;

    @Autowired
    private ProgrammingRepository programmingRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public ProgrammingStatisticDto save(ProgrammingStatisticDto programmingStatisticDto) {
        ProgrammingStatistic request = convertToEntity(programmingStatisticDto);

        assert request != null;
        ProgrammingStatistic saved = programmingStatisticRepository.save(request);
        return convertToDto(saved);
    }

    @Override
    public Boolean delete(Long id) {
        long count = programmingStatisticRepository.count();
        programmingStatisticRepository.deleteById(id);
        return count > programmingStatisticRepository.count();
    }

    @Override
    public Boolean update(ProgrammingStatisticDto programmingStatisticDto) {
        ProgrammingStatistic updated = programmingStatisticRepository.save(Objects.requireNonNull(convertToEntity(programmingStatisticDto)));
        return convertToDto(updated).equals(programmingStatisticDto);
    }

    @Override
    public ProgrammingStatisticDto findById(Long id) {
        Optional<ProgrammingStatistic> programmingStatistic = programmingStatisticRepository.findById(id);
        return programmingStatistic.map(this::convertToDto).orElse(null);
    }

    @Override
    public ArrayList<ProgrammingStatisticDto> findByDate(Date date) {
        ArrayList<ProgrammingStatistic> programmingStatistics = programmingStatisticRepository.findAllByDate(date);
        return programmingStatistics.stream()
                .map(this::convertToDto)
                .collect(Collectors.toCollection(Lists::newArrayList));
    }

    @Override
    public ArrayList<ProgrammingStatisticDto> findAll() {
        return Lists.newArrayList(programmingStatisticRepository.getAllByOrderByScoreDesc()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toCollection(Lists::newArrayList));
    }

    @Override
    public ArrayList<ProgrammingStatisticDto> findAllInProgramming(String name) {
        Long id = programmingRepository.findByName(name).getId();
        return Lists.newArrayList(programmingStatisticRepository.findAllByProgrammingIdOrderByScoreDesc(id)).stream()
                .map(this::convertToDto)
                .collect(Collectors.toCollection(Lists::newArrayList));
    }

    private ProgrammingStatisticDto convertToDto(ProgrammingStatistic programmingStatistic) {
        ProgrammingStatisticDto dto = new ProgrammingStatisticDto();
        dto.setId(programmingStatistic.getId());
        dto.setPlayer(programmingStatistic.getPlayer().getId());
        dto.setProgramming(programmingStatistic.getProgramming().getId());
        dto.setDate(programmingStatistic.getDate());
        dto.setScore(programmingStatistic.getScore());
        return dto;
    }

    private ProgrammingStatistic convertToEntity(ProgrammingStatisticDto programmingStatisticDto) {
        ProgrammingStatistic programmingStatistic = new ProgrammingStatistic();
        Player player = playerRepository.getOne(programmingStatisticDto.getPlayer());
        programmingStatistic.setPlayer(player);
        Programming programming = programmingRepository.getOne(programmingStatisticDto.getProgramming());
        programmingStatistic.setProgramming(programming);

        if (programmingStatisticDto.getPlayer() == null) {
            return null;
        }

        return programmingStatistic;
    }

}
