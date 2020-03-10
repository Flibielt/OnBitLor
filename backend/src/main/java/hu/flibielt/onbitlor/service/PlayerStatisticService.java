package hu.flibielt.onbitlor.service;

import hu.flibielt.onbitlor.dto.PlayerStatisticDto;

import java.util.ArrayList;

public interface PlayerStatisticService {

    Boolean save(PlayerStatisticDto playerStatisticDto);

    Boolean delete(Long id);

    Boolean update(PlayerStatisticDto playerStatisticDto);

    PlayerStatisticDto findById(Long id);

    ArrayList<PlayerStatisticDto> findByPlayerId(Long playerId);

    ArrayList<PlayerStatisticDto> findByGameId(Long gameId);

    ArrayList<PlayerStatisticDto> findAll();

}
