package by.andd3dfx.capturesound;

import by.andd3dfx.capturesound.fft.FrequencyScanner;
import by.andd3dfx.capturesound.threads.CaptureThread;
import by.andd3dfx.capturesound.threads.PlayThread;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * <pre>
 * Microphone sound recorder/player
 *
 * Press [Start] button to start recording
 * Press [Stop] button to stop recording
 * Press [Playback] button to start play of recorded audio
 *
 * Main frequency printed into console after that
 * </pre>
 */
public class AudioCaptureApp extends JFrame {

    public static void main(String[] args) {
        AudioCaptureApp app = new AudioCaptureApp();
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public AudioCaptureApp() {
        FrequencyScanner frequencyScanner = new FrequencyScanner();

        setSize(400, 80);
        setLocation(100, 100);
        this.setLayout(new GridLayout(1, 3, 20, 20));
        this.setResizable(false);

        final JButton startButton = new JButton("Start");
        final JButton stopButton = new JButton("Stop");
        final JButton playButton = new JButton("Playback");

        add(startButton);
        add(stopButton);
        add(playButton);

        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        playButton.setEnabled(false);

        final CaptureThread[] captureThread = {null};
        startButton.addActionListener(e -> {
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            playButton.setEnabled(false);

            AudioFormat audioFormat = buildAudioFormat();
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            try {
                TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
                targetDataLine.open(audioFormat);
                targetDataLine.start();

                captureThread[0] = new CaptureThread(targetDataLine);

            } catch (LineUnavailableException lue) {
                lue.printStackTrace();
            }
        });

        stopButton.addActionListener(e -> {
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            playButton.setEnabled(true);

            captureThread[0].setStopCapture(true);

        });

        playButton.addActionListener(e -> {
            byte audioData[] = captureThread[0].getByteArrayOutputStream().toByteArray();
            InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
            AudioFormat audioFormat = buildAudioFormat();


            double frequency = frequencyScanner.detectFrequency(audioData, (int) audioFormat.getSampleRate())
                    .maxFrequency();
            System.out.println("Freq=" + frequency);

            AudioInputStream audioInputStream = new AudioInputStream(
                    byteArrayInputStream, audioFormat, audioData.length / audioFormat.getFrameSize());

            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
            try (var sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo)) {
                sourceDataLine.open(audioFormat);
                sourceDataLine.start();

                new PlayThread(audioInputStream, sourceDataLine);

            } catch (LineUnavailableException lue) {
                lue.printStackTrace();
            }
        });
    }

    @Override
    public Insets getInsets() {
        return new Insets(40, 20, 10, 20);
    }

    private AudioFormat buildAudioFormat() {
        return new AudioFormat(44_100.0F, 16, 1, true, false);
    }
}

