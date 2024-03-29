package cybersoft.javabackend.java18.gira.user.repository;

import cybersoft.javabackend.java18.gira.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("select (count(u) > 0) from User u where u.username = ?1")
    boolean existsByUsername(String username);

    @Query("select (count(u) > 0) from User u where u.email = ?1")
    boolean existsByEmail(String email);

    User findByFullName(String fullName);

    User findByUsernameLikeIgnoreCase(String username);

    Optional<User> findByUsername(String username);

    User findByEmail(String email);
}