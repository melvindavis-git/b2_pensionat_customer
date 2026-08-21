package org.example.pensionat_customer.Service;

import org.example.pensionat_customer.DTO.RoomDTO;
import org.example.pensionat_customer.Model.Room;
import org.example.pensionat_customer.Repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository repo;

    public RoomService(RoomRepository repo) {
        this.repo = repo;
    }

    public List<RoomDTO> getAllRooms() {
        return repo.findAll().stream().map(r -> RoomToRoomDTO(r)).toList();
    }

    public RoomDTO RoomToRoomDTO(Room r) {
        return RoomDTO.builder().id(r.getId()).nr(r.getNr()).isDoubleRoom(r.isDoubleRoom()).build();
    }

}
