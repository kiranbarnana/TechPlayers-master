import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ExecuteProcess
{
  JButton jButton;
  JFileChooser jFileChooser;
  JFrame jFrame;
  JTextField jTextField;
  static String file_path = "";
  JButton actionbutton;
  
  public void readConfigFile()
    throws IOException
  {
    File f = new File("D:\\config.txt");
    FileReader fr = new FileReader(f);
    BufferedReader br = new BufferedReader(fr);
    String line = "";
    while ((line = br.readLine()) != null) {
      System.out.println(line);
    }
  }
  
  public void Display()
  {
    this.jFrame = new JFrame();
    
    this.jFrame.setSize(1000, 600);
    this.jFrame.setDefaultCloseOperation(3);
    this.jFrame.setLayout(new FlowLayout(1, 150, 120));
    JLabel jLabel = new JLabel("Input File:");
    this.jTextField = new JTextField(30);
    this.jButton = new JButton("Browse");
    this.actionbutton = new JButton("Start Format");
    this.jFileChooser = new JFileChooser();
    this.jFileChooser.setFileSelectionMode(2);
    this.jButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evnt)
      {
        if (evnt.getSource() == ExecuteProcess.this.jButton)
        {
          int return_value = ExecuteProcess.this.jFileChooser.showOpenDialog(ExecuteProcess.this.jFrame);
          if (return_value == 0)
          {
            ExecuteProcess.this.jTextField.setText(ExecuteProcess.this.jFileChooser.getSelectedFile().getAbsolutePath());
            File f = ExecuteProcess.this.jFileChooser.getSelectedFile();
            ExecuteProcess.file_path = f.getAbsolutePath();
            System.out.println(ExecuteProcess.file_path);
          }
        }
      }
    });
    if ((file_path != null) || (file_path != "")) {
      this.actionbutton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent evnt1)
        {
          if (evnt1.getSource() == ExecuteProcess.this.actionbutton) {
            try
            {
              File f = new File("config.dat");
              FileReader fr = new FileReader(f);
              BufferedReader br = new BufferedReader(fr);
              String line = "";
              while ((line = br.readLine()) != null)
              {
                System.out.println(line);
                Runtime r = Runtime.getRuntime();
                Process p = r.exec(line + ExecuteProcess.file_path + " -o output/temp.cpp");
                p = r.exec("cmd /c move output/temp.cpp " + ExecuteProcess.file_path);
              }
            }
            catch (Exception e)
            {
              System.out.println(e.getMessage());
            }
          }
        }
      });
    }
    this.jFrame.add(jLabel);
    this.jFrame.add(this.jTextField);
    this.jFrame.add(this.jButton);
    this.jFrame.add(this.actionbutton);
    this.jFrame.setVisible(true);
    
    System.out.println(file_path);
  }
  
  public static void main(String[] args)
    throws IOException
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        ExecuteProcess obj = new ExecuteProcess();
        obj.Display();
      }
    });
    System.out.println(file_path);
    System.out.println("Heloooo");
  }
}
