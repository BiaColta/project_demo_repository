package payroll;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    private final EmployeeResourceAssembler assembler;

    EmployeeController(EmployeeRepository repository, EmployeeResourceAssembler assembler ){
        this.repository=repository;
        this.assembler=assembler;
    }

/*
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee){
        return repository.save(newEmployee);
    }
 */
    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) throws URISyntaxException {

        Resource<Employee> resource = assembler.toResource(repository.save(newEmployee));

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }
/*
    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id){

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }
 */

    @PutMapping("/emmployees/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) throws URISyntaxException{

        Employee updatedEmployee = repository.findById(id)
        .map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return repository.save(employee);
        })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                return repository.save(newEmployee);
                });

        Resource<Employee> resource = assembler.toResource(updatedEmployee);

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @DeleteMapping("/employee/{id}")
    void deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
    }

    /*
    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id){

        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    */
    /*
    @GetMapping( "/employees/{id}")
    Resource<Employee> myone(@PathVariable Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return new Resource<>(employee,
                linkTo(methodOn(EmployeeController.class).myone(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }

     */

    @GetMapping("/employees/{id}")
    Resource<Employee> myone(@PathVariable Long id){

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return assembler.toResource(employee);
    }


    /*
    @GetMapping("/employees")
    List<Employee> all(){
        return repository.findAll();
    }
    */

    @GetMapping("/employees")
    Resources<Resource<Employee>> all(){

        List<Resource<Employee>> employees = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }
}
