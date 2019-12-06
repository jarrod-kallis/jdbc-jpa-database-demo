package com.in28minutes.database.databasedemo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.in28minutes.database.databasedemo.entity.Person;

@Repository
@Transactional
public class PersonJpaRepository {
	public static final String QUERY_FIND_ALL = "find_all_people";

	@PersistenceContext
	private EntityManager entityManager;

	public List<Person> findAll() {
		TypedQuery<Person> namedQuery = entityManager.createNamedQuery(QUERY_FIND_ALL, Person.class);
		return namedQuery.getResultList();
	}

	public Person findById(int id) {
		return entityManager.find(Person.class, id);
	}

	public Person upsert(Person person) {
		// If the instance of person has an id that exists in the DB then it will update
		// it, otherwise it will insert it
		return entityManager.merge(person);
	}

	public void deleteById(int id) {
		Person person = findById(id);
		entityManager.remove(person);
	}

	public void deleteByMultipleIds(List<Integer> ids) {
		entityManager.createQuery("delete from Person p where p.id in (:ids)").setParameter("ids", ids).executeUpdate();
	}
}
