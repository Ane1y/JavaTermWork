package ru.course.service;

import ru.course.entity.Projects;

import java.util.List;

public interface ProjectsService {
    List<Projects> projectsList();
    Projects findProjectsById(Long id);
    Projects findProjectsByName(String name);
    List<Projects> findByDepartmentId(Long id);
    List<Projects> findByDepartmentName(String name);
    void deleteProjects(Long id);
    Projects addProjects(Projects projects);
    Projects updateProjects(Projects projects);
}
