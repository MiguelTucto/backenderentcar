package com.upc.pe.backenderentcar.user.domain.persistence;

import com.upc.pe.backenderentcar.user.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByPhone(int phone);
    User findByName(String name);
}
