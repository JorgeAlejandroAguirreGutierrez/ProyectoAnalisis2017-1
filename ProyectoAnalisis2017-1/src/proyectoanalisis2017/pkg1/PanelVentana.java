/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoanalisis2017.pkg1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javax.swing.ImageIcon;

/**
 *
 * @author Gianka
 */
public class PanelVentana extends javax.swing.JPanel implements KeyListener {

    Ciudad ciudad;
    AreaItems areaItems;
    private Boolean estaSelecionadoComponente;
    private Item itemSeleccionado;
    private int xImgSelecionada;
    private int yImgSelecionada;
    private LinkedList<CarroMovimiento> carrosMovimiento;
    private GrafoDirigido grafo;
    private int opciones; //0: Para crear el mapa, 1: Para seleccionar el carro y nodo origen.
    AlgoritmosRuta rutaEspecifica;
    Ciudad auxCiudad = null;
    int idNodoOrigen;
    LinkedList<Integer> idDestinos;
    int auxi1 = 0;
    int auxj1 = 0;

    CarroMovimiento auxCarro = null;

    public PanelVentana() {
        initComponents();
        this.carrosMovimiento = new LinkedList<>();
        this.idDestinos = new LinkedList<>();
        this.grafo = new GrafoDirigido();
        this.xImgSelecionada = 0;
        this.yImgSelecionada = 0;
        this.itemSeleccionado = new Item();
        this.estaSelecionadoComponente = false;
        opciones = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        Componente auxUbicacion;
        //Si opciones es igual a 2 es porque el usuario va a realizar un camino.
        if (opciones == 2) {
            //Verificamos que el evento se de dentro del ancho y alto de la ciudad.
            if (evt.getX() < auxCiudad.getAnchoCiudad() && evt.getY() < auxCiudad.getAltoCiudad()) {
                //Recorro la lista de carros en movimiento para obtener el carro al cual se le dio click
                for (int i = 0; i < carrosMovimiento.size(); i++) {
                    if (carrosMovimiento.get(i).getArea().contains(new Point(evt.getX(), evt.getY()))) {
                        auxCarro = carrosMovimiento.get(i);
                        //Se pausa el carro.
                        auxCarro.pause();
                    }
                }
                auxi1 = (int) evt.getY() / ciudad.getAltoCampo();
                auxj1 = (int) evt.getX() / ciudad.getAnchoCampo();
                //Y tambien obtenemos la ubicacion del carro
                auxUbicacion = auxCiudad.getMatrizCiudad()[auxi1][auxj1];

                // Si la ubicación no es un nodo se debe marcar como nodo
                if (auxUbicacion != null && auxCarro != null) {
                    if (auxUbicacion.getIdNodo() == -1) {
                        auxCiudad.marcarNodo(auxUbicacion);
                    }
                    //Pasamos a opciones 3
                    opciones = 3;
                    //Obtenemos el nodo origen para un posterior camino
                    this.idNodoOrigen = auxUbicacion.getIdNodo();
                }
            }
            //Si opciones es igual a 3 es porque ya esta seleccionado el carro y el nodo origen
        } else if (opciones == 3) {
            //Verificamos que el evento esta dentro del ancho y alto de ciudad.
            if (evt.getX() < auxCiudad.getAnchoCiudad() && evt.getY() < auxCiudad.getAltoCiudad()) {
                {
                    int auxi = (int) evt.getY() / ciudad.getAltoCampo();
                    int auxj = (int) evt.getX() / ciudad.getAnchoCampo();
                    //Obtenemos la ubicacion
                    auxUbicacion = auxCiudad.getMatrizCiudad()[auxi][auxj];
                    if (auxUbicacion.getIdNodo() == -1) {
                        //La marcamos si es nodo.
                        auxCiudad.marcarNodo(auxUbicacion);
                    }
                    //Añado a los destino el nodo de destino
                    this.idDestinos.add(auxUbicacion.getIdNodo());
                }

            }
        }
    }//GEN-LAST:event_formMouseClicked
    /**
     * Evaluamos si el puntero del mouse esta en los limites del areaItem y si
     * presiona el click activamos la bandera estaSelecionadoComponente para
     * decir que ya estamos selecionado un item,seteamos el contador a cero para
     * que nos meustra la primera imagen de la lista de compoentes del item
     *
     * @param evt
     */
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed

        //Verifica si se presiono en alguno de los items del area de items
        if (evt.getX() > areaItems.getAnchoListaComponentesX1()) {
            for (int i = 0; i < areaItems.getListaItems().size(); i++) {
                if (areaItems.getListaItems().get(i).getArea().contains(new Point(evt.getX(), evt.getY()))) {
                    this.estaSelecionadoComponente = true;
                    areaItems.getListaItems().get(i).setContador(0);
                    this.itemSeleccionado = areaItems.getListaItems().get(i);
                }
            }
        }

    }//GEN-LAST:event_formMousePressed
    /**
     * Cuando se halla activado la bandera estaSelecionadoComponentes que es
     * cuando se preciona un item y ya cambiamos el X y Y de la imagen
     * selecionada para que la pinte de acuerdo donde va el puntero del mouse
     *
     * @param evt
     */
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if (this.estaSelecionadoComponente) {
            this.xImgSelecionada = evt.getX();
            this.yImgSelecionada = evt.getY();
            repaint();
        }
    }//GEN-LAST:event_formMouseDragged
    /**
     * Cuando se suelta el click y adeas se tiene selecionado un item lo que
     * hace el release es que desactiva la bandera y posedemos a ubicar el
     * componente que se seleciono y ubicarlo en la matriz de la Ciudad
     *
     * @param evt
     */
    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        if (this.estaSelecionadoComponente) {
            try {
                this.estaSelecionadoComponente = false;
                this.xImgSelecionada = 0;
                this.yImgSelecionada = 0;
                int auxN = evt.getY() / ciudad.getAltoCampo();
                int auxM = evt.getX() / ciudad.getAnchoCampo();
                Componente auxComponente = new Componente(itemSeleccionado.getLstComponentes().get(itemSeleccionado.getContador()).getNombre());
                Rectangle area = new Rectangle(evt.getX(), evt.getY(), ciudad.getAnchoCampo(), ciudad.getAltoCampo());
                auxComponente.setArea(area);
                //Si ciudad en la posicion i, j del evento es diferente de null y si es una vida y el componente tomado del area de items uno de las dos interrupciones
                if (ciudad.getMatrizCiudad()[auxN][auxM] != null && ciudad.esVia(ciudad.getMatrizCiudad()[auxN][auxM]) && (auxComponente.getNombre().equals("XX") || auxComponente.getNombre().equals("YY"))) {
                    //Se añade la interrupcion a la lista de interrupciones de la ciudad, con el componente anterior al de la interrupcion y la posicion en donde esta
                    ciudad.getListaInterrupciones().add(new Interrupcion((Componente) ciudad.getMatrizCiudad()[auxN][auxM].clone(), auxN, auxM));
                    //Se establece la interrupcion en la posicion i, j de la matriz de la ciudad
                    ciudad.getMatrizCiudad()[auxN][auxM] = auxComponente;
                    //Marca los nodos adyacentes a la interrupción
                    ciudad.marcarNodosAdyacentes(auxN, auxM);
                    //Se crea una ciudad local
                    Ciudad auxCiudad1 = copiarCiudad();
                    GrafoDirigido auxGrafo = new GrafoDirigido(auxCiudad1.getCantidadNodos());
                    //Se crea el grafo con la ciudad auxiliar
                    auxGrafo.crearGrafo(auxCiudad1.getMatrizCiudad(), auxCiudad1.getAnchoCampo(), auxCiudad1.getAltoCampo());
                    //Se asigna al objeto de clase grafo
                    grafo = auxGrafo;
                    for (int i = 0; i < carrosMovimiento.size(); i++) {
                        //Aqui si se mandara el grafo completo con solo ese clone????
                        carrosMovimiento.get(i).setGrafo((GrafoDirigido) grafo.clone());
                    }
                } 
                //Si la ciudad en la posicion i,j es diferente de null y el componente tomado del area de items es el que remueve interrupciones y donde se tomo el evento en la posicion i, j es alguna de las interrupciones
                else if (ciudad.getMatrizCiudad()[auxN][auxM] != null && auxComponente.getNombre().equals("XXX") && (ciudad.getMatrizCiudad()[auxN][auxM].getNombre().equals("XX") || ciudad.getMatrizCiudad()[auxN][auxM].equals("YY"))) {
                    //Recorro la lista de interrupciones para obtener la interrupciion
                    for (int i = 0; i < ciudad.getListaInterrupciones().size(); i++) {
                        //Si para cada interrupcion en i, j es igual a la posicion del evento
                        if (ciudad.getListaInterrupciones().get(i).getI() == auxN && ciudad.getListaInterrupciones().get(i).getJ() == auxM) {
                            //Le asigno el anterior componente a la posicion i, j de la matriz de la ciudad.
                            ciudad.getMatrizCiudad()[auxN][auxM] = ciudad.getListaInterrupciones().get(i).getComponenteAnterior();
                        }
                    }
                    //Procedo con la eliminacion de nodos adyacentes.
                    ciudad.eliminarNodosAdyacentes(auxN, auxM);
                    Ciudad auxCiudad1 = copiarCiudad();
                    GrafoDirigido auxGrafo = new GrafoDirigido(auxCiudad1.getCantidadNodos());
                    //Creo el grafo con la matriz de la ciudad
                    auxGrafo.crearGrafo(auxCiudad1.getMatrizCiudad(), auxCiudad1.getAnchoCampo(), auxCiudad1.getAltoCampo());
                    //Le asignamos a la variable de clase el grafo.
                    grafo = auxGrafo;
                    for (int i = 0; i < carrosMovimiento.size(); i++) {
                        //Le seteamos a todos los carro el nuevo grafo. (¿EL grafo si clona bien?)
                        carrosMovimiento.get(i).setGrafo((GrafoDirigido) grafo.clone());
                    }

                }
                //Si el componente no es una de las interrupciones ni el que borra las interrupciones es un componente para pintar en el mapa.
                else if (!auxComponente.getNombre().equals("XX") && !auxComponente.getNombre().equals("YY") && !auxComponente.getNombre().equals("XXX")) {
                    ciudad.getMatrizCiudad()[auxN][auxM] = auxComponente;
                }

            } catch (Exception e) {

            }
            repaint();
        }
    }//GEN-LAST:event_formMouseReleased

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseEntered

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_formKeyPressed
    public Ciudad copiarCiudad() {
        Ciudad copiaCiudad = (Ciudad) ciudad.clone();
        copiaCiudad.setMatrizCiudad(null);
        Componente[][] compiaComponentes = new Componente[copiaCiudad.getN()][copiaCiudad.getM()];
        copiaCiudad.setMatrizCiudad(compiaComponentes);
        for (int i = 0; i < ciudad.getN(); i++) {
            for (int j = 0; j < ciudad.getM(); j++) {
                if (ciudad.getMatrizCiudad()[i][j] != null) {
                    copiaCiudad.getMatrizCiudad()[i][j] = (Componente) ciudad.getMatrizCiudad()[i][j].clone();
                }
            }
        }
        return copiaCiudad;
    }

    public void ModificarGrafo() {

//        GrafoDirigido auxGrafo = new GrafoDirigido(auxCiudad.getCantidadNodos());
//        auxGrafo.crearGrafo(auxCiudad.getMatrizCiudad(), auxCiudad.getAnchoCampo(), auxCiudad.getAltoCampo());
//        auxCarro.setGrafo(auxGrafo);
//        RutaCorta auxRuta = new RutaCorta(auxCiudad.getCantidadNodos());
//        auxRuta.llenarPesos(auxGrafo);
//        auxRuta.setOrigen(this.idNodoOrigen);
//        auxRuta.caminosMinimos();
//        auxCarro.setTipo(0);
//        auxCarro.getArea().setLocation(auxj1 * auxCiudad.getAnchoCampo(), auxi1 * auxCiudad.getAltoCampo());
//        LinkedList<Arista> auxCamino = new LinkedList<>();
//        for (int i = 0; i < idDestinos.size(); i++) {
//            LinkedList<Arista> auxCamino1 = auxRuta.convertirCamino(auxGrafo, idDestinos.get(i));
//            for (int j = 0; j < auxCamino1.size(); j++) {
//                auxCamino.add(auxCamino1.get(j));
//            }
//            auxRuta.setOrigen(idDestinos.get(i));
//            auxRuta.caminosMinimos();
//        }
//        auxCarro.setCamino(auxCamino);
//        auxCarro.start();
        GrafoDirigido auxGrafo = new GrafoDirigido(auxCiudad.getCantidadNodos());
        auxGrafo.crearGrafo(auxCiudad.getMatrizCiudad(), auxCiudad.getAnchoCampo(), auxCiudad.getAltoCampo());
        auxCarro.setGrafo(auxGrafo);
        RutaCorta auxRuta = new RutaCorta(auxCiudad.getCantidadNodos());
        System.out.println(auxRuta.getCantidadNodos());
        auxRuta.llenarPesos(auxGrafo);
        int matrizVertices[][] = auxRuta.floydWarshall();
        auxCarro.setTipo(0);
        auxCarro.getArea().setLocation(auxj1 * auxCiudad.getAnchoCampo(), auxi1 * auxCiudad.getAltoCampo());
        int origen = this.idNodoOrigen;
        LinkedList<Arista> auxCamino = new LinkedList<>();
        for (int i = 0; i < idDestinos.size(); i++) {
            LinkedList<Arista> auxCamino1 = auxRuta.obtenerCaminoFloydWarshall(matrizVertices, origen, idDestinos.get(i), auxGrafo);
            for (int j = 0; j < auxCamino1.size(); j++) {
                auxCamino.add(auxCamino1.get(j));
            }
            origen = idDestinos.get(i);
        }
        auxCarro.setCamino(auxCamino);
        auxCarro.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        if (ciudad != null && areaItems != null) {
            g.drawImage(new ImageIcon(getClass().getResource("../ImgComponentes/Fondo.jpg")).getImage(), 0, 0, ciudad.getAnchoCiudad(), ciudad.getAltoCiudad(), this);
            g.setColor(Color.decode("#FC4600"));
            g.fillRect(areaItems.getAnchoListaComponentesX1(), 0, (areaItems.getAnchoListaComponentesX2() - ciudad.getAnchoCiudad()) * 2, ciudad.getAltoCiudad());
            g.setColor(Color.BLACK);
            pintarComponentes(g);
            // lineas de referencia de las areas de la aplicacion
            g.drawRect(0, 0, ciudad.getAnchoCiudad(), ciudad.getAltoCiudad());
            pintarCiudad(g);
            //pitnar carros automaticos
            for (int i = 0; i < this.carrosMovimiento.size(); i++) {
                g.drawImage(new ImageIcon(getClass().getResource(this.carrosMovimiento.get(i).getRuta())).getImage(), (int) this.carrosMovimiento.get(i).getArea().getX(), (int) this.carrosMovimiento.get(i).getArea().getY(), ciudad.getAnchoCampo(), ciudad.getAltoCampo(), this);

            }
            //pinta la anamiacion de colocar imagen en el tablero
            //El 0 es el X1 de la ciudad.
            if (this.estaSelecionadoComponente && this.xImgSelecionada > 0 && this.xImgSelecionada < ciudad.getAnchoCiudad() && this.yImgSelecionada > 0 && this.yImgSelecionada < ciudad.getAltoCiudad()) {
                g.drawImage(new ImageIcon(getClass().getResource(itemSeleccionado.getLstComponentes().get(itemSeleccionado.getContador()).getRuta())).getImage(), this.xImgSelecionada, this.yImgSelecionada, 100, 100, this);
                int auxN = yImgSelecionada / ciudad.getAltoCampo();
                int auxM = xImgSelecionada / ciudad.getAnchoCampo();
                g.drawRect(auxM * ciudad.getAnchoCampo(), auxN * ciudad.getAltoCampo(), ciudad.getAnchoCampo(), ciudad.getAltoCampo());
            }
        }

    }

    /**
     * pintamos la lista de los item que podemos selecionar y poner en la ciudad
     * @param g grafico del panel que sirve como lienzo
     */
    private void pintarComponentes(Graphics g) {
        for (int i = 0; i < areaItems.getListaItems().size(); i++) {
            Item auxItem;
            auxItem = areaItems.getListaItems().get(i);
            g.drawImage(new ImageIcon(getClass().getResource(auxItem.getLstComponentes().getFirst().getRuta())).getImage(), auxItem.getArea().x, auxItem.getArea().y, auxItem.getArea().width, auxItem.getArea().height, this);
        }
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public void setAreaItems(AreaItems areaItems) {
        this.areaItems = areaItems;
    }

    /**
     * Permite en recorrer la matriz de componentes y pintar
     * las difrentes imagenes asociadas ala posicion de la matriz en caso de que
     * sea null es que no hay ningun componente entonces no se pinta nada
     *
     * @param g grafico del panel que sirve como lienzo
     */
    private void pintarCiudad(Graphics g) {
        for (int i = 0; i < this.ciudad.getN(); i++) {
            for (int j = 0; j < this.ciudad.getM(); j++) {
                if (ciudad.getMatrizCiudad()[i][j] != null && !ciudad.getMatrizCiudad()[i][j].getNombre().equals("0")) {
                    g.drawImage(new ImageIcon(getClass().getResource(ciudad.getMatrizCiudad()[i][j].getRuta())).getImage(), ciudad.getAnchoCampo() * j, ciudad.getAltoCampo() * i, ciudad.getAnchoCampo(), this.ciudad.getAltoCampo(), this);
                }
            }
        }
    }

    /**
     * Permite añadir un carro a la lista de carros en movimiento y asignarle un grafo.
     * @param carroAuto 
     */
    public void ingresarCarro(CarroMovimiento carroAuto) {
        this.carrosMovimiento.add(carroAuto);
        this.carrosMovimiento.getLast().setPanel(this);
        GrafoDirigido grafoAux = (GrafoDirigido) this.grafo.clone();
        this.carrosMovimiento.getLast().setGrafo(grafoAux);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    /**
     * detectamos que si se preciona alguna de las teclas aumentamos el contador del
     * item para que cambie el componente y lo logre ubicar en la matriz de la
     * ciudad
     * @param ke
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        if (itemSeleccionado.getContador() == itemSeleccionado.getLstComponentes().size() - 1) {
            this.itemSeleccionado.setContador(0);
        } else {
            itemSeleccionado.setContador(itemSeleccionado.getContador() + 1);
        }
        repaint();
    }

    public LinkedList<CarroMovimiento> getCarrosMovimiento() {
        return carrosMovimiento;
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    public void setGrafo(GrafoDirigido grafo) {
        this.grafo = grafo;
    }

    public int getOpciones() {
        return opciones;
    }

    public void setOpciones(int opciones) {
        this.opciones = opciones;
    }

    public void setAuxCiudad(Ciudad auxCiudad) {
        this.auxCiudad = auxCiudad;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
