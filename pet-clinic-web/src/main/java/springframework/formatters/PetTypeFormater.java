package springframework.formatters;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import springframework.model.PetType;
import springframework.services.PetTypeService;

import java.text.ParseException;
import java.util.Locale;

@Component
public class PetTypeFormater implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormater(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String s, Locale locale) throws ParseException {
       return petTypeService.findAll().stream().filter(petType -> petType.getName().equalsIgnoreCase(s))
                .findAny().orElseThrow(()->new ParseException("type not Found for "+s,0));
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
