package tfg.romerias.ticket.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfg.romerias.pilgrimage.model.PilgrimageResponse;
import tfg.romerias.ticket.converter.TicketConverter;
import tfg.romerias.ticket.model.Ticket;
import tfg.romerias.ticket.model.TicketRequest;
import tfg.romerias.ticket.model.TicketResponse;
import tfg.romerias.ticket.service.ITicketService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(value = "http://localhost:5173")
public class TicketController {
    private final ITicketService ticketService;
    private final TicketConverter converter;

    public TicketController(ITicketService ticketService, TicketConverter converter) {
        this.ticketService = ticketService;
        this.converter = converter;
    }


    @GetMapping()
    public List<TicketResponse> getTickets(){
        List<Ticket> tickets = ticketService.getTickets();
        return tickets.stream().map(converter::convertToResponse).collect(Collectors.toList());
    }

    @GetMapping("user/{username}")
    public List<TicketResponse> getTicketsByUsername(@PathVariable String username){
        List<Ticket> tickets = ticketService.getTicketsByUsername(username);
        return tickets.stream().map(converter::convertToResponse).collect(Collectors.toList());
    }
    @GetMapping("floats/{id}")
    public ResponseEntity<TicketResponse> getTicketsById(@PathVariable Integer id){
        return ResponseEntity.ok(converter.convertToResponse(ticketService.getTicketById(id)));
    }

    @PostMapping("/buy")
    public ResponseEntity<TicketResponse> buyTicket(@RequestBody TicketRequest ticketRequest){
        return ResponseEntity.of(Optional.of(converter.convertToResponse(ticketService.buyTicket(ticketRequest.getUsername(),
                ticketRequest.getPilgrimageId(), ticketRequest.getFloatsId()))));

    }



}


