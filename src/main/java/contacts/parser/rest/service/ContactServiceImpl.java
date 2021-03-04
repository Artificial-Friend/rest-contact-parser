package contacts.parser.rest.service;

import contacts.parser.rest.model.Contact;
import contacts.parser.rest.repository.ContactRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Override
    public List<Contact> filterContactsByRegexFilter(String regex) {
        return contactRepository.findAll().parallelStream()
                .filter(e -> !e.getName().matches(regex))
                .collect(Collectors.toList());
    }
}
