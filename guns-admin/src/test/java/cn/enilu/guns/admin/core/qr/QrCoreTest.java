package cn.enilu.guns.admin.core.qr;

import com.google.zxing.common.BitMatrix;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QrCoreTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void matrixConfigExposesDefaultAndCustomColors() {
        MatrixToImageConfig defaultConfig = new MatrixToImageConfig();
        MatrixToImageConfig customConfig = new MatrixToImageConfig(0xFF112233, 0xFF445566);

        assertEquals(MatrixToImageConfig.BLACK, defaultConfig.getPixelOnColor());
        assertEquals(MatrixToImageConfig.WHITE, defaultConfig.getPixelOffColor());
        assertEquals(BufferedImage.TYPE_BYTE_BINARY, defaultConfig.getBufferedImageColorModel());
        assertEquals(0xFF112233, customConfig.getPixelOnColor());
        assertEquals(0xFF445566, customConfig.getPixelOffColor());
        assertEquals(BufferedImage.TYPE_INT_RGB, customConfig.getBufferedImageColorModel());
    }

    @Test
    public void matrixWriterRendersMatrixAndWritesImage() throws Exception {
        BitMatrix matrix = new BitMatrix(2, 2);
        matrix.set(0, 0);
        matrix.set(1, 1);
        MatrixToImageConfig config = new MatrixToImageConfig(0xFF0000AA, 0xFFFFFFFF);

        BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix, config);

        assertEquals(2, image.getWidth());
        assertEquals(2, image.getHeight());
        assertEquals(0xFF0000AA, image.getRGB(0, 0));
        assertEquals(0xFFFFFFFF, image.getRGB(1, 0));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "png", outputStream, config);
        assertTrue(outputStream.size() > 0);

        File outputFile = temporaryFolder.newFile("qr.png");
        MatrixToImageWriter.writeToFile(matrix, "png", outputFile, config);
        assertTrue(outputFile.length() > 0);
    }

    @Test
    public void qrImageBuilderCopiesAllFields() {
        QrImage qrImage = new QrImage.Builder()
                .setQrContent("content")
                .setQrWidth(120)
                .setQrHeight(130)
                .setQrIconFilePath("/tmp/icon.png")
                .setQrIconWidth(20)
                .setTopWrodHeight(30)
                .setWordSize(12)
                .setWordContent("title")
                .setFileOutputPath("/tmp/qr.png")
                .build();

        assertEquals("content", qrImage.getQrContent());
        assertEquals(120, qrImage.getQrWidth());
        assertEquals(130, qrImage.getQrHeight());
        assertEquals("/tmp/icon.png", qrImage.getQrIconFilePath());
        assertEquals(20, qrImage.getQrIconWidth());
        assertEquals(30, qrImage.getTopWrodHeight());
        assertEquals(12, qrImage.getWordSize());
        assertEquals("title", qrImage.getWordContent());
        assertEquals("/tmp/qr.png", qrImage.getFileOutputPath());
    }
}
