package com.telusko.springweb.controller;

import com.telusko.springweb.Entity.JournalEntry;
import com.telusko.springweb.Entity.User;
import com.telusko.springweb.Service.JournalEntryService;
import com.telusko.springweb.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalService;

    @Autowired
    private UserService userService;

    //get all the journal entries done by a user
    @GetMapping("/showUserJournal/{userName}")
    public ResponseEntity<?>getAllJournalEntriesOfUser(@PathVariable String userName){
        User user=userService.findByUserName(userName).orElse(null);
        if(user==null){
            return new ResponseEntity<>("User is not registered with us!", HttpStatus.OK);
        }
        List<JournalEntry>EntriesByUser=user.getUserJournals();
        if(EntriesByUser.isEmpty()||EntriesByUser==null){
            return new ResponseEntity<>("No Entries present by this User!",HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(EntriesByUser);
    }
    //add user's journal
    @PostMapping("/addingJournal/{userName}")
    public ResponseEntity<?> addingJournal(
            @RequestBody JournalEntry entry,
            @PathVariable String userName
    ) {
        try{
            journalService.saveEntry(entry,userName);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>("journal can't be created!",HttpStatus.BAD_REQUEST);
        }
    }
    //delete user's any journal
    @DeleteMapping("/deleteJournal/{userName}/{id}")
    public ResponseEntity<?>deletingJournals(@PathVariable String userName,@PathVariable ObjectId id){
        try{
            journalService.deleteEntry(userName,id);
            return ResponseEntity.ok("Record deleted Successfully!");
        }
        catch (Exception e){
            return new ResponseEntity<>("journal can't be deleted!",HttpStatus.BAD_REQUEST);
        }
    }

    //update user's journal
    @PutMapping("/updateJournal")
    public ResponseEntity<?>updateJournal(
            @RequestBody JournalEntry entry){
        JournalEntry oldEntry=journalService.findById(entry.getId()).orElse(null);
        if(oldEntry!=null){
            new ResponseEntity<>("Entry is not present please check again!",HttpStatus.NOT_FOUND);
        }
        oldEntry.setTitle(entry.getTitle()!=null&&!entry.getTitle().isEmpty()? entry.getTitle():oldEntry.getTitle());
        oldEntry.setContent(entry.getContent()!=null&&!entry.getContent().isEmpty()?entry.getContent():oldEntry.getContent());
        journalService.saveEntry(oldEntry);
        return new ResponseEntity<>("Entry is updated successfully!",HttpStatus.CREATED);
    }


}
