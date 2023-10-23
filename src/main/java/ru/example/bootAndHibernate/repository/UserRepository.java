package ru.example.bootAndHibernate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.example.bootAndHibernate.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
