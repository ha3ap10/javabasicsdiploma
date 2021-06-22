package ru.netology.graphics;

import ru.netology.graphics.converter.*;
import ru.netology.graphics.image.TextColorSchema;
import ru.netology.graphics.image.TextGraphicsConverter;

import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new ConverterImgToText(); // Создайте тут объект вашего класса конвертера

//        GServer server = new GServer(converter); // Создаём объект сервера
//        converter.setMaxHeight(500);
//        converter.setMaxWidth(500);
//        server.start(); // Запускаем

        // Или то же, но с сохранением в файл:
        PrintWriter fileWriter = new PrintWriter(new File("converted-image.txt"));

        converter.setMaxWidth(200);
        converter.setMaxHeight(300);

//        TextColorSchema colorSchema = new SymbolColorSchema();
//        converter.setTextColorSchema(colorSchema);

        fileWriter.write(converter.convert("https://catherineasquithgallery.com/uploads/posts/2021-02/thumbs/1614334511_12-p-fon-raznotsvetnii-gradient-14.jpg"));
//        fileWriter.write(converter.convert("https://i.ibb.co/6DYM05G/edu0.jpg"));
        fileWriter.close();
    }
}
