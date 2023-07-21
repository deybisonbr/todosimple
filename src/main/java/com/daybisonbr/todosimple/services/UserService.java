package com.daybisonbr.todosimple.services;

import com.daybisonbr.todosimple.entities.User;
import com.daybisonbr.todosimple.repositories.TaskRepository;
import com.daybisonbr.todosimple.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findById(Long id){
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
                "Usuário não encontado! id: " + id + ", Tipo: " + User.class.getName()
        ));
    }

    @Transactional
    public User createUser(User obj){
        obj = this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User updateUser(User obj){
        findById(obj.getId());
        try {
            throw new RuntimeException(
                    "O usuário já existe."
            );
        }catch (Exception e){
            User newObj = findById(obj.getId());
            newObj.setPassword(obj.getPassword());

            return this.userRepository.save(newObj);
        }

    }

    public void delete(Long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException(
              "Não é possivel excluir, pois usuário não existe."
            );
        }
    }
}
