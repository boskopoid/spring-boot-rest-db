package com.boskopoid.simpleservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping(value="hello")
public class Application {

    @Autowired
    private BookRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @ResponseBody
    @PutMapping("/try")
    public Country tryPut(@RequestBody Country body )
    {
        System.out.println("I am in PUT Jason: City" + body.getCity());
        System.out.println("I am in PUT Jason: Country" + body.getCountry());
        Country ctr = new Country();
        ctr.setCity("Country " + body.getCountry());
        ctr.setCountry("City: " + body.getCity());
        return ctr;
    }


    @ResponseBody
    @PutMapping("/{planet}")
    public Country updatePut(@PathVariable("planet") String planet, @RequestParam Map<String, String> body )
    {
        System.out.println("I am in PUT " + planet + ": " + body.toString());
        Country ctr = new Country();
        ctr.setCity("Tokyo");
        ctr.setCountry("Japan");
        return ctr;
    }

    @ResponseBody
    @PostMapping("/{planet}")
//    @RequestMapping(path="/{planet}", method = RequestMethod.POST, consumes = { "multipart/form-data" })
//    public Country updatePost(@PathVariable("planet") String planet, @RequestBody Country body)
    public String updatePost(@PathVariable("planet") String planet, @RequestParam Map<String, String> body )
    {
        System.out.println("I am in POST " + planet + ": " + body.toString());
//        return body;
        return "POST Testng only " + body.toString();
    }

//    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    @PostMapping(value= "/login")
    public ResponseEntity<Object> login(@RequestParam String username, @RequestParam String password)
    {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand("employee.getId()")
                .toUri();

        System.out.println("UserName: " + username);
        System.out.println("Password: " + password);
        System.out.println(location.toString());

        if ("space".equalsIgnoreCase(username )) {
            throw new UserNotfoundException();
        }
//        return ResponseEntity.created(location).build();
        return ResponseEntity.ok().body("The quick brown fox jumps over the lazy dog");
    }

    @RequestMapping(value = "/save", method = { RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String putAndPostFoos() {
        System.out.println("I am in saved...");
        return "Advanced - PUT and POOST within single method";
    }

    @RequestMapping(value="/{firstName}/{lastName}",method = RequestMethod.GET)
    public String hello( @PathVariable("firstName") String firstName,
                         @PathVariable("lastName") String lastName) {

        repository.save(new Book("Java"));
        repository.save(new Book("Node"));
        repository.save(new Book("Python"));
        repository.save(new Book(firstName + ":" + lastName));

        System.out.println("\nfindAll()");
        repository.findAll().forEach(x -> System.out.println(x));

        System.out.println("\nfindById(1L)");
//        repository.findById(1l).ifPresent(x -> System.out.println(x));

        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nfindByName('Node')");
        repository.findByName("Node").forEach(x -> System.out.println(x));


        return buildJson(firstName, lastName);
//        return String.format("{\"message\":\"Hello %s %s\"}", firstName, lastName);
    }

    protected String buildJson(String firstName, String lastName) {
        List<String> elements = new ArrayList<>();
        elements.add("Element1");
        elements.add("Element2");
        elements.add("message");
        elements.add("teamcity");
        elements.add(firstName);
        elements.add(lastName);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(elements);

            System.out.println("json = " + json);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
//            fail();
        }
        return "nothing";
    }

}
