package com.psagar3codes.ems.service.impl;

import com.psagar3codes.ems.dto.EmployeeDto;
import com.psagar3codes.ems.entity.Employee;
import com.psagar3codes.ems.mapper.EmployeeMapper;
import com.psagar3codes.ems.repository.EmployeeRepository;
import com.psagar3codes.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
       Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->
                        new ResolutionException("Employee does not exist with given id "+employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmpoyees() {
       List<Employee> employees = employeeRepository.findAll();

        return employees
                .stream()
                .map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }
}
