package ru.netology.graphics.converter;

import ru.netology.graphics.image.TextColorSchema;

import java.util.NavigableMap;
import java.util.TreeMap;

public class ColorSchemaTreeMapWin implements TextColorSchema {

    private NavigableMap<Integer, Character> colorSchema = new TreeMap<>();

    public ColorSchemaTreeMapWin() {

        colorSchema.put(31, '#');
        colorSchema.put(63, '$');
        colorSchema.put(95, '@');
        colorSchema.put(127, '%');
        colorSchema.put(159, '*');
        colorSchema.put(191, '+');
        colorSchema.put(223, '-');
        colorSchema.put(255, '\'');

    }

    @Override
    public char convert(int color) {
        return colorSchema.ceilingEntry(color).getValue();
    }
}
