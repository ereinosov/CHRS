package org.uteq.backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin (origins = "http://localhost:4200")
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "Conexión establecida correctamente";
    }

    @GetMapping("/saludo/{nombre}")
    public String saludo(@PathVariable String nombre) {
        return "Hola " + nombre;
    }

    @PostMapping("/mensaje")
    public String mensaje(@RequestBody Mensaje mensaje) {
        return "Recibí el siguiente mensaje: " + mensaje.getMensaje();
    }
}

class Mensaje{
    private String mensaje;
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
