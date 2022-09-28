package br.com.app.myFinancy.repository;

import br.com.app.myFinancy.model.CardBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<CardBill, UUID> {
}
