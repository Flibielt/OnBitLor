package hu.deach.etrainer.service;

import hu.deach.etrainer.dto.PlayerStatisticDto;

import java.util.ArrayList;

public interface PlayerStatisticService {

    PlayerStatisticDto save(PlayerStatisticDto playerStatisticDto);

    Boolean delete(Long playerId, Long gameId);

    PlayerStatisticDto update(PlayerStatisticDto playerStatisticDto);

    PlayerStatisticDto findById(Long playerId, Long gameId);

    ArrayList<PlayerStatisticDto> findByPlayerId(Long playerId);

    ArrayList<PlayerStatisticDto> findByGameId(Long gameId);

    ArrayList<PlayerStatisticDto> findAll();

}
