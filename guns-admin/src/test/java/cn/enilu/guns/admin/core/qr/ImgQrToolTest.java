package cn.enilu.guns.admin.core.qr;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import static org.junit.Assert.assertTrue;

public class ImgQrToolTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void createSimpleQrWritesImageFile() throws Exception {
        File output = new File(temporaryFolder.getRoot(), "simple.jpg");

        ImgQrTool.createSimpleQr("hello", 120, 120, output.getAbsolutePath());

        assertTrue(output.isFile());
        assertTrue(output.length() > 0);
    }

    @Test
    public void encodeWithIconWritesToFileAndStream() throws Exception {
        File icon = createIcon("icon.png", 120, 90);
        File output = new File(temporaryFolder.getRoot(), "with-icon.jpg");

        ImgQrTool.encode("https://example.test", 180, 180, icon.getAbsolutePath(), output.getAbsolutePath());

        assertTrue(output.isFile());
        assertTrue(output.length() > 0);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImgQrTool.encode("https://example.test", 180, 180, icon.getAbsolutePath(), stream);
        assertTrue(stream.size() > 0);
    }

    @Test
    public void createQrWithFontsAboveWritesCombinedImage() throws Exception {
        File icon = createIcon("font-icon.png", 90, 120);
        File output = new File(temporaryFolder.getRoot(), "with-fonts.jpg");
        QrImage qrImage = new QrImage.Builder()
                .setQrContent("content")
                .setQrWidth(180)
                .setQrHeight(180)
                .setQrIconFilePath(icon.getAbsolutePath())
                .setWordContent("a long title that should be split")
                .setWordSize(12)
                .setFileOutputPath(output.getAbsolutePath())
                .build();

        ImgQrTool.createQrWithFontsAbove(qrImage);

        assertTrue(output.isFile());
        assertTrue(output.length() > 0);
    }

    private File createIcon(String name, int width, int height) throws Exception {
        File icon = temporaryFolder.newFile(name);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.BLUE);
        graphics.fillRect(10, 10, width - 20, height - 20);
        graphics.dispose();
        ImageIO.write(image, "png", icon);
        return icon;
    }
}
