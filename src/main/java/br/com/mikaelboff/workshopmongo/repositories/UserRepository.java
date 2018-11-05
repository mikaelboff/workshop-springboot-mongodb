package br.com.mikaelboff.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import  br.com.mikaelboff.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}