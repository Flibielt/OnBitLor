package hu.deach.etrainer.service;

import hu.deach.etrainer.dto.RoleDto;

import java.util.Collection;

public interface RoleService {

    RoleDto save(RoleDto roleDto);

    Boolean delete(Long id);

    RoleDto update(RoleDto roleDto);

    RoleDto findById(Long id);

    Collection<RoleDto> findAll();

}
