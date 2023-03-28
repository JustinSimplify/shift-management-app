package org.acme.Dao;

import org.acme.Entity.Shift;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ShiftRepository {

    @Inject
    EntityManager em;

    public List<Shift> listAll() {
        return em.createQuery("SELECT s FROM Shift s", Shift.class).getResultList();
    }

    public Shift findById(UUID id) {
        return em.find(Shift.class, id);
    }

    @Transactional
    public void create(Shift shift) {
        em.persist(shift);
    }

    @Transactional
    public void update(Shift shift) {
        em.merge(shift);
    }

    @Transactional
    public void delete(Shift shift) {
        em.remove(shift);
    }

}
