package com.zemoso.crud.dao;

import com.zemoso.crud.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
     private EntityManager entityManager;
     @Autowired
     public EmployeeDAOHibernateImpl(EntityManager entityManager){
         this.entityManager=entityManager;
     }
    @Override

    public List<Employee> getEmployees() {
        Session session=entityManager.unwrap(Session.class);
        Query<Employee> query=session.createQuery("from Employee",Employee.class);
        List<Employee> employees=query.getResultList();
        return employees;
    }
    @Override
    public Employee getById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Employee theEmployee =
                currentSession.get(Employee.class, theId);
        return theEmployee;
    }
    @Override
    public void save(Employee theEmployee) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theEmployee);
    }
    @Override
    public void deleteById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery =
                currentSession.createQuery(
                        "delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId", theId);
        theQuery.executeUpdate();
    }

}
