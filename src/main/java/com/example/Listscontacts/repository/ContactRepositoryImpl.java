package com.example.Listscontacts.repository;

import com.example.Listscontacts.exeption.ContactNotFoundException;
import com.example.Listscontacts.model.Contact;
import com.example.Listscontacts.repository.mapper.ContactRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
@Slf4j
public class ContactRepositoryImpl implements ContactRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Contact> findAll() {
        log.debug("Calling ContactRepositoryImpl -> findAll");

        String sql = "SELECT * FROM contact";

        return jdbcTemplate.query(sql, new ContactRowMapper());
    }

    @Override
    public Optional<Contact> findById(Long id) {
        log.debug("Calling ContactRepositoryImpl->findById with ID: {}", id);

        String sql = "SELECT * FROM contact WHERE id = ?";
        Contact contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new ArgumentPreparedStatementSetter(new Object[]{id}),
                        new RowMapperResultSetExtractor<>(new ContactRowMapper(), 1)
                )
        );

        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("Calling ContactRepositoryImpl->save with Task: {}", contact);

        contact.setId(System.currentTimeMillis());
        String sql = "INSERT INTO contact (firstName, lastName, email, phone, id) VALUES (?, ?, ?, ?,?)";
        jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone(), contact.getId());

        return contact;
    }

    @Override
    public Contact update(Contact contact) {
        log.debug("Calling ContactRepositoryImpl->update with Task: {}", contact);

        Contact existedContact = findById(contact.getId()).orElse(null);
        if(existedContact != null){
            String sql = "UPDATE contact SET firstName = ?, lastName = ?, email = ?, phone = ? WHERE id = ?";
            jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone(), contact.getId());
            return contact;
        }

        log.warn("Task with ID {} not found", contact.getId());

        throw new ContactNotFoundException("Task for update not found! ID: " + contact.getId());
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Calling ContactRepositoryImpl->deleteById with Id: {}", id);

        String sql = "DELETE FROM contact WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
