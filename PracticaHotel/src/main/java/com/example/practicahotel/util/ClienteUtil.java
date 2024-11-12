package com.example.practicahotel.util;

import com.example.practicahotel.view.Cliente;
import com.example.practicahotel.modelo.ClienteVO;

import java.util.ArrayList;

//Clase para cambiar la clase ClienteVO a Cliente y viceversa

public class ClienteUtil {

    public static ClienteVO parsePersonPersonVO (Cliente cliente){
        if (cliente == null) {
            return null;
        }else {
            return new ClienteVO(cliente.getDni(), cliente.getNombre(), cliente.getApellido(), cliente.getDireccion(), cliente.getLocalidad(), cliente.getProvincia());
        }
    }

    public static ArrayList<Cliente> parsePersonVOPerson(ArrayList<ClienteVO> listaClienteVO) {
        if (listaClienteVO == null) {
            return null;
        }else {
            ArrayList<Cliente> listaClientes = new ArrayList<>();
            for (ClienteVO clienteVO : listaClienteVO) {
                listaClientes.add(new Cliente(clienteVO.getDni(), clienteVO.getNombre(), clienteVO.getApellido(), clienteVO.getDireccion(), clienteVO.getLocalidad(), clienteVO.getProvincia()));
            }
            return listaClientes;
        }
    }
}
