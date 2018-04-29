package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.MatOfByte;
////import org.opencv.highgui.Highgui;
////import org.opencv.highgui.VideoCapture;
//import org.opencv.videoio.VideoCapture;
//import org.opencv.imgcodecs.Imgcodecs;


import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.bytedeco.javacpp.avcodec;

import com.fazecast.jSerialComm.SerialPort;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.System.out;
import java.util.concurrent.TimeUnit;
import org.bytedeco.javacv.OpenCVFrameConverter.ToIplImage;

public class Javacam extends javax.swing.JFrame  {
//Arduino
    static SerialPort chosenPort;
    static int x = 0;
    
    int count = 0;
//    VideoCapture webSource = null;
//
//    Mat frame = new Mat();
//    MatOfByte mem = new MatOfByte();
//    private JPanel canvas;
    
    private static FFmpegFrameRecorder recorder = null;
    private static FFmpegFrameRecorder recordertela = null;//
    private static OpenCVFrameGrabber grabber = null;
    private static OpenCVFrameGrabber grabberi = null;
    private static final int WEBCAM_DEVICE_INDEX = 0; 
    
    //Gravação do video da webcam
    private static final int CAPTUREWIDTH = 800; //largura 
    private static final int CAPTUREHRIGHT = 600; //Altura
    private static final int FRAME_RATE = 30;
    private static final int GOP_LENGTH_IN_FRAMES = 60;
    private volatile boolean runnable = true;
    private static final long serialVersionUID = 1L;
    private Catcher cat;
    private Catcheri cati;
    private Thread catcher;
    private Thread catcheri;
    private Thread threadg;
    private Thread thread;
    int tempoMM = 00;
    int tempoSeg = 00;
    int tempoMin = 00;
    int tempoHr = 00;
    String nomeArquivo = "ne";
    OutputStream saidatela ;
//    ScreenRecordingExample tela;
//    static boolean gravar = true;
    private static final double FRAME_RATEtela = 30;
    
    private static final int SECONDS_TO_RUN_FOR = 20;
    
    //private static final String outputFilename = "E:\\Desktop\\victor\\Physiometrics_V3\\mydesktop.mp4";
    boolean gravar;
    
    private static Dimension screenBounds;
//    String fileName = nomeArquivo +"tela" + ".mp4";
//        final IMediaWriter writer = ToolFactory.makeWriter(fileName);
       // String fileName = nomeArquivo +"tela" + ".mp4";
        IMediaWriter writer ;
    //String path = nomeArquivo + ".xls";
          
       
    SerialPort[] portNames = SerialPort.getCommPorts();
    
    public Javacam() {
        initComponents();
        grabberi = new OpenCVFrameGrabber(WEBCAM_DEVICE_INDEX);
        grabber = new OpenCVFrameGrabber(WEBCAM_DEVICE_INDEX);
                
        cat = new Catcher();
        cati = new Catcheri();
        catcher = new Thread(cat);
        catcher.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_start = new javax.swing.JButton();
        btn_stop = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn_conectar = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        txt_nomeProjeto = new javax.swing.JTextField();
        T_Coleta = new javax.swing.JLabel();
        txt_coleta = new javax.swing.JTextField();
        T_Individuo = new javax.swing.JLabel();
        txt_individuo = new javax.swing.JTextField();
        T_numero = new javax.swing.JLabel();
        txt_numero = new javax.swing.JTextField();
        T_Pesquisador = new javax.swing.JLabel();
        txt_pesquisador = new javax.swing.JTextField();
        T_NomeProjeto = new javax.swing.JLabel();
        T_NomeProjeto1 = new javax.swing.JLabel();
        canvas1 = new java.awt.Canvas();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(102, 102, 102));
        setMinimumSize(new java.awt.Dimension(1200, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_start.setText("Start");
        btn_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startActionPerformed(evt);
            }
        });
        getContentPane().add(btn_start, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 200, 70, -1));

        btn_stop.setText("Stop");
        btn_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stopActionPerformed(evt);
            }
        });
        getContentPane().add(btn_stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 240, 70, -1));

        jLabel1.setFont(new java.awt.Font("Lucida Console", 0, 24)); // NOI18N
        jLabel1.setText("PhysioMetrics");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, 20));

        btn_conectar.setText("Conectar");
        btn_conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conectarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_conectar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 160, 120, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 120, 90, -1));
        getContentPane().add(txt_nomeProjeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 200, -1));

        T_Coleta.setText("Coleta");
        getContentPane().add(T_Coleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, -1, -1));
        getContentPane().add(txt_coleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 180, -1));

        T_Individuo.setText("Indivíduo");
        getContentPane().add(T_Individuo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));
        getContentPane().add(txt_individuo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 170, -1));

        T_numero.setText("Número");
        getContentPane().add(T_numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, -1, -1));
        getContentPane().add(txt_numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 50, 70, -1));

        T_Pesquisador.setText("Pesquisador");
        getContentPane().add(T_Pesquisador, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 50, -1, -1));
        getContentPane().add(txt_pesquisador, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 50, 70, -1));

        T_NomeProjeto.setText("Dados");
        getContentPane().add(T_NomeProjeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        T_NomeProjeto1.setText("Nome do Projeto");
        getContentPane().add(T_NomeProjeto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));
        getContentPane().add(canvas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 800, 600));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 790, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //btn Start
    private void btn_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startActionPerformed
        catcher.stop(); // Stop Preview
        catcher.isInterrupted();
        catcheri = new Thread(cati);
        catcheri.start();

        runnable = true;
        
        
        nomeArquivo = "Projeto_" + txt_nomeProjeto.getText() +
                      "_Participante_" + txt_individuo.getText() +
                      "_Coleta_" + txt_coleta.getText() + 
                      "_n_" + txt_numero.getText() ; //Nomeando o arquivo
        
        //Arduino data Save
        String path = nomeArquivo + ".xls"; //Nomeando o arquivo Excel
        
        File file = new File(path);
        if(file.exists()){

            try {
                new File(path+1+".txt").createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            threadg = new Thread(){
                    @Override public void run() {
                        System.out.println("inicio");
                        Scanner scanner = new Scanner(chosenPort.getInputStream());
                        while(scanner.hasNextLine()) {
                        //Calculo de Tempo
                        long tempoEscreveLinha = System.currentTimeMillis();
                            tempoMM++;
                            if (tempoMM == 10){
                                tempoSeg++;
                                tempoMM = 0;
                            }
                            if (tempoSeg == 60){
                                tempoMin++;
                                tempoSeg = 0;
                            }
                            if (tempoMin == 60){
                                tempoHr++;
                                tempoMin = 0;
                            }
                            String line = scanner.nextLine();
                            //Output do arduino pela porta COM é .HR.2.GSR.456.EMG.336
                            String[] output = line.split("\\.");
                            String hrvalor = output[2];
                            //System.out.println("O Valor do HR é " + hrvalor);
                            String gsrvalor = output[4];
                            //System.out.println("O Valor do GSR é " + gsrvalor);
                            String emgvalor = output[6];
                            //Escrevendo Excel com \t para mudar de coluna
                            try {
                                jTextField1.setText("Tempo: " + "\t" + 
                                                    "mm " + tempoMM + "\t" + 
                                                    tempoHr + ":" + tempoMin + ":" + tempoSeg + "\t" + 
                                                    "HR: " + "\t" + hrvalor + "\t" + 
                                                    "GSR: " + "\t" + gsrvalor + "\t" +
                                                    "EMG: " + "\t" + emgvalor + "\t"); //Escrever no Programa
                                        
                                writer.write("Tempo: " + "\t" + 
                                            "mm " + tempoMM + "\t" + 
                                            tempoHr + ":" + tempoMin + ":" + tempoSeg + "\t" + 
                                            "HR: " + "\t" + hrvalor + "\t" + 
                                            "GSR: " + "\t" + gsrvalor + "\t" +
                                            "EMG: " + "\t" + emgvalor + "\t");

                                System.out.println("Tempo: " + "\t" + 
                                                    "mm " + tempoMM + "\t" + 
                                                    tempoHr + ":" + tempoMin + ":" + tempoSeg + "\t" + 
                                                    "HR: " + "\t" + hrvalor + "\t" + 
                                                    "GSR: " + "\t" + gsrvalor + "\t" +
                                                    "EMG: " + "\t" + emgvalor + "\t");
                                
                               writer.newLine();  //Escreveu uma linha
                            }catch (IOException ex) {
                                Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                            }try { 
                                TimeUnit.MILLISECONDS.sleep(100);// tempo de espera tempo q leva 1 segundo
                                long totalTime = System.currentTimeMillis() - tempoEscreveLinha;
                                                                
                            }catch (InterruptedException ex) {
                                Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                            }try {
                                writer.flush();
                            }catch (IOException ex) {
                                Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        
                        scanner.close();
                        try {
                            writer.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            
         threadg.start();
        } catch (IOException ex) {
            Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }//GEN-LAST:event_btn_startActionPerformed
    //btn Stop
    private void btn_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stopActionPerformed
       
        catcheri.stop();
        paratela();

       
       
    }//GEN-LAST:event_btn_stopActionPerformed

    //btn Conectar
    private void btn_conectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_conectarActionPerformed
        if(btn_conectar.getText().equals("Conectar")){
        // attempt to connect to the serial port
            chosenPort = SerialPort.getCommPort(jComboBox1.getSelectedItem().toString());
            chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
            if(chosenPort.openPort()) { //Si conectar...
                btn_conectar.setText("Disconectar");
                jComboBox1.setEnabled(false);
            }
        }else{
            // disconnect from the serial port
            chosenPort.closePort();
            jComboBox1.setEnabled(true);
            btn_conectar.setText("Connect");
            //series.clear();
            //x = 0;
        }
    }//GEN-LAST:event_btn_conectarActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        for(int i = 0; i < portNames.length; i++){
            jComboBox1.addItem(portNames[i].getSystemPortName());
        }
      
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

   
    
    
    
    //classe com video da cam na tela e a gravação da tela
    class Catcheri implements Runnable {
        @Override
        public void run() {
            synchronized (this) {
               // while (runnable) {
                    try {
                        grabberi.setImageWidth(CAPTUREWIDTH);
                        grabberi.setImageHeight(CAPTUREHRIGHT);
                        grabberi.start();
                        screenBounds = Toolkit.getDefaultToolkit().getScreenSize();
                        
                        String fileName = nomeArquivo +"tela" + ".mp4";
                        writer = ToolFactory.makeWriter(fileName);

                        // We tell it we're going to add one video stream, with id 0,
                        // at position 0, and that it will have a fixed frame rate of FRAME_RATE.CODEC_ID_H264
                        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, 
                                   screenBounds.width, screenBounds.height);

                        long startTimetela = System.nanoTime();
                        
                         
                        recorder = new FFmpegFrameRecorder(nomeArquivo + ".mp4",
                                CAPTUREWIDTH, CAPTUREHRIGHT, 2);
                        recorder.setInterleaved(true);
                        // video options //
                        recorder.setVideoOption("tune", "zerolatency");
                        recorder.setVideoOption("preset", "ultrafast");
                        recorder.setVideoOption("crf", "28");
                        recorder.setVideoBitrate(2000000);
                        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
                        
                        recorder.setVideoCodec(HIDE_ON_CLOSE);
                        
                        recorder.setFormat("mp4");
                        recorder.setFrameRate(FRAME_RATE);
                        recorder.setGopSize(GOP_LENGTH_IN_FRAMES);
                        // audio options //
                        //Com essa configuração não grava o audio, de nem dos dois (Webcam ou Monitor)
//                        recorder.setAudioOption("crf", "20");
//                        recorder.setAudioQuality(100);
//                        recorder.setAudioBitrate(192000);
//                        recorder.setSampleRate(44100);
//                        recorder.setAudioChannels(2);
//                        recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
                        
                        
                        recorder.start();
                        
                        
                        Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                        
                        Frame capturedFrame = null;
                       
                        Java2DFrameConverter paintConverter = new Java2DFrameConverter();
                        
                        long startTime = System.currentTimeMillis();
                        long frame = 0;
                        while ((capturedFrame = grabberi.grab()) != null&&runnable) {
                            BufferedImage buff = paintConverter.getBufferedImage(capturedFrame, 1);
                            
                            Graphics g = canvas1.getGraphics();
                            g.drawImage(buff, 0, 0, CAPTUREWIDTH, CAPTUREHRIGHT, 0, 0, buff.getWidth(), buff.getHeight(), null);
                            
                            
                            recorder.record(capturedFrame);
                           BufferedImage screen = getDesktopScreenshot();
            

                            // convert to the right image type
                            BufferedImage bgrScreen = convertToType(screen, 
                                   BufferedImage.TYPE_3BYTE_BGR);

                            // encode the image to stream #0
                            writer.encodeVideo(0, bgrScreen, System.nanoTime() - startTimetela, 
                                   TimeUnit.NANOSECONDS);
                            System.out.println("gravando");
                            // sleep for frame rate milliseconds
                            try {
                                Thread.sleep((long) (1000 / FRAME_RATE));
                            } 
                            catch (InterruptedException e) {
                                // ignore
                            }
                           
                           
                            
                            frame++;
                            long waitMillis = 1000 * frame / FRAME_RATE - (System.currentTimeMillis() - startTime);
                            while (waitMillis <= 0) {
                                // If this error appeared, better to consider lower FRAME_RATE.
                                //System.out.println("[ERROR] grab image operation is too slow to encode, skip grab image at frame = " + frame + ", waitMillis = " + waitMillis);
                                recorder.record(capturedFrame);  // use same capturedFrame for fast processing.
                            
                                frame++;
                                waitMillis = 1000 * frame / FRAME_RATE - (System.currentTimeMillis() - startTime);
                            }
                            //System.out.println("frame " + frame + ", System.currentTimeMillis() = " + System.currentTimeMillis() + ", waitMillis = " + waitMillis);
                            Thread.sleep(waitMillis);
                        }
                    }catch (FrameGrabber.Exception ex) {
                    Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                    Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                } 

            }
        }
    }
    
    public void paratela(){
        
        System.out.println("parou o gravando");
        // tell the writer to close and write the trailer if  needed
        
        writer.close();
        
    }
    
    
    public static BufferedImage convertToType(BufferedImage sourceImage, int targetType) {
        
        BufferedImage image;

        // if the source image is already the target type, return the source image
        if (sourceImage.getType() == targetType) {
            image = sourceImage;
        }
        // otherwise create a new image of the target type and draw the new image
        else {
            image = new BufferedImage(sourceImage.getWidth(), 
                 sourceImage.getHeight(), targetType);
            image.getGraphics().drawImage(sourceImage, 0, 0, null);
        }

        return image;
        
    }
    
    private static BufferedImage getDesktopScreenshot() {
        try {
            Robot robot = new Robot();
            Rectangle captureSize = new Rectangle(screenBounds);
            return robot.createScreenCapture(captureSize);
        } 
        catch (AWTException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    class Catcher implements Runnable {
        @Override
        public void run() {
            synchronized (this) {
               // while (runnable) {
                    try {
                        
                        grabber.setImageWidth(CAPTUREWIDTH);
                        grabber.setImageHeight(CAPTUREHRIGHT);
                        grabber.start();

                        Frame capturedFrame = null;
                        Java2DFrameConverter paintConverter = new Java2DFrameConverter();
                        long startTime = System.currentTimeMillis();
                        long frame = 0;
                        while ((capturedFrame = grabber.grab()) != null&&runnable) {
                            BufferedImage buff = paintConverter.getBufferedImage(capturedFrame, 1);
                            Graphics g = canvas1.getGraphics();
                            g.drawImage(buff, 0, 0, CAPTUREWIDTH, CAPTUREHRIGHT, 0, 0, buff.getWidth(), buff.getHeight(), null);
                            //recorder.record(capturedFrame);
                            frame++;
                            long waitMillis = 1000 * frame / FRAME_RATE - (System.currentTimeMillis() - startTime);
                            while (waitMillis <= 0) {
                                // If this error appeared, better to consider lower FRAME_RATE.
                                //System.out.println("[ERROR] grab image operation is too slow to encode, skip grab image at frame = " + frame + ", waitMillis = " + waitMillis);
                                //recorder.record(capturedFrame);  // use same capturedFrame for fast processing.
                                frame++;
                                waitMillis = 1000 * frame / FRAME_RATE - (System.currentTimeMillis() - startTime);
                            }
                            //System.out.println("frame " + frame + ", System.currentTimeMillis() = " + System.currentTimeMillis() + ", waitMillis = " + waitMillis);
                            Thread.sleep(waitMillis);
                        }
                    }catch (FrameGrabber.Exception ex) {
                    Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                    Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Javacam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Javacam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Javacam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Javacam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
         // System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
          // load native library of opencv
        /* Create and display the form */
        String opencvpath = System.getProperty("user.dir") + "\\cv\\";
        String libPath = System.getProperty("java.library.path");
       // System.load(opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll");
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Javacam().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel T_Coleta;
    private javax.swing.JLabel T_Individuo;
    private javax.swing.JLabel T_NomeProjeto;
    private javax.swing.JLabel T_NomeProjeto1;
    private javax.swing.JLabel T_Pesquisador;
    private javax.swing.JLabel T_numero;
    private javax.swing.JButton btn_conectar;
    private javax.swing.JButton btn_start;
    private javax.swing.JButton btn_stop;
    private java.awt.Canvas canvas1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txt_coleta;
    private javax.swing.JTextField txt_individuo;
    private javax.swing.JTextField txt_nomeProjeto;
    private javax.swing.JTextField txt_numero;
    private javax.swing.JTextField txt_pesquisador;
    // End of variables declaration//GEN-END:variables
}
