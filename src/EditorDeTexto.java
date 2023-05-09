import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;

public class EditorDeTexto {
     private JScrollPane jScrollPane;
    private JMenuBar menuBar;
    private JPanel panel;
    private JTextArea texto;
   private  JFileChooser fileChooser;
    public EditorDeTexto() {
            panel = new JPanel();
            panel.setLayout(new BorderLayout());
            menuBar = new JMenuBar();
            JMenu archivo= new JMenu("Archivo");
            JMenuItem guardar = new JMenuItem("Guardar");
            JMenuItem abrir = new JMenuItem("Abrir");
            JMenuItem nuevo = new JMenuItem("Nuevo");



            //Creamos el boton y la funcion abrir archivo
            abrir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){

                    int seleccion = fileChooser.showOpenDialog(null);
                    if(seleccion == JFileChooser.APPROVE_OPTION){
                        File archivo = fileChooser.getSelectedFile();
                        try{
                            BufferedReader lector = new BufferedReader(new FileReader(archivo));
                            String linea;
                            StringBuilder contenido = new StringBuilder();
                            while((linea = lector.readLine()) != null){
                                System.out.println(linea);
                                contenido.append(linea + "\n");
                            }
                            lector.close();
                            texto.setText(contenido.toString());
                        }catch(IOException ee){
                            System.out.println(ee.getMessage());
                        }
                    }
                }
            });
            archivo.add(abrir);

            //Creamos el funcionamiento del accion listener del boton nuevo
            nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                texto.setText(" ");
            }});
             archivo.add(nuevo);

             //Creamos el action listener de guardar y su funcionamiento
        guardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int seleccion = fileChooser.showSaveDialog(null);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File archivo = fileChooser.getSelectedFile();
                    try {
                        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo));
                        String[] lineas = texto.getText().split("\n");
                        for (String linea : lineas) {
                            escritor.write(linea + "\n");
                            escritor.newLine();
                        }
                        escritor.close();
                    } catch (IOException t){
                        t.printStackTrace();
                    }
                }

            }
        });
        archivo.add(guardar);
        menuBar.add(archivo);
        fileChooser = new JFileChooser();
        texto = new JTextArea();
        jScrollPane = new JScrollPane(texto);
        panel.add(jScrollPane, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        EditorDeTexto editor = new EditorDeTexto();
        JFrame frame = new JFrame("Editor de texto");
        frame.setJMenuBar(editor.menuBar);
        frame.setContentPane(editor.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1400, 1200);

    }

}
