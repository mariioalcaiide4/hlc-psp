package com.example.practicahotel.util;

import com.example.practicahotel.modelo.ReservaVO;
import com.example.practicahotel.view.Cliente;
import com.example.practicahotel.view.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;



public class ReservaUtil {
    
    static ObservableList<Reserva> listaReservas = FXCollections.observableArrayList();

    {
    };
    
    public static ReservaVO parseReservaReservaVO (Reserva reserva){
        if (reserva == null) {
            return null;
        }else {
            return new ReservaVO(reserva.getId_Reserva(), reserva.getFecha_Entrada(), reserva.getFecha_Salida(), reserva.getNumHabitaciones(), reserva.getTipoHabitacion(), reserva.isFumador(), reserva.getRegimen(), reserva.getId_Cliente());
        }
    }

    public static ObservableList<Reserva> parseReservaVOReserva(ObservableList<ReservaVO> listaReservaVO) {
        if (listaReservaVO == null) {
            return null;
        } else {
            for (ReservaVO reservaVO : listaReservaVO) {
                listaReservas.add(new Reserva(reservaVO.getId_reserva(), reservaVO.getFecha_entrada(), reservaVO.getFecha_salida(), reservaVO.getNum_habitaciones(), reservaVO.getTipo_habitacion(), reservaVO.isFumador(), reservaVO.getRegimen(), reservaVO.getId_cliente()));
            }
            return listaReservas;
        }
    }

}


