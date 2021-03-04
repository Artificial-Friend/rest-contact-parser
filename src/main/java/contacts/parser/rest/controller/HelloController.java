package contacts.parser.rest.controller;

import contacts.parser.rest.model.Contact;
import contacts.parser.rest.service.ContactService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/hello")
public class HelloController {
    private final ContactService contactService;

    @GetMapping("/contacts")
    public List<Contact> getContactsByFilter(@RequestParam String nameFilter) {
        return contactService.filterContactsByRegexFilter(nameFilter);
    }
}
