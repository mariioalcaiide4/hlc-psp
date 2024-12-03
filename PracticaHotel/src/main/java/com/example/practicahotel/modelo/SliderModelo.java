package com.example.practicahotel.modelo;

public class SliderModelo {

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class SliderModelo {
    private final List<Image> imagenes;

    public SliderModelo() {
        imagenes = new ArrayList<>();
            cargarImagenes();
        }

        private void cargarImagenes() {
            imagenes.add(new Image("file:src/main/resources/images/img1.jpg"));
            imagenes.add(new Image("file:src/main/resources/images/img2.jpg"));
            imagenes.add(new Image("file:src/main/resources/images/img3.jpg"));
            imagenes.add(new Image("file:src/main/resources/images/img4.jpg"));
            imagenes.add(new Image("file:src/main/resources/images/img5.jpg"));
        }

        public List<Image> getImagenes() {
            return imagenes;
        }
    }

}
