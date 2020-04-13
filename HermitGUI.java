import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import graph.*;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.UIManager;



public class HermitGUI extends JFrame {

	protected static boolean plotten = false;
	private JPanel contentPane;
	private JTextField nodeCountTextField;
	private JButton btnGenerii;
	private JTextField nodesTextField;
	private JTextArea functionTextArea;
	private JTextField korakTextField;
	private JTextField pocetakTextField;
	private JTextField krajTextField;
	private JTextField theXTextField;
	private JTextField solvedTextField;
	
	
	
	Hermite model;
	
	
	
	
	int br;
	double poc;
	double kraj;
	double korak;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	G2Dint graph         = new G2Dint();    // Graph class to do the plotting
    Axis xaxis;
    Axis yaxis;
    DataSet data;

    Label flabel   = new Label("Funkcija");
    TextField finput     = new TextField(30);      // Input for the function to plot
    JButton plot          = new JButton("Crtaj"); // Button to plot it.
    private JTextField izvodiTextField;
	int maxder;
  
	
	
	
	
	
	
	void plot() {
        int points;
        double maximum;
        double minimum;
        double x;
        int count = 0;
        boolean error = false;

        try {
             points   = 1000;
        } catch(Exception e) {
//             this.showStatus("Error with number of points!");
             System.out.println("Number of points error "+e.getMessage());
             return;
        }

        try {
           maximum = kraj+korak;
        } catch(Exception e) {
 //            this.showStatus("Error with X maximum value!");
             System.out.println("X maximum error "+e.getMessage());
             return;
        }

        try {
           minimum = poc-korak;
        } catch(Exception e) {
   //          this.showStatus("Error with X minimum value!");
             System.out.println("X minimum error "+e.getMessage());
             return;
        }

       

        double d[] = new double[2*points];

       // this.showStatus("Calculating points!");

        for(int i=0; i<points; i++) {

            x = minimum + i*(maximum-minimum)/(points-1);
            d[count] = x;
            try {
                 d[count+1] = model.calcValue(x);
                 count += 2;
            } catch(Exception e) { error = true; }

        }


        if(count <= 2) {
//            this.showStatus("Error NO POINTS to PLOT!"); 
            System.out.println("Error NO POINTS to PLOT!");
            return;
        } else
        if( error ) {
 //           this.showStatus("Error while Calculating points!"); 
            System.out.println("Error while calculating points!");
	 }


        yaxis.setTitleText(finput.getText());

        data.deleteData();

        try {
              data.append(d,count/2);
        } catch(Exception e) {
   //         this.showStatus("Error while appending data!"); 
            System.out.println("Error while appending data!");
            return;
	 }
             

        
     }
	
	
	
	
	
    void plotGraph1(double min, double max) {
        int points;
        double maximum;
        double minimum;
        double x;
        int count = 0;
        boolean error = false;

        try {
            points   = 1000;
       } catch(Exception e) {
//            this.showStatus("Error with number of points!");
            System.out.println("Number of points error "+e.getMessage());
            return;
       }

        maximum=max;
        minimum=min;

    

        double d[] = new double[2*points];

        for(int i=0; i<points; i++) {

            x = minimum + i*(maximum-minimum)/(points-1);
            d[count] = x;
            try {
                 d[count+1] = model.calcValue(x);
                 count += 2;
            } catch(Exception e) { error = true; }

        }


        if(count <= 2) {
//            this.showStatus("Error NO POINTS to PLOT!"); 
            System.out.println("Error NO POINTS to PLOT!");
            return;
        } else
        if( error ) {
 //           this.showStatus("Error while Calculating points!"); 
            System.out.println("Error while calculating points!");
	 }


        yaxis.setTitleText(finput.getText());

        data.deleteData();

        try {
              data.append(d,count/2);
        } catch(Exception e) {
   //         this.showStatus("Error while appending data!"); 
            System.out.println("Error while appending data!");
            return;
	 }
             

     }
	
	
	
	
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HermitGUI frame = new HermitGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HermitGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 358, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBrojvorova = new JLabel("Broj \u010Dvorova: ");
		lblBrojvorova.setBounds(10, 11, 91, 14);
		contentPane.add(lblBrojvorova);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Broj \u010Dvorova", "Broj \u010Dvorova, korak, po\u010Detni interval", "Broj \u010Dvorova, korak, krajnji interval", "Broj \u010Dvorova, interval", "Korak, interval"}));
		comboBox.setBounds(10, 82, 221, 23);
		contentPane.add(comboBox);
		
		nodeCountTextField = new JTextField();
		nodeCountTextField.setBounds(96, 8, 75, 20);
		contentPane.add(nodeCountTextField);
		nodeCountTextField.setColumns(10);
		
		
		
		
		
		
		
		
		JButton btnIzraunajKoeficijente = new JButton("Izra\u010Dunaj koeficijente");
		btnIzraunajKoeficijente.setBackground(new Color(220, 220, 220));
		btnIzraunajKoeficijente.setEnabled(false);
		JButton btnRacunaj = new JButton("Ra\u010Dunaj");
		btnRacunaj.setBackground(new Color(220, 220, 220));
		JButton btnPrikaiGrafik = new JButton("Prika\u017Ei grafik");
		btnPrikaiGrafik.setBackground(new Color(220, 220, 220));
		
		finput.setText("sin(x)"); 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		btnGenerii = new JButton("Generi\u0161i");
		btnGenerii.setBackground(new Color(220, 220, 220));
		btnGenerii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String choice=comboBox.getSelectedItem().toString();
				
				
				String t;
				String zeros;
				String blanks;
				switch (choice) {
				case "Broj \u010Dvorova":
					br=Integer.parseInt(nodeCountTextField.getText());
					maxder=Integer.parseInt(izvodiTextField.getText());
					zeros="";
					blanks="";
					for(int i=1;i<br;i++)
					{
						zeros+="0 ";
						blanks+="* ";
					}
						
					zeros+="0";
					blanks+="*";
					nodesTextField.setText(zeros);
					
					functionTextArea.setText(zeros);
					for(int i=0;i<maxder;i++)
					{
						functionTextArea.append("\n"+blanks);
					}
					//functionTextArea.append(blanks);
					
					break;
					
				case "Broj \u010Dvorova, korak, po\u010Detni interval":
					br=Integer.parseInt(nodeCountTextField.getText());
					poc=Double.parseDouble(pocetakTextField.getText());
					korak=Double.parseDouble(korakTextField.getText());
					maxder=Integer.parseInt(izvodiTextField.getText());
					
					
					zeros="";
					blanks="";
					for(int i=1;i<br;i++)
					{
						zeros+="0 ";
						blanks+="* ";
					}
						
					zeros+="0";
					blanks+="*";
					nodesTextField.setText(zeros);
					
					functionTextArea.setText(zeros);
					for(int i=0;i<maxder;i++)
					{
						functionTextArea.append("\n"+blanks);
					}
					
					t="";			
					for(int i=1;i<br;i++)
						t+="0 ";
					t+="0";
					
					t=""+poc+" ";
					for(int i=1;i<br-1;i++)
						t+=(poc+(i*korak))+" ";
					t+=(poc+((br-1)*korak));
					nodesTextField.setText(t);
					krajTextField.setText(""+(poc+((br-1)*korak)));
					break;
				case "Broj \u010Dvorova, korak, krajnji interval":
					br=Integer.parseInt(nodeCountTextField.getText());
					kraj=Double.parseDouble(krajTextField.getText());
					korak=Double.parseDouble(korakTextField.getText());
					maxder=Integer.parseInt(izvodiTextField.getText());
					
					poc=kraj-((br-1)*korak);
					
					
					zeros="";
					blanks="";
					for(int i=1;i<br;i++)
					{
						zeros+="0 ";
						blanks+="* ";
					}
						
					zeros+="0";
					blanks+="*";
					nodesTextField.setText(zeros);
					
					functionTextArea.setText(zeros);
					for(int i=0;i<maxder;i++)
					{
						functionTextArea.append("\n"+blanks);
					}
					
					
					
					
					
					t=""+poc+" ";
					for(int i=1;i<br-1;i++)
						t+=(poc+(i*korak))+" ";
					t+=(poc+((br-1)*korak));
					nodesTextField.setText(t);
					pocetakTextField.setText(""+poc);
					break;
				case "Broj \u010Dvorova, interval":
					br=Integer.parseInt(nodeCountTextField.getText());
					poc=Double.parseDouble(pocetakTextField.getText());
					kraj=Double.parseDouble(krajTextField.getText());
					maxder=Integer.parseInt(izvodiTextField.getText());
					
					korak=(kraj-poc)/(br-1);
					

					zeros="";
					blanks="";
					for(int i=1;i<br;i++)
					{
						zeros+="0 ";
						blanks+="* ";
					}
						
					zeros+="0";
					blanks+="*";
					nodesTextField.setText(zeros);
					
					functionTextArea.setText(zeros);
					for(int i=0;i<maxder;i++)
					{
						functionTextArea.append("\n"+blanks);
					}
					
					
					
					t=""+poc+" ";
					for(int i=1;i<br-1;i++)
						t+=(poc+(i*korak))+" ";
					t+=(poc+((br-1)*korak));
					nodesTextField.setText(t);
					korakTextField.setText(""+korak);
					break;
				case "Korak, interval":
					poc=Double.parseDouble(pocetakTextField.getText());
					kraj=Double.parseDouble(krajTextField.getText());
					korak=Double.parseDouble(korakTextField.getText());
					maxder=Integer.parseInt(izvodiTextField.getText());
					
					
					br=(int) ((kraj-poc)/korak)+1;


					zeros="";
					blanks="";
					for(int i=1;i<br;i++)
					{
						zeros+="0 ";
						blanks+="* ";
					}
						
					zeros+="0";
					blanks+="*";
					nodesTextField.setText(zeros);
					
					functionTextArea.setText(zeros);
					for(int i=0;i<maxder;i++)
					{
						functionTextArea.append("\n"+blanks);
					}
					
					
					
					
					
					t=""+poc+" ";
					for(int i=1;i<br-1;i++)
						t+=(poc+(i*korak))+" ";
					t+=(poc+((br-1)*korak));
					nodesTextField.setText(t);
					nodeCountTextField.setText(""+br);
					break;
				default:
					break;
				}
				
				
			nodesTextField.setEnabled(true);	
			functionTextArea.setEnabled(true);
			
			btnIzraunajKoeficijente.setEnabled(true);
				
				
			btnPrikaiGrafik.setEnabled(false);
			theXTextField.setEnabled(false);
			btnRacunaj.setEnabled(false);
			solvedTextField.setEnabled(false);	
				
				
				
				
				
			}
		});
		btnGenerii.setBounds(237, 39, 89, 66);
		contentPane.add(btnGenerii);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 116, 282, 50);
		contentPane.add(scrollPane);
		
		nodesTextField = new JTextField();
		nodesTextField.setFont(new Font("Monospaced", Font.PLAIN, 13));
		nodesTextField.setEnabled(false);
		scrollPane.setViewportView(nodesTextField);
		nodesTextField.setColumns(10);
		
		JLabel lblX = new JLabel("x:");
		lblX.setBounds(10, 117, 46, 45);
		contentPane.add(lblX);
		
		JLabel lblFx = new JLabel("f(x):");
		lblFx.setBounds(10, 173, 46, 46);
		contentPane.add(lblFx);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(45, 171, 282, 210);
		contentPane.add(scrollPane_1);
		
		functionTextArea = new JTextArea();
		functionTextArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		functionTextArea.setEnabled(false);
		scrollPane_1.setViewportView(functionTextArea);
		functionTextArea.setColumns(10);
		
		

		
		JLabel lblKorak = new JLabel("Korak:");
		lblKorak.setBounds(181, 11, 46, 14);
		contentPane.add(lblKorak);
		
		korakTextField = new JTextField();
		korakTextField.setBounds(237, 8, 91, 20);
		contentPane.add(korakTextField);
		korakTextField.setColumns(10);
		
		JLabel lblInterval = new JLabel("Interval:");
		lblInterval.setBounds(10, 36, 57, 14);
		contentPane.add(lblInterval);
		
		pocetakTextField = new JTextField();
		pocetakTextField.setBounds(77, 33, 69, 20);
		contentPane.add(pocetakTextField);
		pocetakTextField.setColumns(10);
		
		krajTextField = new JTextField();
		krajTextField.setText("");
		krajTextField.setBounds(162, 33, 69, 20);
		contentPane.add(krajTextField);
		krajTextField.setColumns(10);
		
		JLabel label = new JLabel("-");
		label.setBounds(152, 36, 39, 14);
		contentPane.add(label);
		
		JLabel lblNekaJeX = new JLabel("Ra\u010Dunaj za X:");
		lblNekaJeX.setBounds(10, 429, 91, 14);
		contentPane.add(lblNekaJeX);
		
		theXTextField = new JTextField();
		theXTextField.setEnabled(false);
		theXTextField.setBounds(115, 426, 156, 20);
		contentPane.add(theXTextField);
		theXTextField.setColumns(10);
		

		btnRacunaj.setEnabled(false);
		btnRacunaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double x=Double.parseDouble(theXTextField.getText());
				double rez=model.calcValue(x);
				
				solvedTextField.setText(""+rez);
				
			}
		});
		btnRacunaj.setBounds(10, 455, 91, 23);
		contentPane.add(btnRacunaj);
		
		solvedTextField = new JTextField();
		solvedTextField.setEditable(false);
		solvedTextField.setEnabled(false);
		solvedTextField.setBounds(115, 456, 156, 20);
		contentPane.add(solvedTextField);
		solvedTextField.setColumns(10);
		
		
		
		
		
		
		
		TextField funkcija=new TextField();
		JLabel fja=new JLabel("Dobijen polinom:");
		
		
		btnPrikaiGrafik.setEnabled(false);
		btnPrikaiGrafik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				poc=model.nodes[0];
				kraj=model.nodes[model.dimenX-1];
				
				//grafik.init();
				if(!plotten)
				{
				
				//G2Dint graph         = new G2Dint(); 
				setBounds(100, 100, 750, 540);
				//setBounds(100, 100, 358, 540);
				graph.setBounds(350, 10, 380, 370);
				contentPane.add(graph);
				
				
				fja.setBounds(10, 425, 115, 23);
				funkcija.setBounds(130, 425, 600, 27);
				funkcija.setEditable(false);
				finput.setBounds(435, 392, 155, 20);
				flabel.setBounds(375, 392, 50, 20);
				plot.setBounds(610, 392, 100, 20);
				JLabel bounds = new JLabel("Granice:");
				bounds.setBounds(370, 392, 70, 20);
				JTextField minn = new JTextField("");
				JTextField maxx = new JTextField("");
				minn.setBounds(450, 392, 70, 20);
				maxx.setBounds(530, 392, 70, 20);
				plot.setBackground(new Color(220, 220, 220));
				plot.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						double mx=Double.parseDouble(maxx.getText());
						double mn=Double.parseDouble(minn.getText());
				        plotGraph1(mn,mx);
						//plot();
				         graph.repaint();
						
					}});
				
				//contentPane.add(finput);
				//contentPane.add(flabel);
				contentPane.add(plot);
				contentPane.add(funkcija);
				contentPane.add(fja);
				contentPane.add(bounds);
				contentPane.add(minn);
				contentPane.add(maxx);
				xaxis = graph.createXAxis();
		         xaxis.setTitleText("X");

		         yaxis = graph.createYAxis();


		         data = new DataSet();

		         xaxis.attachDataSet(data);
		         yaxis.attachDataSet(data);
		         graph.attachDataSet(data);


		         graph.setDataBackground(new Color(239, 246, 247));
		         graph.setBackground(new Color(211, 211, 211));
		         plotten=true;
				}
		         funkcija.setText(model.funcToString());
		         funkcija.repaint();
		         finput.setText(model.funcToString());
		         finput.repaint();
		         //System.out.println(model.funcToString());
		         plot();
		         graph.repaint();

				
				
				
				
				
				
		         lblNekaJeX.setBounds(10, 476, 91, 14);
		         theXTextField.setBounds(115, 470, 156, 23);
		         
		         btnRacunaj.setBounds(285, 470, 91, 23);
		         solvedTextField.setBounds(390, 470, 156, 23);
				
				
				
				
			}
		});
		btnPrikaiGrafik.setBounds(212, 392, 116, 23);
		contentPane.add(btnPrikaiGrafik);

		
		
		
		
		
		
		
		btnIzraunajKoeficijente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				double [] nodes =new double[br];
				
				
			String[] temp1=nodesTextField.getText().split(" ");		
			String[] redovi=functionTextArea.getText().split("\\r?\\n");
			String [][] funcs = new String [maxder+1][br];
			

			for(int i=0;i<br;i++)
			{
				nodes[i]=Double.parseDouble(temp1[i]);
				

			}
			for(int i=0;i<maxder+1;i++)
			{
				String[] temp2=redovi[i].split(" ");
				for(int j=0;j<br;j++)
				{
					funcs [i][j]=temp2[j];
				}
				

			}
				
			model=new Hermite(nodes, maxder+1, funcs);	
				
			btnPrikaiGrafik.setEnabled(true);
				theXTextField.setEnabled(true);
				btnRacunaj.setEnabled(true);
				solvedTextField.setEnabled(true);
			
				
			if(plotten)
				funkcija.setText(model.funcToString());
			}
		});
		btnIzraunajKoeficijente.setBounds(46, 392, 156, 23);
		contentPane.add(btnIzraunajKoeficijente);
		
		JLabel lblNajveiDatiIzvod = new JLabel("Najve\u0107i dati izvod:");
		lblNajveiDatiIzvod.setBounds(10, 61, 116, 14);
		contentPane.add(lblNajveiDatiIzvod);
		
		izvodiTextField = new JTextField();
		izvodiTextField.setBounds(123, 58, 106, 20);
		contentPane.add(izvodiTextField);
		izvodiTextField.setColumns(10);
		
	}
}
