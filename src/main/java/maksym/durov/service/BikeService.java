package maksym.durov.service;

import java.util.List;
import maksym.durov.model.SearchBikeDto;
import maksym.durov.model.abstractbike.Bike;

public interface BikeService {

    void addBike(Bike bike);

    List<Bike> getAllBikes(String filename);

    void saveAllBikes();

    List<Bike> getPage(int page);

    List<Bike> findBike(SearchBikeDto searchBikeDto);

    boolean isNotSavedData();
}
