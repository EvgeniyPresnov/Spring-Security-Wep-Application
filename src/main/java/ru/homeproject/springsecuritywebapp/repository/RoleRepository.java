package ru.homeproject.springsecuritywebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homeproject.springsecuritywebapp.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
