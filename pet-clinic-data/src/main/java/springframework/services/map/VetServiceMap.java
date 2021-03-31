package springframework.services.map;

import org.springframework.stereotype.Service;
import springframework.model.Speciality;
import springframework.model.Vet;
import springframework.services.SpecialtyService;
import springframework.services.VetService;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet t) {
        super.delete(t);
    }

    @Override
    public Vet save(Vet vet) {
        if(vet != null && vet.getSpecialities().size() > 0 ){
            vet.getSpecialities().forEach(s->{
                if(s.getId() == null){
                    Speciality savedSpecialty = specialtyService.save(s);
                    savedSpecialty.setId(savedSpecialty.getId());
                }
            });
        }

        return super.save(vet);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
