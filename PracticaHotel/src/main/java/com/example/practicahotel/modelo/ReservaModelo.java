package com.example.practicahotel.modelo;

import com.example.practicahotel.modelo.repository.ReservaRepository;
import com.example.practicahotel.modelo.repository.impl.ConexionJDBC;
import com.example.practicahotel.util.ReservaUtil;
import com.example.practicahotel.view.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class ReservaModelo {
    private ReservaRepository reservaRepository;
    private ConexionJDBC conexion = new ConexionJDBC();

    // Setter para la dependencia ReservaRepository
    public void setReservaRepository(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    /**
     * Obtiene el total de habitaciones disponibles para un tipo de habitación específico.
     *
     * @param tipoHabitacion Tipo de habitación.
     * @return Total de habitaciones disponibles.
     */
    public int getHabitacionesTotales(String tipoHabitacion) {
        return reservaRepository.obtenerTotalHabitaciones(tipoHabitacion);
    }

    /**
     * Obtiene el número de habitaciones ocupadas para un tipo de habitación específico y una fecha actual.
     *
     * @param tipoHabitacion Tipo de habitación.
     * @param fechaActual    Fecha actual.
     * @return Número de habitaciones ocupadas.
     */
    public int getHabitacionesOcupadas(String tipoHabitacion, LocalDate fechaActual) {
        return reservaRepository.obtenerHabitacionesOcupadas(tipoHabitacion, fechaActual);
    }

    /**
     * Obtiene una lista de reservas asociadas a un cliente.
     *
     * @param idCliente ID del cliente.
     * @return Lista de reservas del cliente.
     * @throws ExcepcionHotel En caso de error al obtener las reservas.
     */
    public ObservableList<Reserva> setReservas(String idCliente) throws ExcepcionHotel {
        ObservableList<ReservaVO> listaReservasVO = this.reservaRepository.RelacionClienteReservas(idCliente);
        return ReservaUtil.parseReservaVOReserva(listaReservasVO);
    }

    /**
     * Añade una nueva reserva.
     *
     * @param reserva Reserva a añadir.
     * @throws ExcepcionHotel En caso de error al añadir la reserva.
     */
    public void añadirReserva(Reserva reserva) throws ExcepcionHotel {
        reservaRepository.añadirReserva(ReservaUtil.parseReservaReservaVO(reserva));
    }

    /**
     * Edita una reserva existente.
     *
     * @param reserva Reserva a editar.
     * @throws ExcepcionHotel En caso de error al editar la reserva.
     */
    public void editarReserva(Reserva reserva) throws ExcepcionHotel {
        reservaRepository.editarReserva(ReservaUtil.parseReservaReservaVO(reserva));
    }

    /**
     * Borra una reserva existente.
     *
     * @param reserva Reserva a borrar.
     * @throws ExcepcionHotel En caso de error al borrar la reserva.
     */
    public void borrarReserva(Reserva reserva) throws ExcepcionHotel {
        reservaRepository.borrarReserva(reserva.getId_Reserva());
    }
}
