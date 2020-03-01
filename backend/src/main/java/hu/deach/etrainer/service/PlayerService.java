package hu.deach.etrainer.service;

import hu.deach.etrainer.dto.PlayerDto;

import java.util.ArrayList;

public interface PlayerService {

    Boolean save(PlayerDto playerDto);

    Boolean delete(Long id);

    Boolean update(PlayerDto playerDto);

    PlayerDto findById(Long id);

    PlayerDto findByUsername(String username);

    PlayerDto findByEmail(String email);

    ArrayList<PlayerDto> findAll();

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
