package test;

import junit.framework.TestCase;
import model.Store;

public class StoreTest extends TestCase {

    private Store store;

    private void SetUpStage6(){
        store = new Store();
    }

    private void setUpStage5(){
        SetUpStage6();

        store.addProducts("Bolso Louis Vuitton",
                "Bolso de alta gama con cuero suizo",
                250000,
                250,
                3,
                25);
        store.addProducts("HP Pavillion mini 2012",
                "computadora mini perfecta para llevar en el bolsillo con características técnicas que por su tamaño no aparenta, en perfecto estado se vende por motivo de viaje NO NEGOCIABLE",
                1500000,
                1,
                2,
                0);
        store.addProducts("Grafica AMD RX6800XT",
                "tarjetas gráficas baratas pues se usaron para mineria y ya no les puedo dar uso precio negociable",
                2000000,
                600,
                2,
                30);
        store.addProducts("Vajilla IMUSA Cerámica",
                "Hermosa vajilla de excelentisima calidad",
                40000,
                50000,
                3,
                600);
        store.addProducts("Termo de agua 2L",
                "Termo de agua en diferentes colores y aun excelente precio. Somos una tienda de verificada",
                40000,
                9999,
                3,
                30);
        store.addProducts("Termo de agua 2L",
                "Termo de agua de excelente calidad somos una importadora seria así que mejor  abstenerse de preguntar cosas innecesarias ",
                20000,
                25,
                3,
                2);
        store.addProducts("Tarjeta de sonido profesional",
                "Tarjeta de sonido game blaster platinum para escuchar tus  juegos favoritos  a otro nivel",
                175000,
                25000,
                2,
                3600);
        store.addProducts("Silla de escritorio ergonomica",
                "Silla de oficina marca lo mejor para tu espalda que ayudará a tu columna a liberar toda esa presión acumulada por el trabajo",
                360000,
                5000,
                3,
                350);
        store.addProducts("Anillo de plata tematica juego Zelda",
                "Anillo de excelente calidad con diferentes logotipos de la temática del videojuego de Zelda",
                360000,
                5000,
                3,
                350);

    }

    private void setUpStage4(){
        SetUpStage6();
        setUpStage5();



    }



}
