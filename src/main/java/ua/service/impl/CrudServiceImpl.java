package ua.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.service.CrudService;

public abstract class CrudServiceImpl<T, ID extends Serializable> implements CrudService<T, ID> {

    private final JpaRepository<T, ID> repository;

    public CrudServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T findOne(ID id) {
        Optional<T> optional = repository.findById(id);
        return optional.get();
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(T entity) {
        repository.save(entity);
    }

    @Override
    public void delete(ID id) {
        repository.deleteById(id);
    }

}