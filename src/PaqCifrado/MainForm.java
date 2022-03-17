package PaqCifrado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MainForm extends JFrame {

    private JButton cifrarButton;
    private JButton descifrarButton;
    private JButton importarButton;
    private JButton exportarButton;
    private JButton nuevoCifradoButton;
    private JButton salirButton;
    private JTextField tfIntroducir;
    private JTextField tfEscribir;
    private JLabel lbSuperior;
    private JLabel lbInferior;
    private JPanel Principal;
    private JLabel lbExplicar;
    private JButton okbtn;
    int sel;


    public MainForm(){
        setContentPane(Principal);
        setTitle("Encriptador");
        setSize(450, 300);
        setVisible(true);
        Alfabeto codigo = new Alfabeto();

        cifrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sel != 1) {
                    lbSuperior.setText("Texto a cifrar: ");
                    lbInferior.setText("Texto cifrado: ");
                    lbExplicar.setText("Cifrar texto");
                    sel = 1;
                    tfIntroducir.setText("");
                    tfEscribir.setText("");
                }

            }
        });
        descifrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (sel != 2) {
                   lbSuperior.setText("Texto cifrado: ");
                   lbInferior.setText("Texto descifrado: ");
                   lbExplicar.setText("Descifrar texto");
                   sel = 2;
                   tfIntroducir.setText("");
                   tfEscribir.setText("");
               }
            }
        });
        importarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (sel != 3) {
                   lbExplicar.setText("Introduce el nombre del .dat a importar sin extensiones");
                   lbSuperior.setText("Cifrado a importar: ");
                   sel = 3;
                   tfIntroducir.setText("");
                   tfEscribir.setText("");
               }
            }

        });
        exportarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sel != 4) {
                    lbExplicar.setText("Introduce el nombre para el cifrado: ");
                    sel = 4;
                    tfIntroducir.setText("");
                    tfEscribir.setText("");
                }
            }
        });
        nuevoCifradoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Alfabeto b = new Alfabeto();
                codigo.copiar(b);
                tfIntroducir.setText("");
                tfEscribir.setText("");
                lbExplicar.setText("Nuevo cifrado creado---");
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                return;
            }
        });
        okbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (sel){
                    case 1:
                        tfEscribir.setText(cifrar(tfIntroducir.getText(), codigo));
                        break;
                    case 2:
                        tfEscribir.setText(descifrar(tfIntroducir.getText(), codigo));
                        break;
                    case 3:

                            try {
                                Alfabeto b = importar(tfIntroducir.getText());
                                codigo.copiar(b);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } catch (ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }
                            lbExplicar.setText("Importado---");

                        break;
                    case 4:
                        try {
                            exportar(codigo, tfIntroducir.getText());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        lbExplicar.setText("Cifrado exportado---");
                        break;
                }
            }
        });
    }


    private String descifrar(String cifrado, Alfabeto codigo){
        String descifrado = new String("");
        for (int i = 0; i < cifrado.length(); i++) {
            for (int j = 0; j < 256; j++){
                if (cifrado.charAt(i) == codigo.alf[j])
                    descifrado += (char) j;
            }
        }
        return descifrado;
    }

    private String cifrar(String descifrado, Alfabeto codigo){
        String cifrado = new String("");
        for (int i = 0; i < descifrado.length(); i++){
            cifrado += (char) (codigo.alf[(int) (descifrado.charAt(i))]);
        }
        return cifrado;
    }

    private void exportar(Alfabeto codigo, String nombre) throws IOException {
        FileOutputStream fos = new FileOutputStream(nombre + ".dat");
        ObjectOutputStream salida = new ObjectOutputStream(fos);
        salida.writeObject(codigo);
        fos.close();
        salida.close();
    }

    private Alfabeto importar(String nombre) throws IOException, ClassNotFoundException {

            FileInputStream fis = new FileInputStream(nombre + ".dat");
            ObjectInputStream entrada = new ObjectInputStream(fis);
            Alfabeto codigo = (Alfabeto) entrada.readObject();
            fis.close();
            entrada.close();
            return codigo;

    }


}
