package ru.netology.graphics.converter;

import ru.netology.graphics.image.TextColorSchema;

public class SymbolColorSchema implements TextColorSchema {

    private char[] colorSchema = {'▇', '●', '◉', '◍', '◎', '○', '☉', '◌', '-'};

    @Override
    public char convert(int color) {
        return colorSchema[color / 28];
    }
}
