package com.daybisonbr.todosimple.repositories;

import com.daybisonbr.todosimple.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser_Id(Long id);


    // Optional<Task> findById(Long id);

//    @Query(value = "SELECT t FROM Task t WHERE t.user_id = :id")
//    List<Task> findByUser_Id(@Param("id")  Long id);

//    @Query(value = "SELECT * FROM task t WHERE t.user_id = :id",nativeQuery = true)
//    List<Task> findByUser_Id(@Param("id")  Long id);
}
