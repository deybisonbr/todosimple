package com.daybisonbr.todosimple.services;

import com.daybisonbr.todosimple.entities.Task;
import com.daybisonbr.todosimple.entities.User;
import com.daybisonbr.todosimple.repositories.TaskRepository;
import com.daybisonbr.todosimple.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;

    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException(
                "Missão não encontado! id: " + id + ", Tipo: " + Task.class.getName()
        ));
    }

    @Transactional
    public Task createTask(Task obj){
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;

    }

    @Transactional
    public Task updateTask(Task obj){
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    public void deleteTask(Long id){
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException(
                    "Não é possivel excluir, pois a tarefa não existe."
            );
        }
    }

    public List<Task> findAllByUserId(Long userId){
        List<Task> tasks = this.taskRepository.findByUser_Id(userId);
            return tasks;

    }



}
