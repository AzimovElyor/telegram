package com.example.springmvc.repository;

import com.example.springmvc.model.BaseModel;

import java.util.List;
import java.util.UUID;

public interface BaseRepository<T extends BaseModel> {
    T save(T model);
    List<T> getAll();
    T findById(UUID id);
    int update(T model);
    int delete(UUID id);
}
