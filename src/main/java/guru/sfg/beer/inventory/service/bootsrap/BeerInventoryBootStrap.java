package guru.sfg.beer.inventory.service.bootsrap;

import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class BeerInventoryBootStrap implements CommandLineRunner {
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    public static final UUID BEER_1_UUID = UUID.fromString("69b796ed-d4b5-417b-b3ad-0703fdce44fe");
    public static final UUID BEER_2_UUID = UUID.fromString("da614660-3e7d-42fe-b3ce-98a321b3d3cc");
    public static final UUID BEER_3_UUID = UUID.fromString("e0bd9aa6-6659-442d-80ad-5460ec41369a");

    private final BeerInventoryRepository beerInventoryRepository;


    @Override
    public void run(String... args) throws Exception {
        if (beerInventoryRepository.count() == 0) {
            load();
        }
    }

    private void load() {
        beerInventoryRepository.save(BeerInventory
                .builder()
                .beerId(BEER_1_UUID)
                .upc(BEER_1_UPC)
                .quantityOnHand(50)
                .build());

        beerInventoryRepository.save(BeerInventory
                .builder()
                .beerId(BEER_2_UUID)
                .upc(BEER_2_UPC)
                .quantityOnHand(50)
                .build());
        beerInventoryRepository.save(BeerInventory
                .builder()
                .beerId(BEER_3_UUID)
                .upc(BEER_3_UPC)
                .quantityOnHand(50)
                .build());
    }
}
