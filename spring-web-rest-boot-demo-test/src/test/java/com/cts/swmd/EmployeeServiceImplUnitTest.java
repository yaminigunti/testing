package com.cts.swmd;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.swmd.dao.EmployeeRepository;
import com.cts.swmd.model.Department;
import com.cts.swmd.model.Employee;
import com.cts.swmd.service.EmployeeService;
import com.cts.swmd.service.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplUnitTest {
	
	@TestConfiguration
	static class EmployeeServiceImplTextConfiguration {
		
		@Bean
		public EmployeeService employeeservice() {
			return new EmployeeServiceImpl();
			
		}
	}

		@Autowired
		private EmployeeService employeeService;
		
		@MockBean
		private EmployeeRepository employeeRepository;
		
		@Before
		public void setUp() {
			Employee emp=new Employee("yamini","gunti",45000,
					LocalDate.now(),Department.DEVELOPMENT,
					"9876543210","ads@gmail.com");
			
			Mockito.when(employeeRepository.findByMobileNumber(emp.getMobileNumber()))
			.thenReturn(emp);
		}
		
		@Test
		public void whenValidMobileNumber_thenEmployeeShouldBeFound() {
			String mno = "9876543210";
			Employee found=employeeService.findByMobileNumber(mno);
			assertThat(found.getMobileNumber()).isEqualTo(mno);
		}
		
		@Test
		public void whenValidMobileNumber_thenEmployeeShouldNotBeFound() {
			String mno = "1234567890";
			Employee found=employeeService.findByMobileNumber(mno);
			assertThat(found).isNull();
		}
}
