package org.acme.Service;

import org.acme.Dao.ShiftRepository;
import org.acme.Entity.Shift;
import org.acme.Entity.ShiftCreateDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.acme.Entity.ShiftDTO;
import org.eclipse.microprofile.jwt.JsonWebToken;
@ApplicationScoped
public class ShiftService {

    @Inject
    ShiftRepository shiftRepository;

    @Inject
    JsonWebToken jwt;
    public List<ShiftDTO> listAll() {
        String userId = jwt.getSubject(); // Get user ID

        List<ShiftDTO> res = new ArrayList<>();
        List<Shift> temp = shiftRepository.listAll();

        for (Shift shift : temp) {
            if (Objects.equals(shift.getCreatedBy(), userId)) {
                res.add(new ShiftDTO(shift));
            }
        }
        return res;
    }

    public ShiftDTO findById(UUID id) {
        String userId = jwt.getSubject(); // Get user ID
        Shift shift = shiftRepository.findById(id);
        if(Objects.equals(shift.getCreatedBy(), userId)){
            return new ShiftDTO(shift);
        }else{
            return null;
        }
    }

    @Transactional
    public Shift createShift(ShiftCreateDTO shiftCreateDTO) {
        Shift shift = new Shift();

        shift.onCreate();
        shift.onUpdate();
        shift.setHierarchy(shiftCreateDTO.getHierarchy());
        shift.setLocation(shiftCreateDTO.getLocation());
        shift.setTimeZone(shiftCreateDTO.getTimeZone());

        String userId = jwt.getSubject();
        shift.setCreatedBy(userId); // set user id from jwt

        shiftRepository.create(shift);
        return shift;
    }

    @Transactional
    public ShiftDTO updateShift(UUID id, ShiftDTO shiftDTO) {
        Shift shift = shiftRepository.findById(id);
        String userId = jwt.getSubject();

        if(Objects.equals(userId, shift.getCreatedBy())){
            shift.onUpdate();
            shift.setHierarchy(shiftDTO.getHierarchy());
            shift.setLocation(shiftDTO.getLocation());
            shift.setTimeZone(shiftDTO.getTimeZone());
        }
        shiftRepository.update(shift);

        return new ShiftDTO(shift);
    }

    @Transactional
    public void deleteShift(UUID id) {
        Shift shift = shiftRepository.findById(id);
        shiftRepository.delete(shift);
    }

}
