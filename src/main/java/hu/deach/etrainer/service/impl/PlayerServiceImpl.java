package hu.deach.etrainer.service.impl;

import hu.deach.etrainer.dto.PlayerDto;
import hu.deach.etrainer.repository.PlayerRepository;
import hu.deach.etrainer.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public PlayerDto save(PlayerDto playerDto) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public PlayerDto update(PlayerDto playerDto) {
        return null;
    }

    @Override
    public PlayerDto findById(Long id) {
        return null;
    }

    @Override
    public PlayerDto findByUsername(String username) {
        return null;
    }

    @Override
    public PlayerDto findByEmail(String email) {
        return null;
    }

    @Override
    public Collection<PlayerDto> findAll() {
        return null;
    }
}
