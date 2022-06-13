package be.intec.newreserveblog.repository;

import be.intec.newreserveblog.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RoleRepository extends JpaRepository<Role, Long> {

     Role findByName(String name);

}