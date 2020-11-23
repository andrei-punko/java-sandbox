package by.andd3dfx.capturesound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;

public class PlayThread implements Runnable {

    private final AudioInputStream audioInputStream;
    private final SourceDataLine sourceDataLine;
    private byte tempBuffer[] = new byte[10000];

    public PlayThread(AudioInputStream audioInputStream, SourceDataLine sourceDataLine) {
        this.audioInputStream = audioInputStream;
        this.sourceDataLine = sourceDataLine;

        Thread th = new Thread(this);
        th.start();
    }

    @Override
    public void run() {
        int count;
        try {
            while ((count = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
                if (count > 0) {
                    sourceDataLine.write(tempBuffer, 0, count);
                }
            }
            sourceDataLine.drain();
            sourceDataLine.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
