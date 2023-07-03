package ru.sber.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.demo.entity.Client;

/**
 * Хранилище с данными о пользователях
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
