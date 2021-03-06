package ru.course.service.impl;

import ru.course.exception.DepartmentDeletionException;
import ru.course.exception.DepartmentNotFoundException;
import ru.course.repository.DepartmentsRepository;
import ru.course.entity.Departments;
import ru.course.service.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentsServiceImpl implements DepartmentsService {

    @Autowired
    private DepartmentsRepository departmentsRepository;

    @Override
    public List<Departments> departmentsList() {
        return (List<Departments>) departmentsRepository.findAll();
    }

    @Override
    public Departments findDepartmentsById(Long id) {
        Optional<Departments> department = departmentsRepository.findById(id);

        if (department.isPresent()) {
            return department.get();
        } else {
            throw new DepartmentNotFoundException("Department not found!");
        }
    }

    @Override
    public Departments findDepartmentsByName(String name) {
        Departments department = departmentsRepository.findByName(name);

        if (department != null) {
            return department;
        } else {
            throw new DepartmentNotFoundException("Department not found!");
        }
    }

    @Override
    public void deleteDepartments(Long id) {
        try{
            departmentsRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new DepartmentNotFoundException("Department nor found!");
        } catch (DataIntegrityViolationException ex) {
            throw new DepartmentDeletionException("Department can't be deleted!");
        }
    }

    @Override
    public Departments addDepartments(Departments departments) {
        return departmentsRepository.save(departments);
    }

    @Override
    public Departments updateDepartments(Departments departments) {
        Optional<Departments> tmp = departmentsRepository.findById(departments.getId());
        if (tmp.isPresent()) {
            return departmentsRepository.save(departments);
        } else {
            throw new DepartmentNotFoundException("Department nor found!");
        }
    }
}
