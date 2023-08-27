package com.example.springmvc.repository;

import com.example.springmvc.model.BaseModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseRepository<T extends BaseModel> {
    Optional<T> save(T model);
    Optional<List<T>> getAll();
    Optional<T>findById(UUID id);
    int update(T model);
    int delete(UUID id);
}
