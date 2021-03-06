package ru.job4j.ood.lsp.food.store.impl;

import ru.job4j.ood.lsp.food.model.Food;
import ru.job4j.ood.lsp.food.store.Store;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

    private final List<Food> trashStorage = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        var result = false;
        if (checkQualityPercent(food)) {
            trashStorage.add(food);
            result = true;
        }
        return result;
    }

    @Override
    public boolean checkQualityPercent(Food food) {
        return getExpiredDatePercent(food) < 0;
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(trashStorage);
    }

    @Override
    public void printFoodsInStorage() {
        trashStorage.forEach(f -> System.out.println("Food in trash: " + f));
    }

    @Override
    public void clear() {
        trashStorage.clear();
    }
}
