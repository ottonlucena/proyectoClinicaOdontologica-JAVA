package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.segurity;

import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Usuario;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.UsuarioRole;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatosInicialesSecurity implements ApplicationRunner {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(usuarioRepository.findByEmail("otton@gmail.com").isEmpty()){
            //creamos el usuario de prueba como si fuera real
            BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
            String passSinCifrar = "digital"; //Separado para que se vea de donde sale
            String passCifrado = cifrador.encode(passSinCifrar);

            System.out.println("password " + passCifrado);

            //creamos el usuario
            Usuario usuario = new Usuario("Otton","otton","otton@gmail.com",passCifrado, UsuarioRole.ROLE_ADMIN);

            usuarioRepository.save(usuario);
        }

    }
}
