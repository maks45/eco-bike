package maksym.durov.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import maksym.durov.mappers.EBikeMapper;
import maksym.durov.mappers.FoldingBikeMapper;
import maksym.durov.mappers.SpeedelecMapper;
import maksym.durov.model.EBike;
import maksym.durov.model.FoldingBike;
import maksym.durov.model.SearchBikeDto;
import maksym.durov.model.Speedelec;
import maksym.durov.model.abstractbike.Bike;
import maksym.durov.model.abstractbike.ElectricBike;
import org.springframework.stereotype.Repository;

@Repository
public class BikeDaoImpl implements BikeDao {
    private final EBikeMapper ebikeMapper;
    private final SpeedelecMapper speedelecMapper;
    private final FoldingBikeMapper foldingBikeMapper;
    private String fileName;
    private boolean isNoSavedData;
    private List<Bike> bikes;

    public BikeDaoImpl(EBikeMapper ebikeMapper, SpeedelecMapper speedelecMapper,
                       FoldingBikeMapper foldingBikeMapper) {
        this.ebikeMapper = ebikeMapper;
        this.speedelecMapper = speedelecMapper;
        this.foldingBikeMapper = foldingBikeMapper;
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
        Stream<Bike> bikeStream = filterGenericBike(bikes.stream(), searchBikeDto);
        if (searchBikeDto.getPrefix().equals(Speedelec.PREFIX)) {
            return filterElectricBike(bikeStream, searchBikeDto).collect(Collectors.toList());
        }
        if (searchBikeDto.getPrefix().equals(EBike.PREFIX)) {
            return filterElectricBike(bikeStream, searchBikeDto).collect(Collectors.toList());
        }
        if (searchBikeDto.getPrefix().equals(FoldingBike.PREFIX)) {
            return filterFoldingBike(bikeStream, searchBikeDto).collect(Collectors.toList());
        }
        throw new RuntimeException(String.format("No filter for bike type %s found",
                searchBikeDto.getPrefix()));
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

    private Stream<Bike> filterElectricBike(Stream<Bike> bikeStream, SearchBikeDto searchBikeDto) {
        return bikeStream.map(bike -> (ElectricBike) bike)
                .filter(bike -> searchBikeDto.getMaxSpeed() == null
                        || searchBikeDto.getMaxSpeed().equals(bike.getMaxSpeed()))
                .filter(bike -> searchBikeDto.getBatteryCapacity() == null
                        || searchBikeDto.getBatteryCapacity().equals(bike.getBatteryCapacity()))
                .map(bike -> bike);
    }

    private Stream<Bike> filterFoldingBike(Stream<Bike> bikeStream,
                                           SearchBikeDto searchBikeDto) {
        return bikeStream.map(bike -> (FoldingBike) bike)
                .filter(bike -> searchBikeDto.getGearsNumber() == null
                        || searchBikeDto.getGearsNumber().equals(bike.getGearsNumber()))
                .filter(bike -> searchBikeDto.getWheelsSize() == null
                        || searchBikeDto.getWheelsSize().equals(bike.getWheelsSize()))
                .map(bike -> bike);
    }

    private Stream<Bike> filterGenericBike(Stream<Bike> bikeStream,
                                           SearchBikeDto searchBikeDto) {
        return bikeStream
                .filter(bike -> searchBikeDto.getPrefix().equals(bike.getPrefix()))
                .filter(bike -> searchBikeDto.getBrand() == null
                        || searchBikeDto.getBrand().equals(bike.getBrand()))
                .filter(bike -> searchBikeDto.getWeight() == null
                        || searchBikeDto.getWeight().equals(bike.getWeight()))
                .filter(bike -> searchBikeDto.getBackAndFrontLights() == null
                        || searchBikeDto.getBackAndFrontLights()
                        .equals(bike.isBackAndFrontLights()))
                .filter(bike -> searchBikeDto.getColor() == null
                        || searchBikeDto.getColor().equals(bike.getColor()))
                .filter(bike -> searchBikeDto.getPrice() == null
                        || searchBikeDto.getPrice().equals(bike.getPrice()));
    }
}
