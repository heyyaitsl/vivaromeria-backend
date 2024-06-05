package tfg.romerias.ticket.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tfg.romerias.ticket.converter.TicketConverter;
import tfg.romerias.ticket.model.Ticket;
import tfg.romerias.ticket.model.TicketResponse;
import tfg.romerias.ticket.service.ITicketService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/tickets")
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



}


