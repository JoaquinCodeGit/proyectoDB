package com.joaquin.apirest.apirest.Controllers;


import com.joaquin.apirest.apirest.Entities.Producto;
import com.joaquin.apirest.apirest.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id){
        return productoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No se encontro el produco con el id:" + id));
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto detallesProducto) {
       Producto producto=  productoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No se encontro el produco con el id:" + id));
        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());
        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id){
        Producto producto=  productoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No se encontro el produco con el id:" + id));
        productoRepository.delete(producto);
        return "El producto con el id: " + id + " fue eliminado";
    }

}
