package com.citronix.repository;

import com.citronix.entity.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {
    @Query("SELECT t FROM Tree t WHERE t.isProductive = :isProductive")
    List<Tree> findTreesByProductivity(@Param("isProductive") boolean isProductive);
}
