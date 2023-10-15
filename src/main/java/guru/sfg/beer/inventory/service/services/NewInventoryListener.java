package guru.sfg.beer.inventory.service.services;

import com.mazasoft.msscbrewery.events.NewInventoryEvent;
import guru.sfg.beer.inventory.service.config.JmsConfig;
import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jt on 2019-07-21.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class NewInventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;

    @Transactional
    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent event) {

        log.debug("Got Inventory: " + event.toString());

        List<BeerInventory> beerInventories = beerInventoryRepository.findAllByBeerId(event.getBeerDto().getId());

        if (beerInventories==null|| beerInventories.isEmpty())
        {
            beerInventoryRepository.save(BeerInventory.builder()
                    .beerId(event.getBeerDto().getId())
                    .upc(event.getBeerDto().getUpc())
                    .quantityOnHand(event.getBeerDto().getQuantityOnHand())
                    .build());
            return;
        }
        for (BeerInventory beerInventory: beerInventories)
        {
            beerInventory.setQuantityOnHand(event.getBeerDto().getQuantityOnHand());
            beerInventoryRepository.save(beerInventory);
        }
    }

}
