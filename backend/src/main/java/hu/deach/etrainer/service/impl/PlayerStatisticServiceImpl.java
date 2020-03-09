package hu.deach.etrainer.service.impl;

import com.google.common.collect.Lists;
import hu.deach.etrainer.dto.PlayerStatisticDto;
import hu.deach.etrainer.entity.PlayerStatistic;
import hu.deach.etrainer.repository.PlayerStatisticRepository;
import hu.deach.etrainer.service.PlayerStatisticService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerStatisticServiceImpl implements PlayerStatisticService {

    @Autowired
    private PlayerStatisticRepository playerStatisticRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean save(PlayerStatisticDto playerStatisticDto) {
        long count = playerStatisticRepository.count();
        PlayerStatistic playerStatistic = playerStatisticRepository.save(Objects.requireNonNull(convertToEntity(playerStatisticDto)));
        return count < playerStatisticRepository.count() && playerStatistic.getPlayer().getId() != null;
    }

    @Override
    public Boolean delete(Long id) {
        long count = playerStatisticRepository.count();
        playerStatisticRepository.deleteById(id);
        return count > playerStatisticRepository.count();
    }

    @Override
    public Boolean update(PlayerStatisticDto playerStatisticDto) {
        playerStatisticRepository.save(Objects.requireNonNull(convertToEntity(playerStatisticDto)));
        Optional<PlayerStatistic> updated = playerStatisticRepository.findById(playerStatisticDto.getId());
        return updated.filter(playerStatistic -> convertToDto(playerStatistic).equals(playerStatisticDto)).isPresent();
    }

    @Override
    public PlayerStatisticDto findById(Long id) {
        Optional<PlayerStatistic> updated = playerStatisticRepository.findById(id);
        return updated.map(this::convertToDto).orElse(null);
    }

    @Override
    public ArrayList<PlayerStatisticDto> findByPlayerId(Long playerId) {
        ArrayList<PlayerStatistic> playerStatistics = playerStatisticRepository.findAllByPlayer(playerId);
        return playerStatistics.stream()
                .map(this::convertToDto)
                .collect(Collectors.toCollection(Lists::newArrayList));
    }

    @Override
    public ArrayList<PlayerStatisticDto> findByGameId(Long gameId) {
        ArrayList<PlayerStatistic> playerStatistics = playerStatisticRepository.findAllByGame(gameId);
        return playerStatistics.stream()
                .map(this::convertToDto)
                .collect(Collectors.toCollection(Lists::newArrayList));
    }

    @Override
    public ArrayList<PlayerStatisticDto> findAll() {
        return Lists.newArrayList(playerStatisticRepository.findAll()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toCollection(Lists::newArrayList));
    }

    private PlayerStatisticDto convertToDto(PlayerStatistic playerStatistic) {
        return modelMapper.map(playerStatistic, PlayerStatisticDto.class);
    }

    private PlayerStatistic convertToEntity(PlayerStatisticDto playerStatisticDto) {
        PlayerStatistic playerStatistic = modelMapper.map(playerStatisticDto, PlayerStatistic.class);

        if (playerStatisticDto.getPlayerId() == null) {
            return null;
        }

        return playerStatistic;
    }
}
