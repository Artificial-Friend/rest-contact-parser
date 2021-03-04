package contacts.parser.rest.service;

import contacts.parser.rest.model.Contact;
import java.util.List;

public interface ContactService {
    List<Contact> filterContactsByRegexFilter(String regex);
}
