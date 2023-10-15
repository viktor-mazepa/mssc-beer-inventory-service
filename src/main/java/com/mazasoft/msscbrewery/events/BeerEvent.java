package com.mazasoft.msscbrewery.events;

import guru.sfg.brewery.model.events.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by jt on 2019-07-21.
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {

    private static final long serialVersionUID = 3293973619705099908L;

    private BeerDto beerDto;
}
