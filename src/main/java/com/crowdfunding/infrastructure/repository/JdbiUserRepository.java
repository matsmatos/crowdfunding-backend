package com.crowdfunding.infrastructure.repository;

import com.crowdfunding.domain.entity.User;
import com.crowdfunding.domain.repository.UserRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JdbiUserRepository implements UserRepository {

    private final Jdbi jdbi;

    public JdbiUserRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public User save(User user) {
        return jdbi.withHandle(handle -> {
            Long id = handle.createUpdate(
                    "INSERT INTO users (name, email, password, role) VALUES (:name, :email, :password, :role)")
                    .bind("name", user.getName())
                    .bind("email", user.getEmail())
                    .bind("password", user.getPassword())
                    .bind("role", user.getRole().name())
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Long.class)
                    .one();

            user.setId(id);
            return user;
        });
    }

    @Override
    public Optional<User> findById(Long id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(User.class)
                        .findOne());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE email = :email")
                        .bind("email", email)
                        .mapToBean(User.class)
                        .findOne());
    }
}
