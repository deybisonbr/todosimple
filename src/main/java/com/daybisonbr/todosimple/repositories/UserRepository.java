package com.daybisonbr.todosimple.repositories;

import com.daybisonbr.todosimple.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
