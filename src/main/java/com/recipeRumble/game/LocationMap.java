package com.recipeRumble.game;

import java.util.HashMap;
import java.util.Map;

public class LocationMap {
    private Map<String, Location> locations; // Map to store and manage locations

    // Constructor to initialize the location map
    public LocationMap() {
        this.locations = new HashMap<>();
        initializeLocations();
    }

    // Method to initialize all possible locations
    private void initializeLocations() {
        locations.put("kitchen", new Location("Kitchen", "A place where delicious meals are made."));
        locations.put("forest", new Location("Forest", "A dense forest with towering trees."));
        locations.put("mountain", new Location("Mountain", "A tall mountain with a breathtaking view."));
        // Add more locations as needed
    }

    // Method to get a location by name
    public Location getLocation(String name) {
        return locations.get(name.toLowerCase());
    }
}
