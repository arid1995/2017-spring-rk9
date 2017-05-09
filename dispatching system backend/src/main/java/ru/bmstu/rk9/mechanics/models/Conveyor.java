package ru.bmstu.rk9.mechanics.models;

import java.util.HashMap;

/**
 * Created by farid on 5/2/17.
 */
public class Conveyor extends Device {
    public static final Integer MACHINE_1_KEY = 1;
    public static final Integer MACHINE_2_KEY = 2;
    public static final Integer STOCK_ENTRY_KEY = 3;
    public static final Integer STOCK_EXIT_KEY = 4;
    private HashMap<Integer, Key> keys = new HashMap<>();

    public Conveyor() {
        keys.put(MACHINE_1_KEY, new Key());
        keys.put(MACHINE_2_KEY, new Key());
        keys.put(STOCK_ENTRY_KEY, new Key());
        keys.put(STOCK_EXIT_KEY, new Key());
    }

    public void openKey(Integer key) {
        keys.get(key).state = 1;
    }

    public void closeKey(Integer key) {
        keys.get(key).state = 0;
    }

    private class Key {
        public byte state; //0 - closed, 1 - opened
    }
}
