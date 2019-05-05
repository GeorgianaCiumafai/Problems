
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsvService implements IService<CsvShop, String>  {

    @Autowired
    private CsvRepository csvRepository;

    @Override
    public boolean add(CsvShop element) {
        try {
            csvRepository.save(element);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    @Override
    public List<CsvShop> getAll() {
        try {
            return csvRepository.findAll();
        }
        catch (Exception ex){
            return null;
        }
    }

    @Override
    public boolean getById(String productName) {
        return csvRepository.existsById(productName);
    }

}
