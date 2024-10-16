package com.tfg.boulder_back.service;

import com.tfg.boulder_back.domain.request.UpdateBoulderRequest;
import com.tfg.boulder_back.entity.Boulder;
import com.tfg.boulder_back.exceptions.BoulderNotFoundException;
import com.tfg.boulder_back.repository.BoulderRepository;
import com.tfg.boulder_back.service.impl.BoulderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoulderServiceImplTest {

    @Mock
    private BoulderRepository boulderRepository;

    @InjectMocks
    private BoulderServiceImpl boulderService;

    private Boulder boulder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        boulder = new Boulder(1L, "Boulder One", "123 Street", "Locality", "test@mail.com", "987654321", null, null);
    }

    @Test
    void testAddBoulder() {

        Mockito.when(boulderRepository.save(Mockito.any())).thenReturn(boulder);

        Boulder savedBoulder = boulderService.addBoulder(boulder);

        // Verificaa que el Boulder guardado es el esperado
        assertNotNull(savedBoulder);
        assertEquals(boulder.getName(), savedBoulder.getName());
        Mockito.verify(boulderRepository, Mockito.times(1)).save(boulder);
    }

    @Test
    void testFindAllBoulders() {
        // Simular que el repositorio devuelve una lista de Boulders
        List<Boulder> boulders = Collections.singletonList(boulder);
        Mockito.when(boulderRepository.findAll()).thenReturn(boulders);

        List<Boulder> foundBoulders = boulderService.findAllBoulders();

        // Verificar que la lista tiene el tamaño correcto
        assertEquals(1, foundBoulders.size());
        assertEquals(boulder.getName(), foundBoulders.get(0).getName());
        Mockito.verify(boulderRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testEditBoulderSuccess() {
        // Datos para la actualización
        UpdateBoulderRequest updateRequest = new UpdateBoulderRequest("Updated Boulder", "New Address", "New Locality", "new@mail.com", "942876456", "942876455");

        // Simular que el Boulder existe en la base de datos
        Mockito.when(boulderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(boulder));
        Mockito.when(boulderRepository.save(Mockito.any())).thenReturn(boulder);

        // Invocar el método del servicio para editar el Boulder
        Boulder updatedBoulder = boulderService.editBoulder(1L, updateRequest);

        // Verificar que los campos del Boulder han sido actualizados
        assertEquals(updateRequest.getName(), updatedBoulder.getName());
        assertEquals(updateRequest.getAddress(), updatedBoulder.getAddress());
        assertEquals(updateRequest.getMail(), updatedBoulder.getMail());
        Mockito.verify(boulderRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(boulderRepository, Mockito.times(1)).save(boulder);
    }

    @Test
    void testEditBoulderNotFound() {
        // Simular que el Boulder no existe en la base de datos
        Mockito.when(boulderRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        // Verificar que se lanza la excepción BoulderNotFoundException
        assertThrows(BoulderNotFoundException.class, () -> {
            boulderService.editBoulder(999L, new UpdateBoulderRequest());
        });

        // Verificar que el método findById fue invocado una vez
        Mockito.verify(boulderRepository, Mockito.times(1)).findById(999L);
        // Verificar que el método save nunca fue invocado
        Mockito.verify(boulderRepository, Mockito.times(0)).save(Mockito.any());
    }


}
