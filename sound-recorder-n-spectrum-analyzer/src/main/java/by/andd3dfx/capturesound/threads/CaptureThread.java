package by.andd3dfx.capturesound.threads;

import javax.sound.sampled.TargetDataLine;
import java.io.ByteArrayOutputStream;

public class CaptureThread implements Runnable {

    private final ByteArrayOutputStream byteArrayOutputStream;
    private final TargetDataLine targetDataLine;
    private byte tempBuffer[] = new byte[10000];
    private boolean stopCapture = false;

    public CaptureThread(TargetDataLine targetDataLine) {
        this.byteArrayOutputStream = new ByteArrayOutputStream();
        this.targetDataLine = targetDataLine;

        Thread th = new Thread(this);
        th.start();
    }

    @Override
    public void run() {
        try {
            while (!stopCapture) {
                int count = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
                if (count > 0) {
                    byteArrayOutputStream.write(tempBuffer, 0, count);
                }
            }

            byteArrayOutputStream.close();
            targetDataLine.close();         // Was not present in source code
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void setStopCapture(boolean stopCapture) {
        this.stopCapture = stopCapture;
    }

    public ByteArrayOutputStream getByteArrayOutputStream() {
        return byteArrayOutputStream;
    }
}
