package hu.deach.etrainer.service;

import hu.deach.etrainer.dto.PlayerStatisticDto;

import java.util.Collection;

public interface PlayerStatisticService {

    PlayerStatisticDto save(PlayerStatisticDto playerStatisticDto);

    Boolean delete(Long playerId, Long gameId);

    PlayerStatisticDto update(PlayerStatisticDto playerStatisticDto);

    PlayerStatisticDto findById(Long playerId, Long gameId);

    Collection<PlayerStatisticDto> findByPlayerId(Long playerId);

    Collection<PlayerStatisticDto> findByGameId(Long gameId);

    Collection<PlayerStatisticDto> findAll();

}
