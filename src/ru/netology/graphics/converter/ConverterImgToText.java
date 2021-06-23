package ru.netology.graphics.converter;

import ru.netology.graphics.image.BadImageSizeException;
import ru.netology.graphics.image.TextColorSchema;
import ru.netology.graphics.image.TextGraphicsConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ConverterImgToText implements TextGraphicsConverter {

    private int maxWidth = 300;
    private int maxHeight = 300;
    private double maxRatio = 4;
    private float diffWidth;
    private float diffHeight;
    private int newWidth;
    private int newHeight;
    private TextColorSchema schema = new SymbolColorSchemaWin();

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        int imageWidth = img.getWidth();
        int imageHeight = img.getHeight();
        double imageRatio = (double) imageWidth / imageHeight;

        if (imageRatio > maxRatio) throw new BadImageSizeException(imageRatio, maxRatio);

        setNewWidthHeight(imageWidth, imageHeight);

        return outputImage(img);
    }

    public void setNewWidthHeight(int width, int height) {
        diffWidth = (float) width / maxWidth;
        diffHeight = (float) height / maxHeight;

        float diff = Math.max(diffWidth, diffHeight);
        newWidth = (int) (width / diff);
        newHeight = (int) (height / diff);
    }

    public String outputImage(BufferedImage img) {
        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);

        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D graphics = bwImg.createGraphics();

        graphics.drawImage(scaledImage, 0, 0, null);

        var bwRaster = bwImg.getRaster();

        StringBuilder sb = new StringBuilder();
        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);
                sb
                        .append(c)
                        .append(c);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}
