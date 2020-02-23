package hu.deach.etrainer.service.impl;

import hu.deach.etrainer.dto.RoleDto;
import hu.deach.etrainer.entity.Role;
import hu.deach.etrainer.repository.RoleRepository;
import hu.deach.etrainer.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

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
    public ArrayList<RoleDto> findAll() {
        return null;
    }

    private RoleDto convertToDto(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }

    private Role convertToEntity(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);

        if (roleDto.getId() == null) {
            return null;
        }

        return role;
    }

}
