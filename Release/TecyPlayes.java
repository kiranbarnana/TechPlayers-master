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

public class TecyPlayes
{
  JButton jButton;
  JFileChooser jFileChooser;
  JFrame jFrame;
  JTextField jTextField;
  static String file_path = "";
  JButton actionbutton;
  JLabel jLabel1 = new JLabel("Error Message");
  
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
        if (evnt.getSource() == TecyPlayes.this.jButton)
        {
          int return_value = TecyPlayes.this.jFileChooser.showOpenDialog(TecyPlayes.this.jFrame);
          if (return_value == 0)
          {
            TecyPlayes.this.jTextField.setText(TecyPlayes.this.jFileChooser.getSelectedFile().getAbsolutePath());
            File f = TecyPlayes.this.jFileChooser.getSelectedFile();
            TecyPlayes.file_path = f.getAbsolutePath();
            System.out.println(TecyPlayes.file_path);
          }
        }
      }
    });
    if ((file_path != null) || (file_path != "")) {
      this.actionbutton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent evnt1)
        {
          if (evnt1.getSource() == TecyPlayes.this.actionbutton) {
            try
            {
              File f = new File("config.dat");
              FileReader fr = new FileReader(f);
              BufferedReader br = new BufferedReader(fr);
              String line = "";
              while ((line = br.readLine()) != null)
              {
                Runtime r = Runtime.getRuntime();
                System.out.println(line + TecyPlayes.file_path + " -o output\\temp_tm.cpp");
                Process p = r.exec(line + TecyPlayes.file_path + " -o output\\temp_tm.cpp");
                int exitCode= p.waitFor();
                
                if(exitCode == 0)
                {
                	System.out.println("Success");
                }
                else
                {
                	System.out.println("Fail move exe");
                }
                System.out.println("cmd.exe /c move output\\temp_tm.cpp " + TecyPlayes.file_path);
                p = r.exec("cmd.exe /c move output\\temp_tm.cpp " + TecyPlayes.file_path);
                exitCode= p.waitFor();                
                if(exitCode == 0)
                {
                	System.out.println("Success");
                }
                else
                {
                	System.out.println("Fail move");
                }
              }
              jLabel1.setText("Process Completed");
            }
            catch (Exception e)
            {
              System.out.println(e.getMessage());
              jLabel1.setText("Process Failed"+e.getMessage());
            }
          }
        }
      });
    }
    this.jFrame.add(jLabel);
    this.jFrame.add(this.jTextField);
    this.jFrame.add(this.jButton);
    this.jFrame.add(this.actionbutton);
    this.jFrame.add(jLabel1);
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
        TecyPlayes obj = new TecyPlayes();
        obj.Display();
      }
    });
    System.out.println(file_path);
    System.out.println("Heloooo");
  }
}
