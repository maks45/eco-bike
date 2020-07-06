package maksym.durov.service;

import java.util.List;
import maksym.durov.dao.BikeDao;
import maksym.durov.model.SearchBikeDto;
import maksym.durov.model.abstractbike.Bike;
import org.springframework.stereotype.Service;

@Service
public class BikeServiceImpl implements BikeService {
    private static final int BIKES_PER_PAGE = 10;
    private final BikeDao bikeDao;

    public BikeServiceImpl(BikeDao bikeDao) {
        this.bikeDao = bikeDao;
    }

    @Override
    public void addBike(Bike bike) {
        bikeDao.addBike(bike);
    }

    @Override
    public List<Bike> getAllBikes(String filename) {
        return bikeDao.getAll(filename);
    }

    @Override
    public void saveAllBikes() {
        bikeDao.saveAll();
    }

    @Override
    public List<Bike> getPage(int page) {
        int offset = (page - 1) * BIKES_PER_PAGE;
        return bikeDao.getPage(offset, BIKES_PER_PAGE);
    }

    @Override
    public List<Bike> findBike(SearchBikeDto searchBikeDto) {
        return bikeDao.findBike(searchBikeDto);
    }

    @Override
    public boolean isNotSavedData() {
        return bikeDao.isNoSavedData();
    }
}
