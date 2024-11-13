package com.example.practicahotel.modelo;

import com.example.practicahotel.modelo.repository.ClienteRepository;
import com.example.practicahotel.util.ClienteUtil;
import java.util.ArrayList;
import com.example.practicahotel.view.Cliente;

public class ClienteModelo {
    ClienteRepository clienteRepository;
    public void setClienteRepository(ClienteRepository clienteRepository) {this.clienteRepository = clienteRepository;}

    public ArrayList<Cliente> obtenerClientes() throws ExcepcionHotel{
        ArrayList<ClienteVO> listillaClientes = clienteRepository.ObtenerListaClientes();
        return ClienteUtil.parsePersonVOPerson(listillaClientes);
    }

    public void anadirCliente(Cliente cliente) throws ExcepcionHotel {
        clienteRepository.añadirCliente(ClienteUtil.parsePersonPersonVO(cliente));
    }

    public void editarCliente(Cliente cliente) throws ExcepcionHotel {
        clienteRepository.editarCliente(ClienteUtil.parsePersonPersonVO(cliente));

    }

    public void borrarCliente(String dni) throws ExcepcionHotel {
        clienteRepository.borrarCliente(dni);


    }

}
