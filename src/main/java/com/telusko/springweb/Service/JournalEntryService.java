package com.telusko.springweb.Service;

import com.telusko.springweb.Entity.JournalEntry;
import com.telusko.springweb.Entity.User;
import com.telusko.springweb.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalRespository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry entry,String userName){
        User user=userService.findByUserName(userName).orElse(null);
        if(user==null){
            throw null;
        }
        entry.setDate(LocalDateTime.now());
        JournalEntry saved=journalRespository.save(entry);
        user.getUserJournals().add(saved);
        userService.saveUsers(user);
    }
    public List<JournalEntry> getAllJournal(){
        return(journalRespository.findAll());
    }
    public Optional<JournalEntry>findById(ObjectId id){
        return journalRespository.findById(id);
    }

    public void deleteEntry(String userName, ObjectId id) {
        User user=userService.findByUserName(userName).orElse(null);
        if(user==null){
            throw null;
        }
        else if (user.getUserJournals().stream().anyMatch(m->m.getId().equals(id))) {
            user.getUserJournals().removeIf(x -> x.getId().equals(id));
            userService.saveUsers(user);
            deleteById(id);
        }
        else{
            throw null;
        }
    }

    public void deleteById(ObjectId id){
        if(findById(id).isEmpty()){
            throw null;
        }
        journalRespository.deleteById(id);
        }

    public void saveEntry(JournalEntry oldEntry) {
        journalRespository.save(oldEntry);
    }
}
