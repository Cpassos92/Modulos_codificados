
package com.myApp.cliente_app.controller;

import com.myApp.cliente_app.model.Cliente;
import com.myApp.cliente_app.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    // Crear un nuevo cliente
    @CrossOrigin("http://127.0.0.1:5500")
    @PostMapping("/nuevo")
    public ResponseEntity<?>  newCliente(@RequestBody Cliente newCliente){
        try {
        Cliente clienteGuardado = clienteService.newCliente(newCliente);
        return ResponseEntity.ok(clienteGuardado);  // Devuelve 200 OK si el cliente fue creado exitosamente
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage() );  // Devuelve 400 con el mensaje de error
    }
    }
    
    
    //obtener todos loas clientes
     @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/mostrarCliente")
    public Iterable<Cliente> getAll(){
        return clienteService.getAll();
    }
    
    //modificar un cliente
     @CrossOrigin("http://127.0.0.1:5500")
    @PostMapping ("/modificar")
    public Cliente UpdateCliente(@RequestBody Cliente cliente){
        return this.clienteService.modifyCliente(cliente);
    }
    
    
    //eliminar un cliente
    @CrossOrigin("http://127.0.0.1:5500")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable(value = "id") Long id) {
    try {
        boolean deleted = this.clienteService.deleteCliente(id);
        if (deleted) {
            return ResponseEntity.ok().build(); // 200 OK
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found si no se encontró el cliente
        }
    } catch (Exception e) {
        return ResponseEntity.badRequest().build(); // 400 Bad Request en caso de error
    }
}
}
