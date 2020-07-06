package maksym.durov.dao;

import java.util.List;
import maksym.durov.model.SearchBikeDto;
import maksym.durov.model.abstractbike.Bike;

public interface BikeDao {
    void saveAll();

    List<Bike> getAll(String fileName);

    void addBike(Bike bike);

    boolean isNoSavedData();

    List<Bike> getPage(int offset, int limit);

    List<Bike> findBike(SearchBikeDto searchBikeDto);
}
