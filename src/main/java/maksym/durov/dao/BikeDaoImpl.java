package maksym.durov.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import maksym.durov.filter.BikeFilter;
import maksym.durov.mappers.EBikeMapper;
import maksym.durov.mappers.FoldingBikeMapper;
import maksym.durov.mappers.SpeedelecMapper;
import maksym.durov.model.EBike;
import maksym.durov.model.FoldingBike;
import maksym.durov.model.SearchBikeDto;
import maksym.durov.model.Speedelec;
import maksym.durov.model.abstractbike.Bike;
import org.springframework.stereotype.Repository;

@Repository
public class BikeDaoImpl implements BikeDao {
    private final EBikeMapper ebikeMapper;
    private final SpeedelecMapper speedelecMapper;
    private final FoldingBikeMapper foldingBikeMapper;
    private final BikeFilter bikeFilter;
    private String fileName;
    private boolean isNoSavedData;
    private List<Bike> bikes;

    public BikeDaoImpl(EBikeMapper ebikeMapper, SpeedelecMapper speedelecMapper,
                       FoldingBikeMapper foldingBikeMapper, BikeFilter bikeFilter) {
        this.ebikeMapper = ebikeMapper;
        this.speedelecMapper = speedelecMapper;
        this.foldingBikeMapper = foldingBikeMapper;
        this.bikeFilter = bikeFilter;
    }

    @Override
    public void saveAll() {
        String content = bikes.stream().map(this::getStringRepresentation)
                .collect(Collectors.joining("\n"));
        try {
            Files.write(Paths.get(fileName), content.getBytes());
        } catch (IOException e) {
            System.out.println("unable to write file");
        }
        isNoSavedData = false;
    }

    @Override
    public List<Bike> getAll(String fileName) {
        this.fileName = fileName;
        bikes = new ArrayList<>();
        try {
            bikes.addAll(Files.readAllLines(Paths.get(fileName))
                    .stream()
                    .map(s -> {
                        if (s.startsWith(EBike.PREFIX)) {
                            return ebikeMapper.getObject(s);
                        }
                        if (s.startsWith(FoldingBike.PREFIX)) {
                            return foldingBikeMapper.getObject(s);
                        }
                        if (s.startsWith(Speedelec.PREFIX)) {
                            return speedelecMapper.getObject(s);
                        }
                        throw new RuntimeException("not valid bike prefix in " + s);
                    })
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bikes;
    }

    @Override
    public void addBike(Bike bike) {
        bikes.add(bike);
        isNoSavedData = true;
    }

    @Override
    public boolean isNoSavedData() {
        return isNoSavedData;
    }

    @Override
    public List<Bike> getPage(int offset, int limit) {
        return new ArrayList<>(bikes.subList(Math.min(offset, bikes.size()),
                Math.min((offset + limit), bikes.size())));
    }

    @Override
    public List<Bike> findBike(SearchBikeDto searchBikeDto) {
        return bikeFilter.filterBikes(bikes, searchBikeDto);
    }

    private String getStringRepresentation(Bike bike) {
        if (Speedelec.PREFIX.equals(bike.getPrefix())) {
            return speedelecMapper.getStringInVcs((Speedelec) bike);
        }
        if (EBike.PREFIX.equals(bike.getPrefix())) {
            return ebikeMapper.getStringInVcs((EBike) bike);
        }
        if (FoldingBike.PREFIX.equals(bike.getPrefix())) {
            return foldingBikeMapper.getStringInVcs((FoldingBike) bike);
        }
        throw new RuntimeException("can't find mapper for class " + bike.getClass().getName());
    }


}
