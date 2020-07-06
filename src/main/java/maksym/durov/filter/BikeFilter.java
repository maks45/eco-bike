package maksym.durov.filter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import maksym.durov.model.EBike;
import maksym.durov.model.FoldingBike;
import maksym.durov.model.SearchBikeDto;
import maksym.durov.model.Speedelec;
import maksym.durov.model.abstractbike.Bike;
import maksym.durov.model.abstractbike.ElectricBike;
import org.springframework.stereotype.Component;

@Component
public class BikeFilter {

    public List<Bike> filterBikes(List<Bike> bikes, SearchBikeDto searchBikeDto) {
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
