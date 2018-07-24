package com.example.tabletguides.repos;


import com.example.tabletguides.entity.Instruction;
import org.springframework.data.repository.CrudRepository;

public interface InstructionRepo extends CrudRepository<Instruction, Long> {
}