
package com.myApp.cliente_app.services;

import com.myApp.cliente_app.model.Cliente;
import com.myApp.cliente_app.repository.ClienteRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImplementacion implements ClienteService{
    
    @Autowired
    private ClienteRepository clienteRepository;  

    @Override
    public Cliente newCliente(Cliente cliente) {
        // Verificar si el email ya está registrado
        Optional<Cliente> clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
        if (clienteExistente.isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        return clienteRepository.save(cliente);
}

    @Override
    public Iterable<Cliente> getAll() {
        return this.clienteRepository.findAll();
    }

    @Override
    public Cliente modifyCliente(Cliente cliente) {
    // Buscar el cliente por su id
        Optional<Cliente> clienteEncontrado = this.clienteRepository.findById(cliente.getIdCliente());
    
    // Si el cliente existe (Optional.isPresent()), actualizamos sus datos
    if (clienteEncontrado.get()!=null) {
       clienteEncontrado.get().setNombre(cliente.getNombre());
       clienteEncontrado.get().setApellido(cliente.getApellido());
       clienteEncontrado.get().setEmail(cliente.getEmail());
        // Guardar el cliente actualizado en el repositorio
        return this.newCliente(clienteEncontrado.get());
        }
        return null;
    }

    @Override
    public Boolean deleteCliente(Long idcliente) {
        Optional<Cliente> clienteOptional = this.clienteRepository.findById(idcliente);
    if (clienteOptional.isPresent()) {
        this.clienteRepository.deleteById(idcliente);
        return true; // Cliente eliminado
    }
    return false; // Cliente no encontrado
}

    public boolean emailExiste(String email) {
        throw new UnsupportedOperationException("El email ya está registrado"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean emailExists(String email) {
        throw new UnsupportedOperationException("El email ya está registrado."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}