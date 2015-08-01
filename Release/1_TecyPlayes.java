import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
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
  JTextArea textArea = new JTextArea(5, 20);
  JScrollPane scrollPane = new JScrollPane(textArea);
  JRadioButton birdButton = new JRadioButton("C");
  JRadioButton catButton = new JRadioButton("CPP");
  
  ButtonGroup group = new ButtonGroup();
  
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
              File f;
              String cfgval="";
              if(birdButton.isSelected())
              {
            	  cfgval=birdButton.getText();
              }
              if(catButton.isSelected())
              {
            	  cfgval=catButton.getText();
              }
              if(cfgval.equals("CPP"))
              {
            	  f=new File("config_cpp.dat");
              }
              else
              {
            	  f=new File("config_c.dat");
              }
              textArea.setText(cfgval+'\n');
              FileReader fr = new FileReader(f);
              BufferedReader br = new BufferedReader(fr);
              String line = "";
              textArea.append(TecyPlayes.file_path+'\n');              
              int i=1;
              while ((line = br.readLine()) != null)
              {
            	  textArea.append("processing Config"+i+++'\n');
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
                	textArea.append("Process filed to move"+'\n');
                	break;
                }
              }
              textArea.append("Process Completed"+'\n');
            }
            catch (Exception e)
            {
              System.out.println(e.getMessage());
              textArea.append("Error While Processing"+e.getMessage()+'\n');
            }
          }
        }
      });
    }
    this.jFrame.add(jLabel);
    this.jFrame.add(this.jTextField);
    this.jFrame.add(this.jButton);
    this.jFrame.add(this.actionbutton);
    //this.jFrame.add(jLabel1);
    
    this.jFrame.add(birdButton);
    birdButton.setActionCommand("C");
    this.jFrame.add(catButton);
    catButton.setActionCommand("CPP");
    birdButton.setSelected(true);
    textArea.setEditable(false);
    group.add(birdButton);
    group.add(catButton);
    this.jFrame.add(textArea);
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
