package hu.deach.etrainer.service.impl;

import com.google.common.collect.Lists;
import hu.deach.etrainer.dto.PlayerStatisticDto;
import hu.deach.etrainer.entity.PlayerStatistic;
import hu.deach.etrainer.entity.PlayerStatisticId;
import hu.deach.etrainer.repository.PlayerStatisticRepository;
import hu.deach.etrainer.service.PlayerStatisticService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    public Boolean delete(Long playerId, Long gameId, Date fromDate) {
        long count = playerStatisticRepository.count();
        PlayerStatisticId playerStatisticId = new PlayerStatisticId();
        playerStatisticId.setPlayer(playerId);
        playerStatisticId.setGame(gameId);
        playerStatisticId.setFromDate(fromDate);
        playerStatisticRepository.deleteById(playerStatisticId);
        return count > playerStatisticRepository.count();
    }

    @Override
    public Boolean update(PlayerStatisticDto playerStatisticDto) {
        playerStatisticRepository.save(Objects.requireNonNull(convertToEntity(playerStatisticDto)));
        PlayerStatisticId playerStatisticId = new PlayerStatisticId();
        playerStatisticId.setPlayer(playerStatisticDto.getPlayerId());
        playerStatisticId.setGame(playerStatisticDto.getGameId());
        playerStatisticId.setFromDate(playerStatisticDto.getFromDate());
        Optional<PlayerStatistic> updated = playerStatisticRepository.findById(playerStatisticId);
        return updated.filter(playerStatistic -> convertToDto(playerStatistic).equals(playerStatisticDto)).isPresent();
    }

    @Override
    public PlayerStatisticDto findById(Long playerId, Long gameId, Date fromDate) {
        PlayerStatisticId playerStatisticId = new PlayerStatisticId();
        playerStatisticId.setPlayer(playerId);
        playerStatisticId.setGame(gameId);
        playerStatisticId.setFromDate(fromDate);
        Optional<PlayerStatistic> updated = playerStatisticRepository.findById(playerStatisticId);
        return updated.map(this::convertToDto).orElse(null);
    }

    //todo: Create query
    @Override
    public ArrayList<PlayerStatisticDto> findByPlayerId(Long playerId) {
        return null;
    }

    //todo: create query
    @Override
    public ArrayList<PlayerStatisticDto> findByGameId(Long gameId) {
        return null;
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
