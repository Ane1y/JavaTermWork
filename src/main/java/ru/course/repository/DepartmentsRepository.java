package ru.course.repository;

import org.springframework.stereotype.Repository;
import ru.course.entity.Departments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface DepartmentsRepository extends CrudRepository<Departments, Long> {
    @Query("select d from Departments d where d.name = :name")
    Departments findByName(@Param("name") String name);
}
