package com.example.posterlab;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PostersListener{

    private Button buttonWatchlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView posterRecyclerView = findViewById(R.id.postersRecyclerView);
        buttonWatchlist = findViewById(R.id.addToWatchlist);

        List<Poster> posterList = new ArrayList<>();

        // Posters

        Poster one = new Poster();
        one.image = R.drawable.poster1;
        one.name = "Five nights at Freddy's";
        one.creator = "Scott Cawthon, Jason Blum";
        one.rating = 2.5f;
        one.story = "Old rundown establishment is in need of a night guard.";

        Poster two = new Poster();
        two.image = R.drawable.poster2;
        two.name = "Avengers: Endgame";
        two.creator = "Stan Lee, Marvel Studios";
        two.rating = 4.5f;
        two.story = "Sacrifices must be made as the hero's find out how fragile the world is.";

        Poster three = new Poster();
        three.image = R.drawable.poster3;
        three.name = "Spider-man: Homecoming";
        three.creator = "Stan Lee, Marvel Studios";
        three.rating = 3.5f;
        three.story = "Peter tries to live an ordinary life, while he tries to prove himself.";

        Poster four = new Poster();
        four.image = R.drawable.poster4;
        four.name = "Back to the Future";
        four.creator = "Robert Zemeckis, Bob Gale";
        four.rating = 4.5f;
        four.story = "Marty is thrown back in time to try and save his and Doc Browns life.";

        Poster five = new Poster();
        five.image = R.drawable.poster5;
        five.name = "Star Wars: A New Hope";
        five.creator = "George Lucas";
        five.rating = 4.5f;
        five.story = "Luke must help the Rebel Alliance, and restore freedom and justice to the Galaxy.";

        Poster six = new Poster();
        six.image = R.drawable.poster6;
        six.name = "Halloween (1978)";
        six.creator = "John Carpenter";
        six.rating = 3.5f;
        six.story = "Michael Myers stalks the town of Haddonfield, Illinois, looking for his next victim.";

        Poster seven = new Poster();
        seven.image = R.drawable.poster7;
        seven.name = "Home Alone";
        seven.creator = "Chris Columbus, John Hughes";
        seven.rating = 3.5f;
        seven.story = "Kevin McCallister must protect his home from con men as his parents forget about him on their way to vacation.";

        Poster eight = new Poster();
        eight.image = R.drawable.poster8;
        eight.name = "Moneyball";
        eight.creator = "Bennet Miller";
        eight.rating = 3.5f;
        eight.story = "A's GM Billy Beane is on a tight budget, but must create a team to outsmart all the other ballclubs.";

        Poster nine = new Poster();
        nine.image = R.drawable.poster9;
        nine.name = "Fight Club"; // I'm gonna be honest, i don't know how I selected two Brad Pitt movies in a row
        nine.creator = "David Fincher";
        nine.rating = 4.5f;
        nine.story = "We don't talk about this...";

        Poster ten = new Poster();
        ten.image = R.drawable.poster10;
        ten.name = "John Wick";
        ten.creator = "Chad Stahelski";
        ten.rating = 3.5f;
        ten.story = "The sudden death of his wife left him broken. But when thugs stole his car and killed his dog? Oh boy.";

        //

        posterList.add(one);
        posterList.add(two);
        posterList.add(three);
        posterList.add(four);
        posterList.add(five);
        posterList.add(six);
        posterList.add(seven);
        posterList.add(eight);
        posterList.add(nine);
        posterList.add(ten);


        final PosterAdapter posterAdapter = new PosterAdapter(posterList, this);
        posterRecyclerView.setAdapter(posterAdapter);

        buttonWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Poster> selectPoster = posterAdapter.getSelectedPosters();

                StringBuilder posterNames = new StringBuilder();
                for (int i = 0 ; i < selectPoster.size() ; i++){
                    if (i == 0){
                        posterNames.append(selectPoster.get(i).name);
                    } else {
                        posterNames.append("\n").append(selectPoster.get(i).name);
                    }
                }
                Toast.makeText(MainActivity.this, posterNames.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPosterAction(Boolean isSelected) {
        if (isSelected){
            buttonWatchlist.setVisibility(View.VISIBLE);
        } else {
            buttonWatchlist.setVisibility(View.GONE);
        }
    }
}