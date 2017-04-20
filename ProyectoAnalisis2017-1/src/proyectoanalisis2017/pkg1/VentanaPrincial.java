/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoanalisis2017.pkg1;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Gianka
 */
public class VentanaPrincial extends javax.swing.JFrame implements Serializable {

    Ciudad ciudad;
    AreaItems areaItems;
    GraphicsDevice grafica;
    GrafoDirigido grafo;
    int cantidadCarros;
    int opciones;

    public VentanaPrincial() {
        initComponents();
        opciones=0;
        this.cantidadCarros = 0;
        grafica = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        grafica.setFullScreenWindow(this);
        pnlVentana1.setFocusable(true);
        pnlVentana1.addKeyListener(pnlVentana1);
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingese \n 1 para cargar \n 2 para crear nueva ciudad", "Menu", JOptionPane.INFORMATION_MESSAGE));
        if (opcion == 1) {
            cargarCiudad();
        } else {
            crearCiudad();
        }

        redimensionar();
        setResizable(false);
        setVisible(true);
    }

    private void redimensionar() {
        int anchoCampo = (int) ((this.getWidth() * 0.8) / ciudad.getM());
        int altoCampo = (int) ((this.getHeight() - 100) / ciudad.getN());
        ciudad.setAnchoCampo(anchoCampo);
        ciudad.setAltoCampo(altoCampo);
        ciudad.setAnchoCiudad(ciudad.getM() * ciudad.getAnchoCampo());
        ciudad.setAltoCiudad(ciudad.getAltoCampo() * ciudad.getN());
        crearAreaItem();
        pnlVentana1.setAreaItems(areaItems);
        pnlVentana1.setCiudad(ciudad);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlVentana1 = new proyectoanalisis2017.pkg1.PanelVentana();
        btnGuardar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlVentana1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pnlVentana1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pnlVentana1KeyReleased(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jButton1.setText("Crear Grafo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ver Grafo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setText("IngresarC");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Iniciar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton3.setText("Dijkstra");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jToggleButton1.setText("Camino");
        jToggleButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jToggleButton1StateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlVentana1Layout = new javax.swing.GroupLayout(pnlVentana1);
        pnlVentana1.setLayout(pnlVentana1Layout);
        pnlVentana1Layout.setHorizontalGroup(
            pnlVentana1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVentana1Layout.createSequentialGroup()
                .addGap(52, 778, Short.MAX_VALUE)
                .addGroup(pnlVentana1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVentana1Layout.createSequentialGroup()
                        .addGroup(pnlVentana1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVentana1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlVentana1Layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(jButton2))
                                .addComponent(jButton3))
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        pnlVentana1Layout.setVerticalGroup(
            pnlVentana1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVentana1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton1)
                .addContainerGap(375, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlVentana1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlVentana1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
//        mostrarMatrizCiudad();
        String nombreArchivo = JOptionPane.showInputDialog("ingrese nombre archivo");
        GuardarCiudad(nombreArchivo);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void pnlVentana1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnlVentana1KeyReleased
        // TODO add your handling code here:sd
    }//GEN-LAST:event_pnlVentana1KeyReleased

    private void pnlVentana1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnlVentana1KeyPressed
        // TODO add your handling code   here:
    }//GEN-LAST:event_pnlVentana1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        crearGrafo();
        pnlVentana1.setGrafo(grafo);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        grafo.verGrafo();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        LinkedList<Arista> camino1 = new LinkedList<>();
        boolean bandera = false;
        for (int i = 0; i < grafo.getGrafo().length && !bandera; i++) {
            for (int j = 0; j < grafo.getGrafo().length && !bandera; j++) {
                if (grafo.getGrafo()[i][j] != null) {
                    camino1.add(grafo.getGrafo()[i][j]);
                    bandera = true;
                }
            }

        }
        pnlVentana1.ingresarCarro(new CarroMovimiento(this.cantidadCarros, camino1.getFirst().getX1(), camino1.getFirst().getY1(), camino1, 1));
        this.cantidadCarros++;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        pnlVentana1.getCarrosMovimiento().getLast().start();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //int origen=Integer.parseInt(JOptionPane.showInputDialog(this,"Ingrese el nodo origen", "Informacion", JOptionPane.INFORMATION_MESSAGE));
        //int destino=Integer.parseInt(JOptionPane.showInputDialog(this,"Ingrese el nodo destino", "Informacion", JOptionPane.INFORMATION_MESSAGE));
        int idcarro = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el ID del carro", "Informacion", JOptionPane.INFORMATION_MESSAGE));
        if (idcarro < cantidadCarros) {
            int cantidadNodos = ciudad.getCantidadNodos();
            RutaCorta d = new RutaCorta(idcarro, cantidadNodos);
            d.llenarPesos(grafo);
            d.caminosMinimos();
            LinkedList<Integer> caminoNodos = d.obtenerCamino(idcarro);
            System.out.println("Mostrar Camino");
            for (int i = 0; i < caminoNodos.size(); i++) {
                System.out.println(caminoNodos.get(i));
            }
            LinkedList<Arista> camino = d.convertirCamino(grafo, caminoNodos);
        }
        else
        {
            
        }

        //pnlVentana1.ingresarCarro(new CarroMovimiento(this.cantidadCarros, camino.getFirst().getX1(), camino.getFirst().getY1(), camino, 0));
        //this.cantidadCarros++;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jToggleButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jToggleButton1StateChanged
        opciones=1;
    }//GEN-LAST:event_jToggleButton1StateChanged

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
            java.util.logging.Logger.getLogger(VentanaPrincial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaPrincial frame = new VentanaPrincial();

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JToggleButton jToggleButton1;
    private proyectoanalisis2017.pkg1.PanelVentana pnlVentana1;
    // End of variables declaration//GEN-END:variables

    private void GuardarCiudad(String nombreArchivo) {
        String linea;
        FileWriter fichero = null;
        PrintWriter pw;
        try {
            fichero = new FileWriter(nombreArchivo + ".txt");
            pw = new PrintWriter(fichero);
            linea = String.valueOf(ciudad.getMatrizCiudad().length);
            pw.println(linea);
            linea = String.valueOf(ciudad.getMatrizCiudad()[0].length);
            pw.println(linea);
            linea = "";
            for (int i = 0; i < ciudad.getMatrizCiudad().length; i++) {
                for (int j = 0; j < ciudad.getMatrizCiudad().length; j++) {
                    if (ciudad.getMatrizCiudad()[i][j] == null) {
                        linea = linea + "-,";
                    } else {
                        linea = linea + ciudad.getMatrizCiudad()[i][j].getNombre() + ",";
                    }
                }
                linea = linea.substring(0, linea.length() - 1);
                pw.println(linea);
                linea = "";
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (IOException e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    private void cargarCiudad() {
        String nombreArchivo = JOptionPane.showInputDialog("ingrese nombre archivo");
        File archivo;
        FileReader fr = null;
        BufferedReader br;
        int filas = 0;
        try {
            archivo = new File(nombreArchivo + ".txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());
            Componente matrizCiudad[][] = new Componente[n][m];
            String linea;
            while ((linea = br.readLine()) != null) {
                String columnas[] = linea.split(",");
                for (int i = 0; i < columnas.length; i++) {
                    if ("-".equals(columnas[i])) {
                        matrizCiudad[filas][i] = null;
                    } else {
                        matrizCiudad[filas][i] = new Componente(columnas[i]);
                    }
                }
                filas++;
            }
            ciudad = new Ciudad(matrizCiudad, n, m);
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (IOException e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    private void crearCiudad() {
        int n = Integer.parseInt(JOptionPane.showInputDialog("Ingrese n"));
        int m = Integer.parseInt(JOptionPane.showInputDialog("Ingrese m"));
        Componente matriz[][] = new Componente[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matriz[i][j] = null;
            }
        }
        ciudad = new Ciudad(matriz, n, m);
    }

    private void crearAreaItem() {
        int anchoAreaItemsX1 = ciudad.getN() * ciudad.getAnchoCampo();
        int anchoAreaItemsX2 = this.getWidth() - (int) (this.getWidth() * 0.05);
        areaItems = new AreaItems(new ArrayList<>(), anchoAreaItemsX1, anchoAreaItemsX2);
    }

    private void crearGrafo() {
        ciudad.actualizarCiudad();
        grafo = new GrafoDirigido(ciudad.getCantidadNodos());
        Componente[][] matrizCopia = new Componente[ciudad.getN()][ciudad.getM()];
        for (int i = 0; i < ciudad.getN(); i++) {
            for (int j = 0; j < ciudad.getM(); j++) {
                matrizCopia[i][j] = ciudad.getMatrizCiudad()[i][j];
            }
        }
        grafo.crearGrafo(matrizCopia, ciudad.getAnchoCampo(), ciudad.getAltoCampo());
    }

    private void mostrarMatrizCiudad() {
        for (int i = 0; i < ciudad.getN(); i++) {
            for (int j = 0; j < ciudad.getM(); j++) {
                if (ciudad.getMatrizCiudad()[i][j] != null) {
                    System.out.println(ciudad.getMatrizCiudad()[i][j].getIdNodo());
                }
            }
        }
    }
}
