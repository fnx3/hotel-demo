package by.timo.hotel.demo.hoteldemo.mapper;

import by.timo.hotel.demo.hoteldemo.dto.ContactsDto;
import by.timo.hotel.demo.hoteldemo.model.Contacts;
import org.springframework.stereotype.Component;

@Component
public class ContactsMapper {

    public ContactsDto fromEntityToDto(Contacts contacts) {
        return new ContactsDto(contacts.getPhone(), contacts.getEmail());
    }

    public Contacts fromDtoToEntity(ContactsDto contactsDto) {
        return new Contacts(contactsDto.getPhone(), contactsDto.getEmail());
    }
}
