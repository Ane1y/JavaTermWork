package ru.course.repository;

import org.springframework.stereotype.Repository;
import ru.course.entity.DepartmentsEmployees;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

@Repository
public interface DepartmentsEmployeesRepository extends CrudRepository<DepartmentsEmployees, Long> {
    @Query("select de from DepartmentsEmployees de where de.departments.name = :name")
    Collection<DepartmentsEmployees> findByDepartmentName(@Param("name") String name);

    @Query("select de from DepartmentsEmployees de where de.departments.id = :id")
    Collection<DepartmentsEmployees> findByDepartmentId(@Param("id") Long id);

    @Query("select de from DepartmentsEmployees de where de.employees.id = :id")
    Collection<DepartmentsEmployees> findByEmployeeId(@Param("id") Long id);

    @Query("select de from DepartmentsEmployees de inner join Employees e on " +
            "e.id = de.employees.id where e.firstName = :first and e.lastName = :last and " +
            "e.patherName = :pather")
    Collection<DepartmentsEmployees> findByEmployeeName(@Param("first") String first, @Param("last") String last, @Param("pather") String pather);
}
