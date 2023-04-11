package service.impl;

import model.Facility;
import repository.IFacilityRepository;
import repository.impl.FacilityRepository;
import service.IFacilityService;

import java.util.List;

public class FacilityService implements IFacilityService {
    private IFacilityRepository facilityRepository = new FacilityRepository();
    @Override
    public List<Facility> findAll() {
        return facilityRepository.findAll();
    }
    @Override
    public boolean add(Facility facility) {
        return facilityRepository.add(facility);
    }

    @Override
    public boolean delete(int id) {
        return facilityRepository.delete(id);
    }

    @Override
    public boolean edit(int id, Facility facility) {
        return false;
    }

    @Override
    public List<Facility> search(String name) {
        return facilityRepository.search(name);
    }
}
