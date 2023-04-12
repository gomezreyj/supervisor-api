package lightfeather.test.supervisorsapi.controller;

import lightfeather.test.supervisorsapi.model.Person;
import lightfeather.test.supervisorsapi.service.SupervisorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class SupervisorController
{

   @Autowired
   SupervisorService service;

@GetMapping("/api/supervisors")
List<String> getSupervisors()
{
  return  service.getAndSortSupervisors();
}

@PostMapping("/api/submit")
String postSupervisor(@RequestBody Person person)
{
  return service.postSupervisor(person);

}

}
