package com.example.geschat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geschat.adapters.AnnouncementAdapter;
import com.example.geschat.models.Announcement;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Collections;


public class HomeFragment extends Fragment implements AnnouncementAdapter.OnAnnListListener{

    ArrayList<Announcement> anns;
    DatabaseReference suggestionRef, AnnRef, db;
    TextView bookTxt, wordTxt, phraseTxt;
    RecyclerView rvAnnouncements;
    AnnouncementAdapter annAdapter;
    String role;
    FloatingActionButton floatingActionButton;

//TODO agregar progressbar

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container,false);

        bookTxt = view.findViewById(R.id.suggBook);
        wordTxt = view.findViewById(R.id.suggWord);
        phraseTxt = view.findViewById(R.id.suggPhrase);

        //Boton
        floatingActionButton = view.findViewById(R.id.btn_addAnnouncement);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), AddAnnouncementActivity.class);
                startActivity(in);
            }
        });

        floatingActionButton.hide();


        db = FirebaseDatabase.getInstance().getReference();
        suggestionRef = db.child("Suggestion");
        AnnRef = db.child("Announcement");


        //RecyclerView
        rvAnnouncements = view.findViewById(R.id.rvAnnouncements);
        rvAnnouncements.setLayoutManager(new LinearLayoutManager(getActivity()));



        AnnRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                anns = new ArrayList<>();
                if(dataSnapshot.exists()){
                    for(DataSnapshot dataSnapshotAnn: dataSnapshot.getChildren())
                    {
                        Announcement ann = dataSnapshotAnn.getValue(Announcement.class);
                        ann.setKeyDB(dataSnapshotAnn.getKey());
                        anns.add(ann);
                    }

                    setAnnRvAdapter();

                } else
                    {

                    setAnnRvAdapter();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "There was an error fetching announcements", Toast.LENGTH_SHORT).show();
            }
        });


        populateSuggestions();
        fetchRole();


        return view;

    }

    @Override
    public void onAnnClick(int position) {

        Announcement ann = anns.get(position);

        Intent intent = new Intent(getActivity(), AnnouncementActivity.class);
        intent.putExtra("title", ann.getTitle());
        intent.putExtra("body", ann.getBody());
        intent.putExtra("author", ann.getAuthor());
        intent.putExtra("keyDB", ann.getKeyDB());

        startActivity(intent);

    }

    private void populateSuggestions(){


        suggestionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String book = dataSnapshot.child("book").getValue(String.class);
                String phrase = dataSnapshot.child("phrase").getValue(String.class);
                String word = dataSnapshot.child("word").getValue(String.class);

                bookTxt.setText(book);
                phraseTxt.setText(phrase);
                wordTxt.setText(word);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

    }

    private void setAnnRvAdapter(){
        Collections.reverse(anns);
        annAdapter = new AnnouncementAdapter(anns,this);
        rvAnnouncements.setAdapter(annAdapter);
    }

    private void setVisibleToSupervisor(){
        floatingActionButton.show();

    }

    private void fetchRole(){
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        db.child("Users").child(uid).child("role").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                role = dataSnapshot.getValue(String.class);
                if(role.equals("supervisor")){
                    setVisibleToSupervisor();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });


    }

}
