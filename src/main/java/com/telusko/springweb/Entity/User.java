package com.telusko.springweb.Entity;

import com.mongodb.lang.NonNull;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection="usersEntry")
@Data
public class User {
    @Id
    private ObjectId userId;
    @Indexed(unique = true)
    @NonNull
    private String name;
    @NonNull
    private String password;

    private String mobno;

    @DBRef
    private List<JournalEntry> userJournals =new ArrayList<>();
}
