package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Student;
import com.example.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;

	@GetMapping
	public List<Student> getAllStudent() {
		List<Student> list = studentRepository.findAll();
		return list;
	}

	@PostMapping
	public Student addStudent(@RequestBody Student student) {
		return studentRepository.save(student);

	}

	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
		Student existingStudent = studentRepository.findById(id).get();
		existingStudent.setName(student.getName());
		existingStudent.setMarks(student.getMarks());
		studentRepository.save(existingStudent);
		return existingStudent;

	}

	@DeleteMapping("/{id}")
	public String deleteStudentString(@PathVariable("id") int id) {
		Student findStudent = studentRepository.findById(id).get();
		 studentRepository.delete(findStudent);
		 return "Deleted Student whose ID is "+id;
	}

}
