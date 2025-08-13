package ru.flytickets;

import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class AirportGMTService {


    private static final Map<String, ZoneId> AIRPORT_GMT = new HashMap<>(Map.of(
            "VVO", ZoneId.of("Asia/Vladivostok"),
            "TLV", ZoneId.of("Asia/Jerusalem")
    ));

    public static ZoneId getAirportGMT(String airport) {

        return AIRPORT_GMT.get(airport);
    }

}
