package com.inventorymanagementsystem.repository;

import com.inventorymanagementsystem.model.Export;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportRepository extends JpaRepository<Export, Long> {
}
