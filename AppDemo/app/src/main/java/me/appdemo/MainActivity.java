package me.appdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import me.appdemo.DataModel.Artist;
import me.appdemo.DataModel.Torrents;
import me.appdemo.adapter.TorrentGroupAdapter;

public class MainActivity extends AppCompatActivity {

    TorrentGroupAdapter adapter = null;
    ExpandableListView torrentList;
    List<Artist> artistList;
    List<Torrents> torrentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        artistList = new ArrayList<>();
        torrentsList = new ArrayList<>();

        artistList.add(new Artist(999, "Eminem", "RAP"));
        torrentsList.add(new Torrents("Musica RAP", "ALgun valor"));

        torrentList = (ExpandableListView) findViewById(R.id.exp_list);
        adapter = new TorrentGroupAdapter(this, artistList, torrentsList);
        torrentList.setAdapter(adapter);
    }

}
