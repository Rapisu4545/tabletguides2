package com.example.tabletguides.repos;


import com.example.tabletguides.entity.Instruction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InstructionRepo extends CrudRepository<Instruction, Long> {

    @Query("SELECT c from Instruction c ORDER BY id ASC")
    Iterable<Instruction> findAllandSort();

}