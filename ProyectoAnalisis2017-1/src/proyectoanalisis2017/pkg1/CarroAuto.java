/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoanalisis2017.pkg1;

import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class CarroAuto extends Carro implements Runnable {

    private PanelVentana panel;
    private Thread hilo;
    private GrafoDirigido grafo;

    public CarroAuto(int id, int x, int y, LinkedList<Arista> camino) {
        super(id, x, y, camino);

    }

    public void start() {
        this.hilo = new Thread(this);
        this.hilo.start();
    }

    public CarroAuto() {
    }

    public void setGrafo(GrafoDirigido grafo) {
        this.grafo = grafo;
    }

    public void setPanel(PanelVentana panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        int velocidad = 0;
        while (true) {
            while (!getCamino().isEmpty()) {
                velocidad = getCamino().getFirst().getVelocidad();
                Boolean sentido = false;
                if (getCamino().getFirst().getX1() == getCamino().getFirst().getX2()) {
                    int auxY = 0;
                    if (getY() == getCamino().getFirst().getY1()) {
                        auxY = getCamino().getFirst().getY2();
                    } else {
                        auxY = getCamino().getFirst().getY1();
                    }
                    if (getY() < auxY) {
                        sentido = true;//incrementar
                    } else {
                        sentido = false;
                    }
                    if (sentido) {
                        while (getY() < auxY) {
                            try {
                                setY(getY() + 10);
                                Thread.sleep(velocidad);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ControlCarros.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            panel.repaint();
                        }
                        setY(auxY);
                        panel.repaint();
                    } else {
                        while (getY() > auxY) {
                            try {
                                setY(getY() - 10);
                                Thread.sleep(velocidad);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ControlCarros.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            panel.repaint();
                        }
                        setY(auxY);
                        panel.repaint();
                    }
                } else {
                    int auxX = 0;

                    if (getX() == getCamino().getFirst().getX1()) {
                        auxX = getCamino().getFirst().getX2();
                    } else {
                        auxX = getCamino().getFirst().getX1();
                    }
                    if (getX() < auxX) {
                        sentido = true;// invrementar
                    } else {
                        sentido = false;
                    }
                    if (sentido) {
                        while (getX() < auxX) {
                            try {
                                setX(getX() + 10);

                                Thread.sleep(velocidad);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ControlCarros.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            panel.repaint();
                        }
                        setX(auxX);
                        panel.repaint();
                    } else {
                        while (getX() > auxX) {
                            try {
                                setX(getX() - 10);
                                Thread.sleep(velocidad);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ControlCarros.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            panel.repaint();
                        }
                        setX(auxX);
                        panel.repaint();
                    }
                }
                int n = getCamino().getFirst().getX();
                int m = getCamino().getFirst().getY();
                getCamino().removeFirst();
                buscarCamino(n, m);
            }
        }
    }

    private void buscarCamino(int n, int m) {
        LinkedList<Arista> posiblesCaminos = new LinkedList<>();
        for (int i = 0; i < this.grafo.getGrafo()[m].length; i++) {
            if (this.grafo.getGrafo()[m][i] != null) {
                posiblesCaminos.add(this.grafo.getGrafo()[m][i]);
            }
        }

        int numero = posiblesCaminos.size();
        Random rnd = new Random();
        int num = (int) (rnd.nextDouble() * numero + 0);
        this.getCamino().add(posiblesCaminos.get(num));

    }

}
