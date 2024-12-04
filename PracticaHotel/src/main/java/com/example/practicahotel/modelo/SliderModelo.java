package com.example.practicahotel.modelo;

import java.util.ArrayList;
import javafx.scene.image.Image;

import java.util.List;

public class SliderModelo {

    private final List<Image> doble_individual;
    private final List<Image> doble;
    private final List<Image> suite_junior;
    private final List<Image> suite;

    public SliderModelo() {
        doble_individual = new ArrayList<>();
            cargarImagenes();

        doble = new ArrayList<>();
            cargarImagenes();

        suite_junior = new ArrayList<>();
            cargarImagenes();

        suite = new ArrayList<>();
            cargarImagenes();
        }

        private void cargarImagenes() {
            doble_individual.add(new Image("file:src/main/resources/images/img1.jpg"));
            doble_individual.add(new Image("file:src/main/resources/images/img2.jpg"));
            doble_individual.add(new Image("file:src/main/resources/images/img3.jpg"));
            doble_individual.add(new Image("file:src/main/resources/images/img4.jpg"));

            doble.add(new Image("file:src/main/resources/images/img1.jpg"));
            doble.add(new Image("file:src/main/resources/images/img2.jpg"));
            doble.add(new Image("file:src/main/resources/images/img3.jpg"));
            doble.add(new Image("file:src/main/resources/images/img4.jpg"));

            suite_junior.add(new Image("file:src/main/resources/images/img1.jpg"));
            suite_junior.add(new Image("file:src/main/resources/images/img2.jpg"));
            suite_junior.add(new Image("file:src/main/resources/images/img3.jpg"));
            suite_junior.add(new Image("file:src/main/resources/images/img4.jpg"));

            suite.add(new Image("file:src/main/resources/images/img1.jpg"));
            suite.add(new Image("file:src/main/resources/images/img2.jpg"));
            suite.add(new Image("file:src/main/resources/images/img3.jpg"));
            suite.add(new Image("file:src/main/resources/images/img4.jpg"));

        }

        public List<Image> getDoble_individual() {
            return doble_individual;
        }
        public List<Image> getDoble() {
        return doble;
    }
        public List<Image> getSuite_junior() {
        return suite_junior;
    }
        public List<Image> getSuite() {
        return suite;
    }

}


