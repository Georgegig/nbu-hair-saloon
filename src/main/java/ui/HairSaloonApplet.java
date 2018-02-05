package ui;
import static java.lang.System.exit;
import static java.lang.System.out;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;


public class HairSaloonApplet extends Applet{
  private BufferedReader inStream;
  private PrintWriter outStream;

  @Override
  public void init() {
    Frame applicationFrame = new Frame("Hair Saloon");
    applicationFrame.addWindowListener(
        new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
            exit(0);
          }
        }
    );
    applicationFrame.add(this, BorderLayout.CENTER);
    applicationFrame.setSize(650, 410);
    applicationFrame.setVisible(true);
    setLayout(null);

    try {
      Socket socket = new Socket("localhost", 5400);
      inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      outStream = new PrintWriter(new BufferedWriter(
              new OutputStreamWriter(socket.getOutputStream())),true);
    } catch (Exception exc) {
      out.println("Error in initialize the network - " + exc.toString());
    }

    TextArea outputArea = new TextArea();
    outputArea.setBounds(10, 260, 650, 100);
    add(outputArea);

    Label hairStyleLabel = new Label("Hair style", Label.RIGHT);
    hairStyleLabel.setBounds(10, 80, 80, 30);
    add(hairStyleLabel);

    TextField hairStyleField = new TextField();
    hairStyleField.setBounds(100, 80, 260, 30);
    add(hairStyleField);

    Button enterSaloonButton = new Button("Enter saloon");
    enterSaloonButton.setBounds(10, 30, 380, 30);
    enterSaloonButton.addActionListener(new EnterL(outStream, inStream, outputArea));
    add(enterSaloonButton);

    Button startProcedureButton = new Button("Start procedure");
    startProcedureButton.setBounds(10, 120, 185, 50);
    startProcedureButton.addActionListener(
        new StartProcedureL(outStream, inStream, outputArea, hairStyleField));
    add(startProcedureButton);

    Button restyleHaircutButton = new Button("Restyle haircut");
    restyleHaircutButton.setBounds(205, 120, 185, 50);
    restyleHaircutButton.addActionListener(new RestyleHairCutL(outStream, inStream, outputArea));
    add(restyleHaircutButton);

    Button payHaircutButton = new Button("Pay haircut");
    payHaircutButton.setBounds(10, 180, 380, 30);
    payHaircutButton.addActionListener(new PayHaircutL(outStream, inStream, outputArea));
    add(payHaircutButton);

    Button exitSaloonButton = new Button("Leave saloon");
    exitSaloonButton.setBounds(10, 220, 380, 30);
    exitSaloonButton.addActionListener(new LeaveSaloonL(outStream, inStream, outputArea));
    add(exitSaloonButton);

  }
}