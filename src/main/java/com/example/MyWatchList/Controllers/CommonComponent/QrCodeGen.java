package com.example.MyWatchList.Controllers.CommonComponent;

import io.nayuki.qrcodegen.QrCode;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.util.Objects;


public class QrCodeGen {

    public static Image genQrCode(String text) {
        QrCode qrCode = QrCode.encodeText(text, QrCode.Ecc.LOW);
        BufferedImage bufferedImage = toImage(qrCode, 3, 0);
        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

    private static BufferedImage toImage(QrCode qr, int scale, int border) {
        Objects.requireNonNull(qr);
        if (scale <= 0 || border < 0)
            throw new IllegalArgumentException("Value out of range");
        if (border > Integer.MAX_VALUE / 2 || qr.size + border * 2L > Integer.MAX_VALUE / scale)
            throw new IllegalArgumentException("Scale or border too large");

        BufferedImage result = new BufferedImage((qr.size + border * 2) * scale, (qr.size + border * 2) * scale, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < result.getHeight(); y++) {
            for (int x = 0; x < result.getWidth(); x++) {
                boolean color = qr.getModule(x / scale - border, y / scale - border);
                result.setRGB(x, y, color ? 0 : 16777215);
            }
        }
        return result;
    }
}
