package com.recipeRumble.game.foods.techniques;

import java.util.Objects;

public class CookingTechnique {
    private final String name;
    private final String adjective;

    public CookingTechnique(String name, String adjective) {
        this.name = name;
        this.adjective = adjective;
    }

    public String getName() {
        return name;
    }

    public String getAdjective() {
        return adjective;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CookingTechnique that = (CookingTechnique) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(adjective, that.adjective);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, adjective);
    }
}
