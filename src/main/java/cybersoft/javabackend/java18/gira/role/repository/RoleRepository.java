package cybersoft.javabackend.java18.gira.role.repository;

import cybersoft.javabackend.java18.gira.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByCode(String code);

    void deleteByCode(String code);

    Optional<Role> findByName(String name);

    @Query(nativeQuery = true, value = """
                select r.name as name
                from users u
                join users_roles ur on u.id = ur.users_id
                join roles r on r.id = ur.roles_id
                where u.username = %?1%
            """)
    List<Role> findAllRolesByUsername(String username);
}
