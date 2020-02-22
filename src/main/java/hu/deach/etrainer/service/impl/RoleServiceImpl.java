package hu.deach.etrainer.service.impl;

import hu.deach.etrainer.dto.RoleDto;
import hu.deach.etrainer.repository.RoleRepository;
import hu.deach.etrainer.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDto save(RoleDto roleDto) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        return null;
    }

    @Override
    public RoleDto findById(Long id) {
        return null;
    }

    @Override
    public Collection<RoleDto> findAll() {
        return null;
    }
}
