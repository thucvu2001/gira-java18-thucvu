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
                select r.id as id, r.name as name, r.code as code, r.description as description
                from users u
                join usergroups_users uug on u.id = uug.user_id
                join usergroups ug on uug.usergroup_id = ug.id
                join roles_usergroups rug on ug.id = rug.usergroup_id
                join roles r on rug.role_id = r.id
                where u.username = ?1
            """)
    List<Role> findAllRolesByUsername(String username);
}
