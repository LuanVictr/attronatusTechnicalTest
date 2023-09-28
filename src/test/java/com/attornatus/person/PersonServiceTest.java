package com.attornatus.person;

import com.attornatus.person.model.entities.Address;
import com.attornatus.person.model.entities.Person;
import com.attornatus.person.model.repositories.PersonRepository;
import com.attornatus.person.services.PersonService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PersonServiceTest {

  @Autowired
  private PersonService personService;

  @MockBean
  PersonRepository personRepository;

  @Test
  public void createPersonTest() {
    Person personMock = new Person(1L, "Luan Victor", "21/03/1990",
        new Address("Rua das ruas", "88938-231", 40, "Camocim") );

    when(personRepository.save(personMock)).thenReturn(personMock);

    Person createdPerson = this.personService.createPerson(personMock);

    verify(personRepository, times(1)).save(personMock);

    assertEquals(personMock.getName(), createdPerson.getName());
    assertEquals(personMock.getId(), createdPerson.getId());
    assertEquals(personMock.getBirthDate(), createdPerson.getBirthDate());
    assertEquals(personMock.getAddress(), createdPerson.getAddress());
  }

}